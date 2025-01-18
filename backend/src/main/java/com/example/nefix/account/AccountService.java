package com.example.nefix.account;

import com.example.nefix.accountsubscription.AccountSubscription;
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
import com.example.nefix.subscription.SubscriptionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService extends BaseService<Account, Long>
{
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountSubscriptionRepository accountSubscriptionRepository;

    @Autowired
    private BlockedAccountsRepository blockedAccountsRepository;

    @Autowired
    private ReferralDiscountRepository referralDiscountRepository;

    private PasswordEncoder passwordEncoder;

    public AccountService(AccountRepository repository)
    {
        super(repository, List.of());
    }

    @Override
    public Account update(Long id, Account entity) throws RuntimeException
    {
        Account existingEntity = this.accountRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Entity not found with id: " + id)
        );

        existingEntity.setEmail(entity.getEmail());
        existingEntity.setPassword(this.passwordEncoder.encode(entity.getPassword()));
        existingEntity.setPaymentMethod(entity.getPaymentMethod());

        return this.repository.save(existingEntity);
    }

    @Transactional
    public AccountSubscription addSubscription(Long accountId, Long subscriptionId, AccountSubscriptionRequestDto requestDto)
    {
        this.accountSubscriptionRepository.callAddSubscription(accountId, subscriptionId, requestDto.getDateOfPurchase(), requestDto.getDateOfExpire());

        return accountSubscriptionRepository.getByAccount_AccountIdAndSubscription_SubscriptionId(accountId, subscriptionId);
    }

    @Transactional
    public AccountSubscription updateSubscription(Long accountId, Long subscriptionId, AccountSubscriptionRequestDto requestDto)
    {
        this.accountSubscriptionRepository.callUpdateSubscription(accountId, subscriptionId, requestDto.getDateOfPurchase(), requestDto.getDateOfExpire());

        return accountSubscriptionRepository.getByAccount_AccountIdAndSubscription_SubscriptionId(accountId, subscriptionId);
    }


    @Transactional
    public void deleteSubscription(Long accountId, Long subscriptionId)
    {
        this.accountSubscriptionRepository.callDeleteSubscription(accountId, subscriptionId);
    }

    public boolean isAccountBlocked(Long accountId)
    {
        List<BlockedAccount> blockedAccounts = this.blockedAccountsRepository.getBlockedAccountsByAccount_AccountId(accountId);
        for (BlockedAccount blockedAccount : blockedAccounts)
        {
            if (blockedAccount.getIsPermanent() || blockedAccount.getDateOfExpire().isAfter(LocalDate.now()))
            {
                return true;
            }
        }

        return false;
    }

    public BlockedAccount blockAccount(Long accountId, BlockedAccountRequestDto requestDto)
    {
        this.blockedAccountsRepository.callBlockAccount(accountId, requestDto.isPermanent(), requestDto.getDateOfExpire());

        return this.blockedAccountsRepository.findByAccount_AccountId(accountId).orElseThrow(() -> new RuntimeException("Account not found with ID: " + accountId));
    }

    @Transactional
    public void unblockAccount(Long accountId)
    {
        this.blockedAccountsRepository.callUnblockAccount(accountId);
    }

    @Transactional
    public ReferralDiscount addReferralDiscount(Long accountId, ReferralDiscountRequestDto requestDto)
    {
        Account account = this.accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found with ID: " + accountId));

        ReferralDiscount referralDiscount = new ReferralDiscount();
        referralDiscount.setLink(requestDto.getLink());
        referralDiscount.setAccount(account);

        return referralDiscountRepository.save(referralDiscount);
    }

    public List<ReferralDiscountResponseDto> getReferralDiscounts(Long accountId)
    {
        Account account = this.accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found with ID: " + accountId));

        List<ReferralDiscount> referralDiscounts = this.referralDiscountRepository.findByAccount(account);

        return referralDiscounts.stream()
                .map(referralDiscount -> new ReferralDiscountResponseDto(
                        referralDiscount.getReferralDiscountId(),
                        referralDiscount.getLink()
                ))
                .collect(Collectors.toList());
    }
}

