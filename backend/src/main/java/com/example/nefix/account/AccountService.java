package com.example.nefix.account;

import com.example.nefix.accountsubscription.AccountSubscription;
import com.example.nefix.accountsubscription.AccountSubscriptionId;
import com.example.nefix.accountsubscription.AccountSubscriptionRepository;
import com.example.nefix.accountsubscription.AccountSubscriptionRequestDto;
import com.example.nefix.blockedaccount.BlockedAccount;
import com.example.nefix.blockedaccount.BlockedAccountRequestDto;
import com.example.nefix.blockedaccount.BlockedAccountsRepository;
import com.example.nefix.genrealization.service.BaseService;
import com.example.nefix.referraldiscount.ReferralDiscount;
import com.example.nefix.referraldiscount.ReferralDiscountRepository;
import com.example.nefix.referraldiscount.ReferralDiscountRequestDto;
import com.example.nefix.referraldiscount.ReferralDiscountResponseDto;
import com.example.nefix.subscription.Subscription;
import com.example.nefix.subscription.SubscriptionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService extends BaseService<Account, Long>
{
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private AccountSubscriptionRepository accountSubscriptionRepository;

    @Autowired
    private BlockedAccountsRepository blockedAccountsRepository;

    @Autowired
    private ReferralDiscountRepository referralDiscountRepository;

    public AccountService(AccountRepository repository)
    {
        super(repository);
    }

    @Transactional
    public AccountSubscription addSubscription(Long accountId, Long subscriptionId, AccountSubscriptionRequestDto requestDto)
    {
        // Fetch Account
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found with ID: " + accountId));

        // Fetch Subscription
        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new RuntimeException("Subscription not found with ID: " + subscriptionId));

        // Create AccountSubscriptionId
        AccountSubscriptionId accountSubscriptionId = new AccountSubscriptionId(accountId, subscriptionId);

        // Create and Populate AccountSubscription
        AccountSubscription accountSubscription = new AccountSubscription(
                accountSubscriptionId,
                account,
                subscription,
                requestDto.getDateOfPurchase(),
                requestDto.getDateOfExpire()
        );

        // Save and Return
        return accountSubscriptionRepository.save(accountSubscription);
    }

    // Method to update an existing subscription for the account
    @Transactional
    public AccountSubscription updateSubscription(Long accountId, Long subscriptionId, AccountSubscriptionRequestDto requestDto)
    {
        // Fetch Account
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found with ID: " + accountId));

        // Fetch Subscription
        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new RuntimeException("Subscription not found with ID: " + subscriptionId));

        // Fetch existing AccountSubscription
        AccountSubscriptionId accountSubscriptionId = new AccountSubscriptionId(accountId, subscriptionId);
        AccountSubscription existingAccountSubscription = accountSubscriptionRepository.findById(accountSubscriptionId)
                .orElseThrow(() -> new RuntimeException("AccountSubscription not found with the given account and subscription IDs"));

        // Update fields from the request DTO
        if (requestDto.getDateOfPurchase() != null)
        {
            existingAccountSubscription.setDateOfPurchase(requestDto.getDateOfPurchase());
        }
        if (requestDto.getDateOfExpire() != null)
        {
            existingAccountSubscription.setDateOfExpire(requestDto.getDateOfExpire());
        }
        // Additional updates can be added here, like renewal preferences or subscription type
        // For example:
        //         existingAccountSubscription.setRenewalPreference(requestDto.getRenewalPreference());

        // Save updated AccountSubscription entity
        return accountSubscriptionRepository.save(existingAccountSubscription);
    }


    @Transactional
    public void deleteSubscription(Long accountId, Long subscriptionId)
    {
        // Fetch Account
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found with ID: " + accountId));

        // Fetch Subscription
        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new RuntimeException("Subscription not found with ID: " + subscriptionId));

        // Fetch existing AccountSubscription using composite key
        AccountSubscriptionId accountSubscriptionId = new AccountSubscriptionId(accountId, subscriptionId);
        AccountSubscription accountSubscription = accountSubscriptionRepository.findById(accountSubscriptionId)
                .orElseThrow(() -> new RuntimeException("AccountSubscription not found for the given account and subscription IDs"));

        // Cleanup (if necessary)
        // Example: Validate or check if there are any dependencies before deletion

        // Delete the AccountSubscription
        accountSubscriptionRepository.delete(accountSubscription);
    }

    public BlockedAccount blockAccount(Long accountId, BlockedAccountRequestDto requestDto)
    {
        // Fetch the account to be blocked
        Account account = repository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found with ID: " + accountId));

        // Create and populate the BlockedAccount entity
        BlockedAccount blockedAccount = new BlockedAccount();
        blockedAccount.setAccount(account);
        blockedAccount.setIsPermanent(requestDto.isPermanent());
        blockedAccount.setDateOfExpire(requestDto.isPermanent() ? null : requestDto.getDateOfExpire());

        // Save the BlockedAccount
        return blockedAccountsRepository.save(blockedAccount);
    }

    public void unblockAccount(Long accountId)
    {
        // Fetch the account to unblock
        Account account = repository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found with ID: " + accountId));

        // Find the associated BlockedAccount record
        BlockedAccount blockedAccount = blockedAccountsRepository.findByAccount_AccountId(account.getAccountId())
                .orElseThrow(() -> new RuntimeException("No block record found for account ID: " + accountId));

        // Delete the BlockedAccount record
        blockedAccountsRepository.delete(blockedAccount);
    }

    @Transactional
    public ReferralDiscount addReferralDiscount(Long accountId, ReferralDiscountRequestDto requestDto)
    {
        // Fetch Account
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found with ID: " + accountId));

        // Create ReferralDiscount
        ReferralDiscount referralDiscount = new ReferralDiscount();
        referralDiscount.setLink(requestDto.getLink());
        referralDiscount.setAccount(account);

        // Save ReferralDiscount and link to Account
        return referralDiscountRepository.save(referralDiscount);
    }

    public List<ReferralDiscountResponseDto> getReferralDiscounts(Long accountId)
    {
        // Fetch the Account
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found with ID: " + accountId));

        // Fetch all ReferralDiscounts linked to the Account
        List<ReferralDiscount> referralDiscounts = referralDiscountRepository.findByAccount(account);

        // Convert ReferralDiscount entities to Response DTOs
        return referralDiscounts.stream()
                .map(referralDiscount -> new ReferralDiscountResponseDto(
                        referralDiscount.getReferralDiscountId(),
                        referralDiscount.getLink()
                ))
                .collect(Collectors.toList());
    }

}

