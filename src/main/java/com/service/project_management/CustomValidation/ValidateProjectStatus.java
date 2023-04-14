package com.service.project_management.CustomValidation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.CONSTRUCTOR,ElementType.PARAMETER,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = projectStatusValidate.class)
public @interface ValidateProjectStatus {

    String message() default "Please enter valid status like 10,20,30..";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
