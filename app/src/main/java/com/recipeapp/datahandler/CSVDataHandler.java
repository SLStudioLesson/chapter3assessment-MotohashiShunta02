package com.recipeapp.datahandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.recipeapp.model.Ingredient;
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
            String line;
            ArrayList<Recipe> recipesTxt = new ArrayList<>();
            ArrayList<Ingredient> ingredients = new ArrayList<>();
            while ((line = reader.readLine()) != null) {

                String[] txt = line.split(",");
                for(int i=1; i<txt.length; i++){
                    Ingredient ingredient = new Ingredient(txt[i]);
                    ingredients.add(ingredient);
                }
                // Ingredient ingredient = new Ingredient(txt[1]);
                // ingredients.add(ingredient);
                Recipe recipe = new Recipe(txt[0], ingredients);
                recipesTxt.add(recipe);
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
