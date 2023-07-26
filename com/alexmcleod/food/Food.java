package com.alexmcleod.food;

import com.alexmcleod.util.*;

public abstract class Food {
    protected String name;
    protected String storageArea;
    protected double storageTemp; 
    protected DateClass expiryDate;
    protected String packaging;
    protected String foodCategory;
   
    /*need to check expiry */
    public Food(String newName, double newTemp, 
                String newExpiryDate, String newPackaging) {

        name = stringValidation(newName, "name");
        storageArea = checkStorageLocation(newTemp);
        storageTemp = newTemp;
        expiryDate = new DateClass(newExpiryDate);
        packaging = stringValidation(newPackaging, "packaging");
    }

    public String getName() {
        return name;
    }

    public String getStorageArea() {
        return storageArea;
    }

    public double getStorageTemp() {
        return storageTemp;
    }

    public DateClass getExpiryDate() {
        return expiryDate;
    }

    public String getPackaging() {
        return packaging;
    }

    public String getFoodCategory() {
        return foodCategory;
    }

    public String toStringLabelled() {
        String outputString = new String("Category: " + foodCategory + ", " +
                                         "Name: " + name + ", " + 
                                         "Storage Temperature: " + storageTemp + ", " + 
                                         "ExpiryDate: " + expiryDate + ", " + 
                                         "Packaging: " + packaging + ", ");
        return outputString;
    }

    public String toString() {
        String foodString = new String(foodCategory + ", " +
                                       name + ", ");
        return foodString;
    }

    public void setName(String newName) {
        name = stringValidation(newName, "name");
    }

    public void setStorageAreaAndTemp(double newTemp) {
        storageArea = checkStorageLocation(newTemp);
        storageTemp = newTemp;
    }

    public void setExpiryDate(String newExpiryDate) {
        expiryDate.setDate(newExpiryDate);
    }

    public void setPackaging(String newPackaging) {
        packaging = stringValidation(newPackaging, "packaging");
    }

    private String checkStorageLocation(double newTemp){
        String whereToStore;

        if (newTemp > -27.0 && newTemp < -5.0) {
            whereToStore = "freezer";
        } 
        else if (newTemp > -2.0 && newTemp < 6.0) {
            whereToStore = "fridge";
        }  
        else if (newTemp > 8.0 && newTemp < 25.0) {
            whereToStore = "pantry";
        }
        else {
            throw new IllegalArgumentException("Error: storage temp is invalid");
        }
        return whereToStore;
    }

    public boolean calcExpiry()
    {
        return expiryDate.isInThePast();
    }

    protected String stringValidation(String myString, String field) {
        
        if(myString.length() > 25) {
            throw new IllegalArgumentException("Error: " + field + " can't be more than 25 characters");
        }    
        else if(myString.trim().isEmpty()) {
            throw new IllegalArgumentException("Error: " + field + " can't be blank");
        }
        return myString;
    }
}
