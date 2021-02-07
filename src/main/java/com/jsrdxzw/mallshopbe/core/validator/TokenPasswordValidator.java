package com.jsrdxzw.mallshopbe.core.validator;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author xuzhiwei
 * @date 2021-02-07
 */
public class TokenPasswordValidator implements ConstraintValidator<TokenPassword, String> {
    private int max;
    private int min;

    @Override
    public void initialize(TokenPassword constraintAnnotation) {
        this.max = constraintAnnotation.max();
        this.min = constraintAnnotation.min();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (!StringUtils.hasLength(s)) {
            return true;
        }
        return s.length() >= this.min && s.length() <= this.max;
    }
}
