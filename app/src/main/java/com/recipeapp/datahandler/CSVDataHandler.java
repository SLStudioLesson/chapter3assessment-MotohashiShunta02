package com.recipeapp.datahandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.recipeapp.model.Recipe;

public class CSVDataHandler implements DataHandler {
    public String filePath;

    public CSVDataHandler() {// コンストラクタ
        this.filePath = "app/src/main/resources/recipes.csv";
    }

    public CSVDataHandler(String filePath) {
        this.filePath = filePath;
    }

    public String getMode() {
        return "CSV";
    }

    public ArrayList<Recipe> readData() {

        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            Recipe line;
            ArrayList<Recipe> recipesTxt = new ArrayList<>();
            ArrayList<String> recipesTxt2 = new ArrayList<>();
            while ((line = (Recipe)reader.readLine()) != null) {
                
                recipesTxt.add(line);
            }
            
            return recipesTxt;
        } catch (IOException e){
            System.out.println("Error reading file:" + e.getMessage());
        }
        return null;
    }



    public void writeData(Recipe recipe) {

    }

    public ArrayList<Recipe> searchData(String keyword) {
        return null;
    }

}
