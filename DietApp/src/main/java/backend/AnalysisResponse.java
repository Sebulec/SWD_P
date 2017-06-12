package backend;

/**
 * Created by sebastiankotarski on 12.06.2017.
 */
public class AnalysisResponse extends DietEntity {
    ActivityType activityType;
    RecipeType recipeType;

    public AnalysisResponse(ActivityType activityType, RecipeType recipeType) {
        this.activityType = activityType;
        this.recipeType = recipeType;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public RecipeType getRecipeType() {
        return recipeType;
    }

    public AnalysisResponse() {
    }

}
