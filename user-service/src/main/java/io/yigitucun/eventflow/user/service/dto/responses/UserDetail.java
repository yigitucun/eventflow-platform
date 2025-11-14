package io.yigitucun.eventflow.user.service.dto.responses;

import java.time.Instant;
import java.util.Date;

public record UserDetail(
    int id,
    String username,
    String name,
    String email,
    String password,
    String role,
    int role_id,
    Date created_at
) { }
