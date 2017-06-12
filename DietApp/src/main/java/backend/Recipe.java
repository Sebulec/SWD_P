/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import com.google.gson.*;

import java.lang.reflect.Type;

public class Recipe implements JsonDeserializer<Recipe> {

    String title;
    Double calories;
    Double carbohydrates;
    Double proteins;
    Double fat;
    String url;
    RecipeType recipeType;
    CaloriesLevel caloriesLevel;

    public Recipe(String title, Double calories, Double carbohydrates, Double proteins, Double fat, String url) {
        this.title = title;
        this.calories = calories;
        this.carbohydrates = carbohydrates;
        this.proteins = proteins;
        this.fat = fat;
        this.url = url;
    }

    @Override
    public Recipe deserialize(JsonElement json, Type type,
                              JsonDeserializationContext context) throws JsonParseException {
        JsonObject jobject = json.getAsJsonObject();
        Double calories = Double.parseDouble(jobject.get("calories").getAsString());
        return new Recipe(jobject.get("title").getAsString(),
                calories,
                jobject.get("totalCarbs").getAsDouble(),
                jobject.get("protein").getAsDouble(),
                jobject.get("totalFat").getAsDouble(),
                jobject.get("url").getAsString());
    }

    public Recipe() {

    }

    public String getTitle() {
        return title;
    }

    public Double getCalories() {
        return calories;
    }

    public Double getCarbohydrates() {
        return carbohydrates;
    }

    public Double getProteins() {
        return proteins;
    }

    public Double getFat() {
        return fat;
    }

    public String getUrl() {
        return url;
    }

    public RecipeType getRecipeType() {
        return recipeType;
    }

    public CaloriesLevel getCaloriesLevel() {
        if (this.getCalories() <= 100) {
            return CaloriesLevel.low;
        } else if (this.getCalories() > 250) {
            return CaloriesLevel.high;
        } else {
            return CaloriesLevel.normal;
        }
    }

    @Override
    public String toString() {
        return this.getTitle();
    }

}