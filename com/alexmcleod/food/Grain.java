package com.alexmcleod.food;

public class Grain extends Food {
    private String type;
    private double volume;

    public Grain(String newName, double newStorageTemp, String newExpiryDate, 
                 String newPackaging, String newType, double newVolume) {

        super(newName, newStorageTemp, newExpiryDate, newPackaging);
        foodCategory = "Grain";
        if(newVolume >= 0.2 && newVolume <= 5.0) {
            type = stringValidation(newType, "type");
            volume = newVolume;
        }
        else {
            throw new IllegalArgumentException("Error: Volume must be between 0.2 and 5.0");
        }
    }

    public String getType() {
        return type;
    }

    public double getVolume() {
        return volume;
    }

    public String toStringLabelled() {
        String outputString = new String(super.toStringLabelled() + 
                                         "Type: " + type + ", " + 
                                         "Volume: " + volume);
        return outputString;
    }

    public String toString() {
        String outputString = new String(super.toString() + 
                                         type + ", " +
                                         volume + ", " +
                                         storageTemp + ", " + 
                                         expiryDate.toString() + ", " +
                                         packaging);
        return outputString;
    }

    public void setType(String newType) {
        type = stringValidation(newType, "type");
    }

    public void setVolume(double newVolume) {
        if(newVolume >= 0.2 && newVolume <= 5.0) {
            volume = newVolume;
        }
        else {
            throw new IllegalArgumentException("Error: Volume must be between 0.2 and 5.0");
        }    
    }
}
