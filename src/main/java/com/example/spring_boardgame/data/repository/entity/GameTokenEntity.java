package com.example.spring_boardgame.data.repository.entity;
import io.micrometer.common.lang.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;

import java.util.UUID;

@Entity
public class GameTokenEntity {

    @Id
    private UUID id;
    private @NotNull UUID ownerId;
    private @NotNull String name;
    private boolean removed;
    private @Nullable Integer x;
    private @Nullable Integer y;

    public GameTokenEntity(UUID id, UUID ownerId, String name) {
        this.id = id;
        this.ownerId = ownerId;
        this.name = name;

    }

    public GameTokenEntity() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(UUID ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }

    @Nullable
    public Integer getX() {
        return x;
    }

    public void setX(@Nullable Integer x) {
        this.x = x;
    }

    @Nullable
    public Integer getY() {
        return y;
    }

    public void setY(@Nullable Integer y) {
        this.y = y;
    }

}