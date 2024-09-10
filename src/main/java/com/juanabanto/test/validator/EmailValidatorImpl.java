package com.juanabanto.test.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class EmailValidatorImpl implements ConstraintValidator<EmailValidator, String> {

    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";

    @Override
    public void initialize(EmailValidator email) {}

    @Override
    public boolean isValid(String emailField, ConstraintValidatorContext cxt) {
        if (emailField == null || !Pattern.matches(EMAIL_PATTERN, emailField)) {
            cxt.buildConstraintViolationWithTemplate("Email no válido")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            return false;
        }
        return true;
    }
}
