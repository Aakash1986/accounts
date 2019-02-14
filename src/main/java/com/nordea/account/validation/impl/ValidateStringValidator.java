package com.nordea.account.validation.impl;

import com.nordea.account.validation.ValidateStringValue;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ValidateStringValidator implements ConstraintValidator<ValidateStringValue, String> {

    private List<String> valueList;

    public void initialize(ValidateStringValue validate) {
        valueList = new ArrayList<String>();
        for(String val : validate.acceptedValues()) {
            valueList.add(val.toUpperCase());
        }
    }

    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if(!valueList.contains(value.toUpperCase())) {
            return false;
        }
        return true;
    }
}
