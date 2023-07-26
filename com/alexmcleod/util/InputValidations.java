//purpose: contains lambda express for input validations. Allowing us to pass
//         functions as input

package com.alexmcleod.util;

import com.alexmcleod.resources.Strings;


public class InputValidations {

    //purpose: checks the input is a real value
    public static final UserInput realValidation = (input) -> {     
        if(!input.matches(Strings.REAL_REGEX)) {
            throw new IllegalArgumentException(Strings.REAL_ERROR);
        }
        return input; 
    };

    //purpose: checks the input is an integer value
    public static final UserInput intValidation = (input) -> {
        if(!input.matches(Strings.INTEGER_REGEX)) {
            throw new IllegalArgumentException(Strings.INTEGER_ERROR);
        }
        return input;
    };

    //purpose: checks the input is a String
    public static final UserInput stringValidation = (input) -> { 
        if(input.matches(Strings.REAL_REGEX) || input.matches(Strings.INTEGER_REGEX)) {
            throw new IllegalArgumentException(Strings.STRING_ERROR);
        }
        return input;
    };

    //purpose: checks the input is a Date
    public static final UserInput dateValidation = (input) -> {
        if(!input.matches(Strings.DATE_REGEX)) {
            throw new IllegalArgumentException(Strings.DATE_ERROR);
        }
        return input;
    };
}
