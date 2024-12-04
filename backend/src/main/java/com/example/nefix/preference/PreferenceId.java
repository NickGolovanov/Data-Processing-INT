package com.example.nefix.preference;

import lombok.Data;
import org.aspectj.lang.Signature;

import java.io.Serializable;

public class PreferenceId implements Serializable
{
    private Long profileId;

    private Long infoId;
}