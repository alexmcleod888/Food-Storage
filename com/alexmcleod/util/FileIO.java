package com.alexmcleod.util;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import com.alexmcleod.food.Storage;
import java.util.StringTokenizer;
import com.alexmcleod.food.*;

public class FileIO {

    public static void validateStorageFileFormat(String filename) throws Exception {
        FileInputStream fileStrm = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;
        int lineNum;

        lineNum = 1;

        try {
            fileStrm = new FileInputStream(filename);
            rdr = new InputStreamReader(fileStrm); 
            bufRdr = new BufferedReader(rdr); 
            validateStorageFormat(bufRdr.readLine(), lineNum);
            lineNum = lineNum + 1;
            validateStorageFormat(bufRdr.readLine(), lineNum);
            lineNum = lineNum + 1;
            validateStorageFormat(bufRdr.readLine(), lineNum);
            fileStrm.close(); 
        }
        catch (IOException e) { //catches any errors if file cant be opened
            if (fileStrm != null) { //clean up the stream if it was opened
                try { fileStrm.close(); } catch (IOException ex2) { } // We can’t do anything more!
            }
            //error must be thrown to skip any further processing of the file
            throw new IOException("Error: File cannot be found. please ensure file is placed in the resources file");
        }  
        catch(Exception e) { //catches any errors in the formatting of the file
            if (fileStrm != null) { //clean up the stream if it was opened
                try { fileStrm.close(); } catch (IOException ex2) { } // We can’t do anything more!
            }
            //error must be thrown to skip any further processing of the file
            throw new Exception(e.getMessage());
        }  
    }
    
    public static boolean readStorage(String filename, Storage storage, boolean fileRead) {
        FileInputStream fileStrm = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;
        String line1, line2, line3; 
        String line4 = new String("");

        int lineNum = 4;

        try {
            fileStrm = new FileInputStream(filename);
            rdr = new InputStreamReader(fileStrm); 
            bufRdr = new BufferedReader(rdr); 
            line1 = bufRdr.readLine(); 
            line2 = bufRdr.readLine();
            line3 = bufRdr.readLine();
            processStorage(line1, storage);
            processStorage(line2, storage);
            processStorage(line3, storage);
            line4 = bufRdr.readLine();
            if(line4 == null){
                System.out.println("*No food to be stored*");
            }
            else {
                while(line4 != null) {
                    processFood(line4, lineNum, storage); 
                    lineNum = lineNum + 1;
                    line4 = bufRdr.readLine();
                }
            }
            fileStrm.close(); 
            fileRead = true;
        }
        catch (Exception e) { //catches any errors in token types and values
            if (fileStrm != null) { //clean up the stream if it was opened
                try { fileStrm.close(); } catch (IOException ex2) { } // We can’t do anything more!
            }
            System.out.println(e.getMessage());
        }
        return fileRead;
    }

    private static void processStorage(String fileLine, Storage storage) throws Exception {
        String thisToken = null;
        StringTokenizer strTok;
        int pantrySize, fridgeSize, freezerSize;

        strTok = new StringTokenizer(fileLine, ",");  
        thisToken = strTok.nextToken();
        if((thisToken.trim()).equals("Pantry")) {
            pantrySize = Integer.parseInt(strTok.nextToken().trim());
            storage.setPantrySize(pantrySize);     
        }
        else if((thisToken.trim()).equals("Fridge")) {
            fridgeSize = Integer.parseInt(strTok.nextToken().trim());
            storage.setFridgeSize(fridgeSize);
        }
        else if((thisToken.trim()).equals("Freezer")) {
            freezerSize = Integer.parseInt(strTok.nextToken().trim());
            storage.setFreezerSize(freezerSize);
        }
    }

    private static void processFood(String fileLine, int lineNum, Storage storage) {
        String thisToken = null;
        StringTokenizer strTok;
        Food newFood;
        String name, packaging, expiryDate;
        double storageTemp; 

        try {
            validateFoodFormat(fileLine, lineNum);
            strTok = new StringTokenizer(fileLine, ","); 
            thisToken = strTok.nextToken();
            if((thisToken).trim().equals("Meat")) {
                String cut;
                double weight;
            
                name = strTok.nextToken().trim();
                cut = strTok.nextToken().trim();
                weight = Double.parseDouble(strTok.nextToken().trim());
                storageTemp = Double.parseDouble(strTok.nextToken().trim());
                expiryDate = strTok.nextToken().trim();
                packaging = strTok.nextToken().trim();
                newFood = new Meat(name, storageTemp, expiryDate, packaging, cut, weight);

            }
            else if((thisToken).trim().equals("Grain")) {
                String type;
                double volume;

                name = strTok.nextToken().trim();
                type = strTok.nextToken().trim();
                volume = Double.parseDouble(strTok.nextToken().trim());
                storageTemp = Double.parseDouble(strTok.nextToken().trim());
                expiryDate = strTok.nextToken().trim();
                packaging = strTok.nextToken().trim();
                newFood = new Grain(name, storageTemp, expiryDate, packaging, type, volume);

            }
            else if((thisToken).trim().equals("Fruit")) {
                String type;
                int numPieces;

                name = strTok.nextToken().trim();
                type = strTok.nextToken().trim();
                numPieces = Integer.parseInt(strTok.nextToken().trim());
                storageTemp = Double.parseDouble(strTok.nextToken().trim());
                expiryDate = strTok.nextToken().trim();
                packaging = strTok.nextToken().trim();
                newFood = new Fruit(name, storageTemp, expiryDate, packaging, type, numPieces);
            }
            else if((thisToken).trim().equals("Vegetable")) {
                double weight;

                name = strTok.nextToken().trim();
                weight = Double.parseDouble(strTok.nextToken().trim());
                storageTemp = Double.parseDouble(strTok.nextToken().trim());
                expiryDate = strTok.nextToken().trim();
                packaging = strTok.nextToken().trim();
                newFood = new Vegetable(name, storageTemp, expiryDate, packaging, weight);
            }
            else {
                throw new IllegalArgumentException("Error: invalid food item not processed");
            }
            storage.addFood(newFood);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void writeStorage(String filename, Storage storage) {
        FileOutputStream fileStrm = null;
        PrintWriter pw;

        try {
            Food[] pantry = storage.getPantry();
            Food[] fridge = storage.getFridge();
            Food[] freezer = storage.getFreezer();

            fileStrm = new FileOutputStream(filename); 
            pw = new PrintWriter(fileStrm); 
            pw.println("Pantry, " + pantry.length);
            pw.println("Freezer, " + freezer.length);
            pw.println("Fridge, " + fridge.length);         
            printStorageLocationItems(pw, pantry, storage.getNumItemsPantry());
            printStorageLocationItems(pw, fridge, storage.getNumItemsFridge());
            printStorageLocationItems(pw, freezer, storage.getNumItemsFreezer());
            pw.close(); 
            fileStrm.close();
        }
        catch (IOException e) { 
            if (fileStrm != null) { 
                try { fileStrm.close(); } catch (IOException ex2) { } // We can’t do anything more!
            }
            System.out.println("Error in writing to file: " + e.getMessage()); 
        }    
    }

    private static void printStorageLocationItems(PrintWriter pw, Food[] foodArray, int numItems) {
        for(int i = 0; i < numItems; i++) {
            Food food = foodArray[i];
            pw.println(food.toString());
        }
    }

    private static void validateStorageFormat (String line, int lineNum) throws Exception{
        StringTokenizer strTok;

        //create a new UserInput object that checks a string is an integer
        UserInput intValidate = InputValidations.intValidation;

        strTok = new StringTokenizer(line, ",");
        if(strTok.countTokens() != 2) {
            throw new Exception("Error: storage format error on line " + lineNum + "\n please refer to the file format instructions in the readme.txt file");
        }
        else {
            String storageString = strTok.nextToken();
            if ((storageString.trim()).equals("Pantry") 
             || (storageString.trim()).equals("Fridge")
             || (storageString.trim()).equals("Freezer")) {
                try {
                    intValidate.validateInput(strTok.nextToken().trim());
                }
                catch (Exception e) {
                    throw new Exception("Error on line " + lineNum + ": " + e.getMessage());
                }
            }
            else {
                throw new IllegalArgumentException("Format Error line " + lineNum + ": storage locations must be spelt Pantry, Fridge and Freezer on seperate lines");
            }
        }
    }

    private static void validateFoodFormat (String line, int lineNum) throws Exception {
        StringTokenizer strTok;
        String newToken;

        strTok = new StringTokenizer(line, ",");
        newToken = strTok.nextToken();
        if(newToken.equals("Meat")) {
            if(strTok.countTokens() != 6) {
                System.out.println(strTok.countTokens());
                throw new Exception("Error on line " + lineNum + ": Meat format Exception");
            }
        }
        else if(newToken.equals("Grain")) {
            if(strTok.countTokens() != 6) {
                throw new Exception("Error on line " + lineNum + ": Grain format Exception");
            }
        }
        else if(newToken.equals("Fruit")) {
            if(strTok.countTokens() != 6) {
                throw new Exception("Error on line " + lineNum + ": Fruit format Exception");
            }
        }
        else if(newToken.equals("Vegetable")) {
            if(strTok.countTokens() != 5) {
                throw new Exception("Error on line " + lineNum + ": Vegetable format Exception");
            }
        }
        else {
            throw new Exception("Error on line " + lineNum + ": incompatible food type");
        }
    }
}
