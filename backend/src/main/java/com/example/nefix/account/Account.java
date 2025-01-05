package com.example.nefix.account;

import com.example.nefix.accountsubscription.AccountSubscription;
import com.example.nefix.blockedaccount.BlockedAccount;
import com.example.nefix.profile.Profile;
import com.example.nefix.referraldiscount.ReferralDiscount;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.Check;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Account
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(value = "accountId", access = JsonProperty.Access.READ_ONLY)
    private Long accountId;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("account")
    private Set<AccountSubscription> subscriptions = new HashSet<>();

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties({"account", "liveInfos", "preference", "watchList"})
    private Set<Profile> profiles = new HashSet<>();

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("account")
    private Set<BlockedAccount> blockedAccounts = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "referral_discount_id")
    @JsonProperty("referralDiscount")
    private ReferralDiscount referralDiscount;

    @Enumerated(EnumType.STRING)
    @JsonProperty("paymentMethod")
    @Column(name = "payment_method")
    @NotNull(message = "Payment method is required.")
    private PaymentMethod paymentMethod;

    @JsonProperty("email")
    @Email(message = "Invalid email format.")
    @NotBlank(message = "Email is required.")
    private String email;

    @Check(constraints = "LENGTH(password) >= 8")
    @JsonProperty("password")
    @NotBlank(message = "Password is required.")
    @Size(min = 8, message = "Password must be at least 8 characters long.")
    private String password;
}