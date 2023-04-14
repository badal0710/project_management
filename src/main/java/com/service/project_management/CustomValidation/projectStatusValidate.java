package com.service.project_management.CustomValidation;

import com.service.project_management.Entities.Project;
import com.service.project_management.dto.ProjectDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class projectStatusValidate implements ConstraintValidator<ValidateProjectStatus, Integer> {


    List<Integer> proStatus = Arrays.asList(10, 20, 30, 40, 50, 60, 70, 80, 90, 100);

    @Override
    public void initialize(ValidateProjectStatus constraintAnnotation) {
//        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer projectStatus, ConstraintValidatorContext constraintValidatorContext) {
        if (proStatus.contains(projectStatus)) {
            return true;
        }
        return false;
    }
}
