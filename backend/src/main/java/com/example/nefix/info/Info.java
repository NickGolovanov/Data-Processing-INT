package com.example.nefix.info;

import com.example.nefix.infomovie.InfoType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Info
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long infoId;

    private String description;
    private InfoType type;
}
