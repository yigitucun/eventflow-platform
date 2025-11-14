package io.yigitucun.eventflow.user.service.annotation.abstracts;

import io.yigitucun.eventflow.user.service.annotation.concretes.UniqueUsernameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueUsernameValidator.class)
public @interface UniqueUsername {
    String message() default "Username is already taken";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
