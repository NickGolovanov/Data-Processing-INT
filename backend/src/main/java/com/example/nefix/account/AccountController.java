package com.example.nefix.account;

import com.example.nefix.accountsubscription.AccountSubscription;
import com.example.nefix.accountsubscription.AccountSubscriptionRequestDto;
import com.example.nefix.blockedaccount.BlockedAccount;
import com.example.nefix.blockedaccount.BlockedAccountRequestDto;
import com.example.nefix.genrealization.controller.BaseController;
import com.example.nefix.genrealization.response.ApiResponse;
import com.example.nefix.genrealization.response.ErrorResponse;
import com.example.nefix.referraldiscount.ReferralDiscount;
import com.example.nefix.referraldiscount.ReferralDiscountRequestDto;
import com.example.nefix.referraldiscount.ReferralDiscountResponseDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController extends BaseController<Account, Long>
{
    @Autowired
    private AccountService accountService;

    public AccountController(AccountService service)
    {
        super(service);
    }

    @PostMapping("/{accountId}/subscription/{subscriptionId}")
    public ResponseEntity<ApiResponse<AccountSubscription>> addSubscription(
            @PathVariable Long accountId,
            @PathVariable Long subscriptionId,
            @Valid @RequestBody AccountSubscriptionRequestDto requestDto)
    {
        try
        {
            AccountSubscription accountSubscription = this.accountService.addSubscription(accountId, subscriptionId, requestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(accountSubscription, null));
        } catch (RuntimeException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(null, new ErrorResponse(e.getMessage())));
        }
    }

    @PatchMapping("/{accountId}/subscription/{subscriptionId}")
    public ResponseEntity<ApiResponse<AccountSubscription>> updateSubscription(
            @PathVariable Long accountId,
            @PathVariable Long subscriptionId,
            @Valid @RequestBody AccountSubscriptionRequestDto requestDto)
    {
        try
        {
            AccountSubscription updatedAccountSubscription = this.accountService.updateSubscription(accountId, subscriptionId, requestDto);

            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(updatedAccountSubscription, null));
        } catch (RuntimeException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(null, new ErrorResponse(e.getMessage())));
        }
    }

    @DeleteMapping("/{accountId}/subscription/{subscriptionId}")
    public ResponseEntity<ApiResponse<Void>> deleteSubscription(
            @PathVariable Long accountId,
            @PathVariable Long subscriptionId)
    {
        try
        {
            this.accountService.deleteSubscription(accountId, subscriptionId);

            return ResponseEntity.noContent().build();
        } catch (RuntimeException e)
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse<>(null, new ErrorResponse(e.getMessage())));
        }
    }

    @GetMapping("/{accountId}/block")
    public ResponseEntity<ApiResponse<Boolean>> isAccountBlocked(@PathVariable Long accountId)
    {
        try
        {
            Boolean isBlocked = accountService.isAccountBlocked(accountId);
            return ResponseEntity.ok(new ApiResponse<>(isBlocked, null));
        } catch (RuntimeException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(null, new ErrorResponse(e.getMessage())));
        }
    }

    @PostMapping("/{accountId}/block")
    public ResponseEntity<ApiResponse<BlockedAccount>> blockAccount(
            @PathVariable Long accountId,
            @Valid @RequestBody BlockedAccountRequestDto requestDto)
    {
        try
        {
            BlockedAccount blockedAccount = accountService.blockAccount(accountId, requestDto);

            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(blockedAccount, null));
        } catch (RuntimeException e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(null, new ErrorResponse(e.getMessage())));
        }
    }

    @DeleteMapping("/{accountId}/block")
    public ResponseEntity<ApiResponse<Void>> unblockAccount(@PathVariable Long accountId)
    {
        try
        {
            accountService.unblockAccount(accountId);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponse<>(null, null));
        } catch (RuntimeException e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(null, new ErrorResponse(e.getMessage())));
        }
    }

    @PostMapping("/{accountId}/referral-discount")
    public ResponseEntity<ApiResponse<ReferralDiscount>> addReferralDiscount(
            @PathVariable Long accountId,
            @Valid @RequestBody ReferralDiscountRequestDto requestDto)
    {
        try
        {
            ReferralDiscount referralDiscount = accountService.addReferralDiscount(accountId, requestDto);

            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(referralDiscount, null));
        } catch (RuntimeException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(null, new ErrorResponse(e.getMessage())));
        }
    }

    @GetMapping("/{accountId}/referral-discount")
    public ResponseEntity<ApiResponse<List<ReferralDiscountResponseDto>>> getAllReferralDiscounts(
            @PathVariable Long accountId)
    {
        try
        {
            List<ReferralDiscountResponseDto> referralDiscounts = accountService.getReferralDiscounts(accountId);

            return ResponseEntity.ok(new ApiResponse<>(referralDiscounts, null));
        } catch (RuntimeException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(null, new ErrorResponse(e.getMessage())));
        }
    }
}