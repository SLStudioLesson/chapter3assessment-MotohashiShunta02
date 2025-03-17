package com.recipeapp.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.recipeapp.datahandler.DataHandler;
import com.recipeapp.model.Ingredient;
import com.recipeapp.model.Recipe;

public class RecipeUI {
    private BufferedReader reader;
    private DataHandler dataHandler;

    public RecipeUI(DataHandler dataHandler) {
        reader = new BufferedReader(new InputStreamReader(System.in));
        this.dataHandler = dataHandler;
    }

    public void displayMenu() {

        System.out.println("Current mode: " + dataHandler.getMode());

        while (true) {
            try {
                System.out.println();
                System.out.println("Main Menu:");
                System.out.println("1: Display Recipes");
                System.out.println("2: Add New Recipe");
                System.out.println("3: Search Recipe");
                System.out.println("4: Exit Application");
                System.out.print("Please choose an option: ");

                String choice = reader.readLine();

                switch (choice) {
                    case "1":
                        System.out.println();
                        displayRecipes();
                        break;
                    case "2":
                        break;
                    case "3":
                        break;
                    case "4":
                        System.out.println("Exiting the application.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Error reading input from user: " + e.getMessage());
            }
        }
    }

    private void displayRecipes() {
        ArrayList<Recipe> dispplay = dataHandler.readData();

        if (dispplay == null) {
            System.out.println("No recipes available.");
        } else {
            if (dispplay.isEmpty()) {
                System.out.println("No recipes available.");
            } else {
                System.out.println("Recipes:");
                System.out.println("-----------------------------------");

                for (Recipe dispplays : dispplay) {
                    System.out.println("Recipe Name: " + dispplays.getName());
                    System.out.println("Main Ingredients: ");
                    for(Ingredient ingredientsRecipe : dispplays.getIngredients()){
                        System.out.print(ingredientsRecipe.getName());
                        System.out.println();
                    }

                    System.out.println("-----------------------------------");
                }
            }
        }
    }
}
