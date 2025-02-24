package com.example.spring_boardgame.data.repository.entity;
import io.micrometer.common.lang.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;

@Entity
public class TokenEntity {
    @Id
    private Long id;
    private @NotNull String ownerId;
    private @NotNull String name;
    private boolean removed;
    private @Nullable Integer x;
    private @Nullable Integer y;
}