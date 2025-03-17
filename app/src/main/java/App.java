import com.recipeapp.datahandler.CSVDataHandler;
import com.recipeapp.datahandler.DataHandler;
import com.recipeapp.datahandler.JSONDataHandler;
import com.recipeapp.ui.RecipeUI;
import java.io.*;

public class App {

    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Choose the file format:");
            System.out.println("1. CSV");
            System.out.println("2. JSON");
            System.out.print("Select (1/2): ");
            String choice = reader.readLine();

            if (choice.equals("1") || !choice.equals("2")) {// 1または2以外が入力された場合

                CSVDataHandler csvDataHandler = new CSVDataHandler();//インスタンス生成

                RecipeUI recipeUI = new RecipeUI(csvDataHandler);//引き渡し
                recipeUI.displayMenu();//実行

            } else if (choice.equals("2")) {
                System.out.println("Current mode: JSON");

                JSONDataHandler jsonDataHandler = new JSONDataHandler();//インスタンス生成
                RecipeUI jsRecipeUI = new RecipeUI(jsonDataHandler);//引き渡し
                jsRecipeUI.displayMenu();//実行
            }

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}