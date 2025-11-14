package io.yigitucun.eventflow.user.service.annotation.concretes;

import io.yigitucun.eventflow.user.service.annotation.abstracts.UniqueEmail;
import io.yigitucun.eventflow.user.service.mapper.UserMapper;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail,String> {
    private final UserMapper userMapper;

    public UniqueEmailValidator(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return !userMapper.existsByEmail(email);
    }
}
