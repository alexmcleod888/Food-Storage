//purpose: A class for processing and outputting user input

package com.alexmcleod.util;

import com.alexmcleod.food.*;
import com.alexmcleod.resources.Strings;
import java.util.Scanner;

public class UserInputOutput {

    private static final Scanner scanner = new Scanner(System.in);

    //purpose: allows a user to add their own food item to storage
    public static Food foodUserInput() {

        int foodType, numPieces;
        double storageTemp, weight, volume;
        String foodName, expiryDate, packaging, cut, type;
        Food newFoodItem = null;

        
        foodType = Integer.parseInt(tryCatch(Strings.MESSAGE_1, InputValidations.intValidation));
        if(foodType < 1 || foodType > 4) {
            throw new IllegalArgumentException(Strings.FOODTYPE_ERROR);
        }
        foodName = tryCatch(Strings.MESSAGE_2, InputValidations.stringValidation);
        storageTemp = Double.parseDouble(tryCatch(Strings.MESSAGE_3, InputValidations.realValidation));
        expiryDate = tryCatch(Strings.MESSAGE_4, InputValidations.dateValidation);
        packaging = tryCatch(Strings.MESSAGE_5, InputValidations.stringValidation);     
        if(foodType == 1) { //If meat type
            cut = tryCatch(Strings.MESSAGE_6, InputValidations.stringValidation);
            weight = Double.parseDouble(tryCatch(Strings.MESSAGE_7, InputValidations.realValidation));
            newFoodItem = new Meat(foodName, storageTemp, expiryDate, packaging, cut, weight);
        }
        else if(foodType == 2) { //if grain type
            type = tryCatch(Strings.MESSAGE_8, InputValidations.stringValidation);
            volume = Double.parseDouble(tryCatch(Strings.MESSAGE_9, InputValidations.realValidation));
            newFoodItem = new Grain(foodName, storageTemp, expiryDate, packaging, type, volume);
        }
        else if(foodType == 3) { //if fruit type
            type = tryCatch(Strings.MESSAGE_8, InputValidations.stringValidation);
            numPieces = Integer.parseInt(tryCatch(Strings.MESSAGE_10, InputValidations.intValidation));
            newFoodItem = new Fruit(foodName, storageTemp, expiryDate, packaging, type, numPieces);
        }
        else if(foodType == 4) { //if vegetable type
            weight = Double.parseDouble(tryCatch(Strings.MESSAGE_7, InputValidations.realValidation));
            newFoodItem = new Vegetable(foodName, storageTemp, expiryDate, packaging, weight);
        }     
        return newFoodItem;        
    }

    //purpose: allows a user to choose an option from the menu
    public static int menuSelection() {
        int userInput;
        System.out.println(Strings.MENU_SELECTION);
        userInput = Integer.parseInt(scanner.nextLine());
        return userInput;
    }

    //purpose: allows a user to choose a storage location
    public static int chooseStorageLocation() {
        int storageNum;
        storageNum = Integer.parseInt(tryCatch(Strings.STORAGE_SELECTION, InputValidations.intValidation));
        if(storageNum < 1 || storageNum > 3) {
            throw new IllegalArgumentException(Strings.STORAGE_SELECT_ERROR);
        }
        return storageNum;
    }

    //purpose: allows a user to choose a storage location and then choose a food item from
    //         storage
    public static int chooseFood(Storage storage, int storageNum) {
        int selectionNum;
        String storageString;
        
        if(storageNum == 1 && !storage.pantryCheckEmpty()) {
            storageString = storage.pantryToString();
            selectionNum = foodSelect(storage.getPantry(), storage.getNumItemsPantry(), storageString);
        }
        else if(storageNum == 2 && !storage.fridgeCheckEmpty()) {
            storageString = storage.fridgeToString();
            selectionNum = foodSelect(storage.getFridge(), storage.getNumItemsFridge(), storageString);    
        }
        else if (storageNum == 3 && !storage.freezerCheckEmpty()) {
            storageString = storage.freezerToString();
            selectionNum = foodSelect(storage.getFreezer(), storage.getNumItemsFreezer(), storageString);
        }
        else {
            throw new IllegalArgumentException("Error: Selected storage area is empty!");
        }
        return selectionNum;
    }

    //purpose: allows a user to choose a food item from a list of food items
    public static int foodSelect(Food[] storageArray, int numItems, String storageString) {
        int itemSelection;
        String prompt;

        prompt = new String("Enter a number to remove an item from the storage location: ");
        storageString = prompt + storageString;
        itemSelection = Integer.parseInt(tryCatch(storageString, InputValidations.intValidation));
        if(itemSelection < 1 || itemSelection > numItems) {
            throw new IllegalArgumentException("input must be from 1 to " + numItems);
        }
        return itemSelection;
    }

    //purpose: allows a user to input a storage area name.
    public static String fileNameInput() {
        String fileName = tryCatch("Enter the name of a storage file to read: ", InputValidations.stringValidation);
        return fileName;
    }

    //purpose: a try catch block that surrounds a particular input validation method(the inputObj)
    public static String tryCatch(String outputMessage, UserInput inputObj) {
        boolean error;
        String userInput;

        userInput = "";
        do {
            try {
                error = false;
                System.out.println(outputMessage);
                String input = scanner.nextLine();
                userInput = inputObj.validateInput(input);    
            } 
            catch (IllegalArgumentException e) {
                error = true;
                System.out.println(e.getMessage());
            }
            catch (Exception e) {
                error = true;
            }
        } while(error == true);
        return userInput;
    }

     

    //purpose: method for closing the scanner of a file
    public static void closeScanner() {
        scanner.close();
    }
}
