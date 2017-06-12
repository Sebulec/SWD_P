package backend;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by sebastiankotarski on 11.06.2017.
 */
public class DecisionMaker {

    public void makeDecision(User user, RecipeType recipeType, List<Recipe> recipes, CompletionHandler completionHandler) {
        LogicFunctions logicFunctions;
        Integer decisionIndex1 = 0;
        Integer decisionIndex2 = 0;
        Integer decisionIndex3 = 0;

        switch (recipeType) {
            case breakfast:
                decisionIndex2 = 4;
                break;
            case dinner:
                decisionIndex2 = 5;
                break;
            case supper:
                decisionIndex2 = 6;
                break;
        }
        switch (user.activityType) {
            case low:
                decisionIndex1 = 1;
                break;
            case normal:
                decisionIndex1 = 2;
                break;
            case high:
                decisionIndex1 = 3;
                break;
        }
        switch (user.bmi.weightType) {
            case underweight:
                decisionIndex3 = 1;
                break;
            case normal:
                decisionIndex3 = 2;
                break;
            case flesh:
                decisionIndex3 = 3;
                break;
        }

        logicFunctions = new LogicFunctions(decisionIndex1, decisionIndex2, decisionIndex3);
        HashSet<List<Boolean>> setY1 = new HashSet<>();
        HashSet<List<Boolean>> setY2 = new HashSet<>();
        while (logicFunctions.incrementAlphas()) {
            List<Boolean> alphasY = new ArrayList<>(logicFunctions.getAlphasY());
            if ((logicFunctions.functionF() && logicFunctions.functionY()) && logicFunctions.functionY()) {
                setY1.add(alphasY);
            } else if ((logicFunctions.functionF() && logicFunctions.functionY()) && !logicFunctions.functionY()) {
                setY2.add(alphasY);
            }
        }
        setY1.removeAll(setY2);
        Set<CaloriesLevel> caloriesLevels = new HashSet<>();
        setY1.stream().forEach((alphasY) -> {
            if (alphasY.get(0)) {
                caloriesLevels.add(CaloriesLevel.low);
            }
            if (alphasY.get(1)) {
                caloriesLevels.add(CaloriesLevel.normal);
            }
            if (alphasY.get(2)) {
                caloriesLevels.add(CaloriesLevel.high);
            }
        });
        completionHandler.completed(recipes.stream().filter((recipe -> caloriesLevels.contains(recipe.getCaloriesLevel()))).collect(Collectors.toList()));
    }

    public void makeAnalysis(User user, Recipe recipe, CompletionHandler completionHandler) {
        Set<AnalysisResponse> analysisResponses = new HashSet<>();
        LogicFunctions logicFunctions;

        Integer analysisIndex1 = 0;
        Integer internalDecisionIndex = 0;

        switch (recipe.getCaloriesLevel()) {
            case low:
                analysisIndex1 = 1;
                break;
            case normal:
                analysisIndex1 = 2;
                break;
            case high:
                analysisIndex1 = 3;
                break;
        }
        switch (user.bmi.weightType) {
            case underweight:
                internalDecisionIndex = 1;
                break;
            case normal:
                internalDecisionIndex = 2;
                break;
            case flesh:
                internalDecisionIndex = 3;
                break;
        }

        logicFunctions = new LogicFunctions(analysisIndex1, internalDecisionIndex);
        HashSet<List<Boolean>> setU1 = new HashSet<>();
        while (logicFunctions.incrementAlphas()) {
            List<Boolean> alphasU = new ArrayList<>(logicFunctions.getAlphasU());
            if (logicFunctions.functionU() && logicFunctions.functionF()) {
                setU1.add(alphasU);
            }
        }

        setU1.stream().forEach((alphasU) -> {
            AnalysisResponse analysisResponse = new AnalysisResponse();
            if (alphasU.get(0)) {
                analysisResponse.activityType = ActivityType.low;
            }
            if (alphasU.get(1)) {
                analysisResponse.activityType = ActivityType.normal;
            }
            if (alphasU.get(2)) {
                analysisResponse.activityType = ActivityType.high;
            }
            if (alphasU.get(3)) {
                analysisResponse.recipeType = RecipeType.breakfast;
            }
            if (alphasU.get(4)) {
                analysisResponse.recipeType = RecipeType.dinner;
            }
            if (alphasU.get(5)) {
                analysisResponse.recipeType = RecipeType.supper;
            }
            if (analysisResponse.getActivityType() != null && analysisResponse.getRecipeType() != null && analysisResponses.stream().filter((aR) -> aR.getRecipeType() == analysisResponse.getRecipeType() && aR.getRecipeType() == analysisResponse.getRecipeType()).count() == 0) {
                analysisResponses.add(analysisResponse);
            }
        });

        completionHandler.completed(new ArrayList<AnalysisResponse>(analysisResponses));
    }
}
