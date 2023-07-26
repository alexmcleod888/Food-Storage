package com.alexmcleod.util;

import com.alexmcleod.resources.Strings;

//purpose: contains lambda express for input validations
public class InputValidations {

    public static final UserInput realValidation = (input) -> {     
        if(!input.matches(Strings.REAL_REGEX)) {
            throw new IllegalArgumentException(Strings.REAL_ERROR);
        }
        return input; 
    };

    public static final UserInput intValidation = (input) -> {
        if(!input.matches(Strings.INTEGER_REGEX)) {
            throw new IllegalArgumentException(Strings.INTEGER_ERROR);
        }
        return input;
    };

    public static final UserInput stringValidation = (input) -> { 
        if(input.matches(Strings.REAL_REGEX) || input.matches(Strings.INTEGER_REGEX)) {
            throw new IllegalArgumentException(Strings.STRING_ERROR);
        }
        return input;
    };

    public static final UserInput dateValidation = (input) -> {
        if(!input.matches(Strings.DATE_REGEX)) {
            throw new IllegalArgumentException(Strings.DATE_ERROR);
        }
        return input;
    };
}
