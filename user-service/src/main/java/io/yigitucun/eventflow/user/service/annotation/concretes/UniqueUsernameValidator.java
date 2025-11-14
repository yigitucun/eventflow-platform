package io.yigitucun.eventflow.user.service.annotation.concretes;


import io.yigitucun.eventflow.user.service.annotation.abstracts.UniqueUsername;
import io.yigitucun.eventflow.user.service.mapper.UserMapper;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername,String> {

    private final UserMapper userMapper;

    public UniqueUsernameValidator(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        return !userMapper.existsByUsername(username);
    }
}
