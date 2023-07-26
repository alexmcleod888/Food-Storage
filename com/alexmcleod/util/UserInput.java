//purpose: a functional interface used for the InputValidations class enforcing the lamda expressions in 
//         this class to input and output strings. We can then reference all of the 
//         the different InputValidation methods as UserInput objects as they all implement this class.
//         This functionality is used within the tryCatch method of the UserInputOutput class allowing
//         us to check different validations within the same try catch block reducing repition.

package com.alexmcleod.util;

@FunctionalInterface
public interface UserInput {

    String validateInput(String input);
}
