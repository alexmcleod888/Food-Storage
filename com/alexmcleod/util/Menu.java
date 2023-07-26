package com.alexmcleod.util;

import com.alexmcleod.food.*;

/**
 * Menu
 */
public class Menu {

    private static boolean fileRead;
    private static Storage storage;

    public static void run() {
        displayMenu();
    }

    private static void displayMenu() {
        boolean exit;
        int userInput = 0;
        storage = new Storage();
        
        exit = false;
        fileRead = false;
        System.out.println("*please read in storage first*");
        while(exit == false) {
            try {
                userInput = UserInputOutput.menuSelection();
    
                if(userInput == 1) {
                    addFood();
                }
                else if(userInput == 2) {
                    removeFood();
                }
                else if(userInput == 3) {
                    displayFood();
                }
                else if(userInput == 4) {
                    findExpired();
                }
                else if(userInput == 5) {
                    readStorage();    
                }
                else if(userInput == 6) {
                    writeStorage();
                }
                else if(userInput == 7) {
                    exit = true;
                }       
                else {
                    throw new IllegalArgumentException("Error: input must be from 1 to 7");
                }         
            }
            catch(Exception e) {
                System.out.println(e.getMessage());
            }
        }
        UserInputOutput.closeScanner();
    }

    private static void addFood() throws Exception {
        if(fileRead == true) {
            Food newFood = UserInputOutput.foodUserInput(); 
            storage.addFood(newFood);
        }
        else {
            throw new Exception("Error: file storage must be read");
        }
    }

    private static void removeFood() throws Exception {
        if(fileRead == true) {
            int storageNum, foodNum;
            storageNum = UserInputOutput.chooseStorageLocation();
            foodNum = UserInputOutput.chooseFood(storage, storageNum);
            storage.removeFood(storageNum, foodNum);
        }
        else {
            throw new Exception("Error: file storage must be read");
        }
    }

    private static void displayFood() throws Exception {
        if(fileRead == true) {
            int storageNum = UserInputOutput.chooseStorageLocation();
            String storageLocationString = storage.storageLocationToString(storageNum);
            System.out.println(storageLocationString);
        }
        else {
            throw new Exception("Error: file storage must be read");
        }
    }

    private static void findExpired() throws Exception {
        if(fileRead == true) {
            storage.findExpired();
        }
        else {
            throw new Exception("Error: file storage must be read");
        }
    }

    private static void readStorage() throws Exception {
        if(fileRead == false) {
            String foodStorageFile = UserInputOutput.fileNameInput();
            FileIO.validateStorageFileFormat("./com/alexmcleod/resources/" + foodStorageFile);
            fileRead = FileIO.readStorage("./com/alexmcleod/resources/" + foodStorageFile, storage, fileRead);
        }
        else {
            throw new Exception("Error: only one file can be read");
        }
    }

    private static void writeStorage() throws Exception {
        if(fileRead == true) {
            String foodStorageFile = UserInputOutput.fileNameInput();
            FileIO.writeStorage("./com/alexmcleod/resources/" + foodStorageFile, storage);
        }
        else {
            throw new Exception("Error: file storage must be read");
        }                
    }
}