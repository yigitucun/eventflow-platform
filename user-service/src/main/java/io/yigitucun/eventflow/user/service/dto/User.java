package io.yigitucun.eventflow.user.service.dto;

public record User(
        Integer id,
        String username,
        String email,
        String name
) {}
