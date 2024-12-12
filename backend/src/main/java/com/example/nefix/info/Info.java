package com.example.nefix.info;

import com.example.nefix.infomovie.InfoType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Info
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="info_id")
    private Long infoId;

    private String description;
    private InfoType type;
}
