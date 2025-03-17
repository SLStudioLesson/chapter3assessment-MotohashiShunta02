package com.recipeapp.datahandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            ArrayList<Recipe> recipesTxt = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                ArrayList<Ingredient> ingredients = new ArrayList<>();// for文の中で宣言することで毎回新しいものが生まれる
                String[] txt = line.split(",");// ,で分ける
                for (int i = 1; i < txt.length; i++) {// for文で挿入していく
                    ingredients.add(new Ingredient(txt[i]));// 配列に入れていく
                }
                Recipe recipe = new Recipe(txt[0], ingredients);// 名前と材料配列を入れていく
                recipesTxt.add(recipe);// 名前と材料を一緒になったものをListとして作る
            }
            return recipesTxt;
        } catch (IOException e) {
            System.out.println("Error reading file:" + e.getMessage());
        }
        return null;
    }

    public void writeData(Recipe recipe) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filePath, true)))) {
            //writer.newLine(recipe.getName())??
            int c = 0;//カウント変数
            out.println();//改行
            out.print(recipe.getName() + ",");//名前書き込み
            ArrayList<Ingredient> ingredients = recipe.getIngredients();//材料配列

            for (Ingredient ingredientsRecipe : ingredients) {//材料を拡張for文で書き込み
                if (c == ingredients.size() - 1) {//,の位置の調整
                    out.print(ingredientsRecipe.getName());
                } else {
                    out.print(ingredientsRecipe.getName() + ",");
                }
            }
        } catch (IOException e) {
            System.out.println("Failed to add new recipe: " + e.getMessage());
        }
    }

    public ArrayList<Recipe> searchData(String keyword) {
        return null;
    }

}
