package com.example.nefix.account;

import com.example.nefix.accountsubscription.AccountSubscription;
import com.example.nefix.accountsubscription.AccountSubscriptionId;
import com.example.nefix.blockedaccount.BlockedAccount;
import com.example.nefix.profile.Profile;
import com.example.nefix.referraldiscount.ReferralDiscount;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.Check;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.*;

@Data
@Entity
public class Account implements Serializable, UserDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(value = "accountId", access = JsonProperty.Access.READ_ONLY)
    private Long accountId;

    @Enumerated(EnumType.STRING)
    @JsonProperty("paymentMethod")
    @Column(name = "payment_method")
    private PaymentMethod paymentMethod;

    @JsonProperty("email")
    private String email;

    @Check(constraints = "LENGTH(password) >= 8")
    @JsonProperty(value = "password", access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties({"accountId"})
    private Set<AccountSubscription> subscriptions = new HashSet<>();

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties({"accountId", "watchList", "liveInfos", "preferenceId"})
    private Set<Profile> profiles = new HashSet<>();

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties({"accountId"})
    private Set<BlockedAccount> blockedAccounts = new HashSet<>();

    @OneToOne(mappedBy = "account")
    @JoinColumn(name = "referral_discount_id")
    private ReferralDiscount referralDiscount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account that = (Account) o;
        return Objects.equals(this.accountId, that.accountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.accountId);
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("USER"));
    }

    @Override
    @JsonIgnore
    public String getPassword()
    {
        return this.password;
    }

    @Override
    @JsonIgnore
    public String getUsername()
    {
        return this.email;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled()
    {
        return true;
    }
}
