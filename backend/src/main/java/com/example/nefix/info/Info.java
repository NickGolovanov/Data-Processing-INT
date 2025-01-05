package com.example.nefix.info;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class Info
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "info_id")
    private Long infoId;

    @JsonProperty("description")
    @NotBlank(message = "Description must not be blank.")
    @NotNull(message = "Description must not be null.")
    private String description;

    @JsonProperty("type")
    @NotNull(message = "Type must not be null.")
    private InfoType type;
}
