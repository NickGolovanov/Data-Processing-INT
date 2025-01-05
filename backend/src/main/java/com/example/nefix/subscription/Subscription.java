package com.example.nefix.subscription;

import com.example.nefix.accountsubscription.AccountSubscription;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Subscription
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subscription_id")
    private Long subscriptionId;

    @JsonProperty("description")
    @NotBlank(message = "Description must not be blank.")
    @NotNull(message = "Description must not be null.")
    private String description;

    @JsonProperty("subscriptionPrice")
    @Column(name = "subscription_price")
    @NotNull(message = "Subscription price must not be null.")
    private Double subscriptionPrice;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties({"subscription"})
    private Set<AccountSubscription> accounts = new HashSet<>();
}
