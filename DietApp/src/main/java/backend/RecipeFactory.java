/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author sebastiankotarski
 */
public class RecipeFactory {

    public static List<Recipe> getRecipesWithType(final RecipeType recipeType) throws FileNotFoundException {
        Recipe[] recipes;
        JsonDeserializer<Recipe> deserializer = new Recipe();
        Gson gson = new GsonBuilder().registerTypeAdapter(Recipe.class, deserializer).create();
        String filename = null;
        switch (recipeType) {
            case breakfast:
                filename = "breakfast";
                break;
            case dinner:
                filename = "dinner";
                break;
            case supper:
                filename = "supper";
                break;
        }
        FileReader fileReader = new FileReader("./DietApp/src/main/resources/" + filename + ".json");
        recipes = gson.fromJson(fileReader, Recipe[].class);
        List<Recipe> recipeList = Arrays.asList(recipes);
        recipeList.forEach((recipe) -> recipe.recipeType = recipeType);
        return recipeList;
    }
}
