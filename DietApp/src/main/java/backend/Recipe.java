/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

/**
 *
 * @author sebastiankotarski
 */
public class Recipe {

    String title;
    Integer calories;
    Integer carbohydrates;
    Integer proteins;
    Integer fat;
    String url;
    RecipeType recipeType;

    public Recipe(String title, Integer calories, Integer carbohydrates, Integer proteins, Integer fat, String url, RecipeType recipeType) {
        this.title = title;
        this.calories = calories;
        this.carbohydrates = carbohydrates;
        this.proteins = proteins;
        this.fat = fat;
        this.url = url;
        this.recipeType = recipeType;
    }

    public String getTitle() {
        return title;
    }

    public Integer getCalories() {
        return calories;
    }

    public Integer getCarbohydrates() {
        return carbohydrates;
    }

    public Integer getProteins() {
        return proteins;
    }

    public Integer getFat() {
        return fat;
    }

    public String getUrl() {
        return url;
    }

    public RecipeType getRecipeType() {
        return recipeType;
    }

}
