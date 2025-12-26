package io.yigitucun.eventflow.event.service.annotation.abstracts;


import io.yigitucun.eventflow.event.service.annotation.concretes.NoEventOverlapValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NoEventOverlapValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface NoEventOverlap {
    String message() default "Bu saat aralığında salon dolu.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
