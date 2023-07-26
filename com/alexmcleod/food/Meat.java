//purpose: a subclass of food which deals with Meat data and functionality

package com.alexmcleod.food;

public class Meat extends Food {
    private String cut;
    private double weight;


    //constructor
    public Meat(String newName, double newStorageTemp, String newExpiryDate, 
                String newPackaging, String newCut, double newWeight) {

        super(newName, newStorageTemp, newExpiryDate, newPackaging);
        foodCategory = "Meat";
        if(newWeight >= 0.2 && newWeight <= 5.0) {
            cut = stringValidation(newCut, "cut");
            weight = newWeight;    
        }
        else {
            throw new IllegalArgumentException("Error: Weight must be between 0.2 and 5.0");
        }
    }

    //getters
    public String getCut() {
        return cut;
    }

    public double getWeight() {
        return weight;
    }

    //toStrings
    public String toStringLabelled() {
        String outputString = new String(super.toStringLabelled() + 
                                         "Cut: " + cut + ", " + 
                                         "Weight: " + weight);
        return outputString;
    }

    
    public String toString() {
        String outputString = new String(super.toString() + 
                                         cut + ", " +
                                         weight + ", " +
                                         storageTemp + ", " +
                                         expiryDate.toString() + ", " +
                                         packaging);
        return outputString;
    }
    
    //setters
    public void setCut(String newCut) {
        cut = stringValidation(newCut, "cut");
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
