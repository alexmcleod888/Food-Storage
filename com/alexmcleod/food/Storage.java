//purpose: store class containing arrays for different storage locations and methods for their
//         functionality

package com.alexmcleod.food;

public class Storage {
    Food[] pantry;
    Food[] fridge;
    Food[] freezer;
    int numItemsPantry;
    int numItemsFridge;
    int numItemsFreezer;

    //constructor
    public Storage() {
        pantry = null;
        fridge = null;
        freezer = null;
        numItemsPantry = 0;
        numItemsFridge = 0;
        numItemsFreezer = 0;

    }

    //purpose: for adding a new food to storage
    public void addFood(Food newFood) {
        
        if(newFood.getStorageArea().equals("pantry")) {
            if(numItemsPantry + 1 <= pantry.length) {
                pantry[numItemsPantry] = newFood;
                numItemsPantry = numItemsPantry + 1;
                System.out.println(newFood.getName() + " added to the Pantry");
            }
            else {
                throw new IllegalArgumentException("Error: Pantry storage is full at " + numItemsPantry + " items");
            }    
        }
        else if(newFood.getStorageArea().equals("fridge")) {
            
            if(numItemsFridge + 1 <= fridge.length) {
                fridge[numItemsFridge] = newFood;
                numItemsFridge = numItemsFridge + 1;
                System.out.println(newFood.getName() + " added to the Fridge");
            }
            else {
                throw new IllegalArgumentException("Error: Fridge storage is full at " + numItemsFridge + " items");
            }
                
        }
        else if(newFood.getStorageArea().equals("freezer")) {
            if(numItemsFreezer + 1 <= freezer.length) {
                freezer[numItemsFreezer] = newFood;
                numItemsFreezer = numItemsFreezer + 1;
                System.out.println(newFood.getName() + " added to the Freezer");
            }
            else {
                throw new IllegalArgumentException("Error: Freezer storage is full at " + numItemsFreezer + " items");
            }         
        }
    }

    //purpose: removes a item from given storage location and index number
    private String arrayRemove(Food[] storageLocation, int numItems, int foodNum) {

        String itemRemoved = new String("");

        if(numItems - 1 == 0) {
            itemRemoved = storageLocation[0].getName();
            storageLocation[0] = null;
        }
        else {
            Food[] tempArray1;
            tempArray1 = new Food[numItems - 1];

            //add all items to a new array except the one to remove
            int newIndex = 0;
            for(int i = 0; i < numItems; i++) {
                if(i != (foodNum - 1)) {
                    tempArray1[newIndex] = storageLocation[i];
                    newIndex = newIndex + 1;
                }
                else{ 
                    itemRemoved = storageLocation[i].getName();
                }
            }
            //overwrite each element in the array and set the last element to be null
            for(int i = 0; i < numItems - 1; i++) {
                storageLocation[i] = tempArray1[i];
            }
            storageLocation[numItems - 1] = null;
        }
        return itemRemoved;
    }

    //purpose: for removing an item from storage
    public void removeFood(int storageNum, int foodNum) {
        String itemRemoved;

        if(storageNum == 1) {
            itemRemoved =arrayRemove(pantry, numItemsPantry, foodNum);
            numItemsPantry = numItemsPantry - 1;
            System.out.println("ALERT: " + itemRemoved + " removed from pantry");  
        }
        else if (storageNum == 2) {
            itemRemoved = arrayRemove(fridge, numItemsFridge, foodNum);
            numItemsFridge = numItemsFridge - 1;
            System.out.println("ALERT: " + itemRemoved + " removed from fridge");  
        }
        else if (storageNum == 3) {
            itemRemoved = arrayRemove(freezer, numItemsFreezer, foodNum);
            numItemsFreezer = numItemsFreezer - 1;
            System.out.println("ALERT: " + itemRemoved + " removed from freezer");
        }
    }

    //purpose: for finding any expired food
    public void findExpired() {
        int numExpiredItems = 0;

        System.out.println("EXPIRED FOOD:");
        System.out.println("Pantry:");
        for(int i = 0; i < numItemsPantry; i++) {
            if(pantry[i].calcExpiry()){
                System.out.println(pantry[i].toStringLabelled());
                numExpiredItems += 1;
            }
        }
        if(numExpiredItems == 0) {
            System.out.println("*No expired items*");
        }
        numExpiredItems = 0;
        System.out.println("Fridge:");
        for(int i = 0; i < numItemsFridge; i++) {
            if(fridge[i].calcExpiry()) {
                System.out.println(fridge[i].toStringLabelled());
                numExpiredItems += 1;
            }     
        }
        if(numExpiredItems == 0) {
            System.out.println("*No expired items*");
        }
        numExpiredItems = 0;
        System.out.println("Freezer:");
        for(int i = 0; i < numItemsFreezer; i++) {
            if(freezer[i].calcExpiry()) {
                System.out.println(freezer[i].toStringLabelled());
                numExpiredItems += 1;
            }
        }
        if(numExpiredItems == 0) {
            System.out.println("*No expired items*");
        }
    }

    //purpose: for getting an output string for items in a particular storage location
    public String storageLocationToString(int storageLocationNum) {
        String outputString = new String("");

        if(storageLocationNum == 1 && numItemsPantry != 0) {
            outputString = pantryToString();
        }
        else if (storageLocationNum == 2 && numItemsFridge != 0) {
            outputString = fridgeToString();
        } 
        else if (storageLocationNum == 3 && numItemsFreezer != 0) {
            outputString = freezerToString();
        }
        else {
            throw new IllegalArgumentException("Error: Selected storage area is empty!");
        }
        return outputString;
    }

    //setters
    public void setPantrySize(int newSize) {
        if(pantry == null){
            if(newSize < 0 || newSize <= 500){
                pantry = new Food[newSize];
            }
            else {
                throw new IllegalArgumentException("Error: pantry storage must be an integer from 1 to 500");
            }
        }
        else {
            throw new IllegalArgumentException("Error: pantry size has been set twice");
        }
    }

    public void setFridgeSize(int newSize) {
        if(fridge == null){
            if(newSize != 0 || newSize <= 500){
                fridge = new Food[newSize];
            }
            else {
                throw new IllegalArgumentException("Error: Fridge storage must be an integer from 1 to 500");
            }
        }
        else {
            throw new IllegalArgumentException("Error: fridge size has been set twice");
        }
    }

    public void setFreezerSize(int newSize) {
        if(freezer == null){
            if(newSize != 0 || newSize <= 500){
                freezer = new Food[newSize];
            }
            else {
                throw new IllegalArgumentException("Error: freezer storage must be an integer from 1 to 500");
            }
        }
        else {
            throw new IllegalArgumentException("Error: freezer size has been set twice");
        }
    }


    //getters
    public Food[] getPantry() {
        return pantry;
    }

    public Food[] getFridge() {
        return fridge;
    }

    public Food[] getFreezer() {
        return freezer;
    }

    public int getNumItemsPantry() {
        return numItemsPantry;
    }

    public int getNumItemsFridge() {
        return numItemsFridge;
    }

    public int getNumItemsFreezer() {
        return numItemsFreezer;
    }


    //purpose: for checking if storage area's are empty
    public boolean pantryCheckEmpty() {
        boolean isEmpty = false;
        if (numItemsPantry == 0) {
            isEmpty = true;
        }
        return isEmpty;
    }

    public boolean fridgeCheckEmpty() {
        boolean isEmpty = false;
        if (numItemsFridge == 0) {
            isEmpty = true;
        }
        return isEmpty;
    }

    public boolean freezerCheckEmpty() {
        boolean isEmpty = false;
        if (numItemsFreezer == 0) {
            isEmpty = true;
        }
        return isEmpty;
    }

    //toStrings
    public String pantryToString() {

        String pantryString = new String("pantry" + " contents:");
        for(int i = 0; i < numItemsPantry; i++) { 
            String newString = (i + 1) + ". " + pantry[i].toStringLabelled();
            pantryString = pantryString + "\n" + newString;
        }
        return pantryString;
    }    

    public String fridgeToString() {

        String fridgeString = new String("fridge" + " contents:");
        for(int i = 0; i < numItemsFridge; i++) { 
            String newString = (i + 1) + ". " + fridge[i].toStringLabelled();
            fridgeString = fridgeString + "\n" + newString;
        }
        return fridgeString;
    }

    public String freezerToString() {

        String freezerString = new String("freezer" + " contents:");
        for(int i = 0; i < numItemsFreezer; i++) { 
            String newString = (i + 1) + ". " + freezer[i].toStringLabelled();
            freezerString = freezerString + "\n" + newString;
        }
        return freezerString;
    }
}

