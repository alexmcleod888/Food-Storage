package com.alexmcleod.food;

public class Fruit extends Food{
    private String type;
    private int numPieces;

    public Fruit(String newName, double newStorageTemp, String newExpiryDate, 
                 String newPackaging, String newType, int newNumPieces) {

        super(newName, newStorageTemp, newExpiryDate, newPackaging);
        foodCategory = "Fruit";
        if(newNumPieces >= 1 && newNumPieces <= 20) {
            type = stringValidation(newType, "type");
            numPieces = newNumPieces;
        }
        else {
            throw new IllegalArgumentException("Error: The number of pieces must be between 1 and 20");
        }
    }

    public String getType() {
        return type;
    }

    public int getNumPieces() {
        return numPieces;
    }

    public String toStringLabelled() {
        String outputString = new String(super.toStringLabelled() + 
                                         "Type: " + type + ", " + 
                                         "Number of Pieces: " + numPieces);
        return outputString;
    }

    public String toString() {
        String outputString = new String(super.toString() + 
                                         type + ", " + 
                                         numPieces + ", " +
                                         storageTemp + ", " +
                                         expiryDate.toString() + ", " +
                                         packaging);
        return outputString;
    }
    
    public void setType(String newType) {
        type = stringValidation(newType, "type");
    }

    public void setNumPieces(int newNumPieces) {
        if(newNumPieces >= 1 && newNumPieces <= 20) {
            numPieces = newNumPieces;
        }
        else {
            throw new IllegalArgumentException("Error: The number of pieces must be between 1 and 20");
        }    
    }

}
