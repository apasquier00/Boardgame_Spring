package com.example.spring_boardgame.controller;

import io.micrometer.common.lang.Nullable;

import java.util.UUID;

public record GameDataDto(UUID gameId, String gametype, @Nullable UUID winnerId, @Nullable UUID loserId) {
}
