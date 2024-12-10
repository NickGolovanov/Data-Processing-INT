package com.example.nefix.info;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
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
    private String description;

    @JsonProperty("type")
    private InfoType type;
}
