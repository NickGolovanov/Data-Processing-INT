package com.example.nefix.blockedaccount;

import com.example.nefix.account.Account;
import com.example.nefix.account.AccountDeserializer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@Table(name = "blocked_account")
public class BlockedAccount implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blocked_accountId")
    private Long blockedAccountId;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    @JsonProperty(value = "accountId", access = JsonProperty.Access.WRITE_ONLY)
    @JsonDeserialize(using = AccountDeserializer.class)
    @NotNull(message = "Account must not be null.")
    private Account account;

    @JsonProperty("dateOfExpire")
    @Column(name = "date_of_expire")
    @Future(message = "Date of expire must be in the future.")
    @NotNull(message = "Date of expire must not be null.")
    private LocalDate dateOfExpire;

    @JsonProperty("isPermanent")
    @Column(name = "is_permanent")
    @NotNull(message = "Permanent status must not be null.")
    private Boolean isPermanent;

    @JsonProperty(value = "accountId", access = JsonProperty.Access.READ_ONLY)
    public Long getAccountId()
    {
        return account.getAccountId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlockedAccount that = (BlockedAccount) o;
        return Objects.equals(this.blockedAccountId, that.blockedAccountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.blockedAccountId);
    }
}
