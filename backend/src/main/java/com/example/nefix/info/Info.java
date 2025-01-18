package com.example.nefix.info;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Objects;

@Data
@Entity
public class Info
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "info_id")
    private Long infoId;

    @JsonProperty("description")
    @NotBlank(message = "Description cannot be blank")
    private String description;

    @Enumerated(EnumType.STRING)
    @JsonProperty("type")
    @NotNull(message = "Description must not be blank.")
    private InfoType type;

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Info that = (Info) o;
        return Objects.equals(this.infoId, that.infoId);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(this.infoId);
    }
}
