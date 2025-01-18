package com.example.nefix.liveinfo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LiveInfoDTO
{
    @JsonProperty("watchedTime")
    private String watchedTime;
}
