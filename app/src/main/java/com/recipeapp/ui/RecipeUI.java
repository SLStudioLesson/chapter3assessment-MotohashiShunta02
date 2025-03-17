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
                        System.out.println();
                        addNewRecipe();
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

    private void displayRecipes() throws IOException {
        ArrayList<Recipe> dispplay = dataHandler.readData();// レシピデータ

        if (dispplay == null) {
            System.out.println("No recipes available.");
        } else {
            if (dispplay.isEmpty()) {// 空なら表示
                System.out.println("No recipes available.");
            } else {// 表示
                System.out.println("Recipes:");
                System.out.println("-----------------------------------");

                for (Recipe dispplays : dispplay) {// レシピデータの拡張for文で表示
                    System.out.println("Recipe Name: " + dispplays.getName());// レシピ名の表示
                    System.out.print("Main Ingredients:");
                    ArrayList<Ingredient> ingredients = dispplays.getIngredients();
                    int c = 0;// カウント変数
                    for (Ingredient ingredientsRecipe : ingredients) {

                        if (c == ingredients.size() - 1) {// カウント変数とサイズのラストと同じか比べて,を変化を出す
                            System.out.print(" " + ingredientsRecipe.getName());
                        } else {
                            System.out.print(" " + ingredientsRecipe.getName() + ",");
                            c++;
                        }

                    }

                    // for (int i = 0; i < ingredients.size(); i++) {
                    // System.out.print(ingredients.get(i).getName());
                    // if (i < ingredients.size() - 1) {
                    // System.out.print(",");
                    // }
                    // }

                    System.out.println();
                    System.out.println("-----------------------------------");
                }
            }
        }
    }

    private void addNewRecipe() throws IOException {// 書き込み
        System.out.println("Adding a new recipe.");
        System.out.print("Enter recipe name: ");

        String name = reader.readLine();// 名前の書き込み

        System.out.println("Enter ingredients (type 'done' when finished):");

        ArrayList<Ingredient> ingredients = new ArrayList<>();// 材料を格納する配列を用意
        while (true) {// doneまで入力する
            System.out.print("Ingredient: ");
            String ingredientsName = reader.readLine();
            if (ingredientsName.equals("done")) {
                break;// 入力されたらブレイク
            } else {
                ingredients.add(new Ingredient(ingredientsName));// Listに格納
                continue;
            }
        }

        System.out.println("Recipe added successfully.");

        Recipe recipe = new Recipe(name, ingredients);// Recipe型に入れる

        dataHandler.writeData(recipe);// メソッドを読みだす

    }
}
