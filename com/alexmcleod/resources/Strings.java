//purpose: Strings used throughout the program
package com.alexmcleod.resources;

public class Strings {
    public static final String MESSAGE_1 = "Enter a number to select your food type: \n1. Meat\n2. Grain\n3. Fruit\n4. Vegetable";
    public static final String MESSAGE_2 = "Enter the name of the food: ";
    public static final String MESSAGE_3 = "\033[3mpantry = 8.0 to 25.0\nfridge = -2.0 to 6.0\nfreezer = -27.0 to -5.0\033[0m\nEnter a number for the the storage temp:";
    public static final String MESSAGE_4 = "Enter the useby or best before date (cant be in the past): ";
    public static final String MESSAGE_5 = "Enter the packaging used: ";
    
    public static final String MESSAGE_6 = "Enter the type of cut: ";
    public static final String MESSAGE_7 = "Enter the weight in kg's (number between 0.2 and 5.0): ";
    public static final String MESSAGE_8 = "Enter the type: ";
    public static final String MESSAGE_9 = "Enter the volume in litres (number between 0.2 and 5.0): ";
    public static final String MESSAGE_10 = "Enter the number of pieces (number between 1 and 20): ";

    public static final String REAL_REGEX = "^[-+]?[0-9]*\\.?[0-9]+$";
    public static final String INTEGER_REGEX = "^([1-9]|[1-9][0-9]|[1-9][0-9][0-9])$";
    public static final String DATE_REGEX = "^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d$";

    public static final String MENU_SELECTION = "\n1. Add Food\n2. Remove Food\n3. Display Contents\n4. Find Expired\n5. Read in Storage\n6. Read Out Storage\n7. Exit";
    public static final String STORAGE_SELECTION = "Choose a storage location:\n1. Pantry\n2. Fridge\n3. Freezer";

    public static final String INTEGER_ERROR = "Error: input must be a positive integer value (up to 3 digits)";
    public static final String REAL_ERROR = "Error: input must be real value";
    public static final String FOODTYPE_ERROR = "Error: input must be a number between 1 and 4";
    public static final String STRING_ERROR = "Error: input cant be a number";
    public static final String DATE_ERROR = "Error: input must be in format dd/mm/yyyy";
    public static final String STORAGE_SELECT_ERROR = "Error: input must be between 1 and 3";
}
