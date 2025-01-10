package com.example.nefix.account;

import com.example.nefix.accountsubscription.AccountSubscription;
import com.example.nefix.blockedaccount.BlockedAccount;
import com.example.nefix.genrealization.controller.BaseController;
import com.example.nefix.referraldiscount.ReferralDiscount;
import com.example.nefix.subscription.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController extends BaseController<Account, Long>
{
    @Autowired
    private AccountService accountService;

    public AccountController(AccountService service)
    {
        super(service);
    }

    @PostMapping("/{accountId}/subscription/{subscriptionId}")
    public ResponseEntity<?> addSubscription(
            @PathVariable Long accountId,
            @PathVariable Long subscriptionId,
            @RequestBody AccountSubscriptionRequestDto requestDto) {
        try {
             AccountSubscription accountSubscription = accountService.addSubscription(accountId, subscriptionId, requestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(accountSubscription);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // PATCH endpoint to update the subscription
    @PatchMapping("/{accountId}/subscription/{subscriptionId}")
    public ResponseEntity<?> updateSubscription(
            @PathVariable Long accountId,
            @PathVariable Long subscriptionId,
            @RequestBody AccountSubscriptionRequestDto requestDto) {
        try {
            // Call service to update the subscription
            AccountSubscription updatedAccountSubscription = accountService.updateSubscription(accountId, subscriptionId, requestDto);
            // Return the updated subscription details in the response DTO
            return ResponseEntity.status(HttpStatus.OK).body(updatedAccountSubscription);
        } catch (RuntimeException e) {
            // If account or subscription is not found, return 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{accountId}/subscription/{subscriptionId}")
    public ResponseEntity<?> deleteSubscription(
            @PathVariable Long accountId,
            @PathVariable Long subscriptionId) {
        try {
            // Call the service to delete the subscription
            accountService.deleteSubscription(accountId, subscriptionId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            // Handle case when Account or Subscription is not found
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PostMapping("/{accountId}/block")
    public ResponseEntity<BlockedAccount> blockAccount(
            @PathVariable Long accountId,
            @RequestBody BlockedAccountRequestDto requestDto) {
        BlockedAccount blockedAccount = accountService.blockAccount(accountId, requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(blockedAccount);
    }

    @DeleteMapping("/{accountId}/block")
    public ResponseEntity<Void> unblockAccount(@PathVariable Long accountId) {
        accountService.unblockAccount(accountId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{accountId}/referralDiscount")
    public ResponseEntity<ReferralDiscount> addReferralDiscount(
            @PathVariable Long accountId,
            @RequestBody ReferralDiscountRequestDto requestDto) {
        try {
            ReferralDiscount referralDiscount = accountService.addReferralDiscount(accountId, requestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(referralDiscount);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/{accountId}/referralDiscount")
    public ResponseEntity<List<ReferralDiscountResponseDto>> getAllReferralDiscounts(
            @PathVariable Long accountId) {
        try {
            List<ReferralDiscountResponseDto> referralDiscounts = accountService.getReferralDiscounts(accountId);
            return ResponseEntity.status(HttpStatus.OK).body(referralDiscounts);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}