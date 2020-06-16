package com.dev.cinema.annotation;

import com.dev.cinema.model.dto.request.UserRegistrationDto;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements
        ConstraintValidator<PasswordValueMatch, UserRegistrationDto> {

    @Override
    public boolean isValid(UserRegistrationDto userRegistrationDto,
                           ConstraintValidatorContext constraintValidatorContext) {
        return userRegistrationDto.getPassword() != null
                && userRegistrationDto.getRepeatPassword() != null
                && userRegistrationDto.getPassword()
                .equals(userRegistrationDto.getRepeatPassword());
    }
}
