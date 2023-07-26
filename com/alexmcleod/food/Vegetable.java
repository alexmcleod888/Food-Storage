package com.alexmcleod.food;

public class Vegetable extends Food{

    private double weight;
    
    public Vegetable(String newName, double newStorageTemp, String newExpiryDate, 
                     String newPackaging, double newWeight) {
        super(newName, newStorageTemp, newExpiryDate, newPackaging);
        foodCategory = "Vegetable";
        if(newWeight >= 0.2 && newWeight <= 5.0) {
            weight = newWeight;    
        }
        else {
            throw new IllegalArgumentException("Error: Weight must be between 0.2 and 5.0");
        }        
    }



    public double getWeight() {
        return weight;
    }

    public String toStringLabelled() {
        String outputString = new String(super.toStringLabelled() + 
                                         "Weight: " + weight);
        return outputString;
    }

    public String toString() {
        String outputString = new String(super.toString() + 
                                         weight + ", " +
                                         storageTemp + ", " +
                                         expiryDate + ", " + 
                                         packaging);
        return outputString;
    }
    
    public void setWeight(double newWeight) {
        if(newWeight >= 0.2 && newWeight <= 5.0) {
            weight = newWeight;
        }
        else {
            throw new IllegalArgumentException("Error: Weight must be between 0.2 and 5.0");
        }
        
    }
}
