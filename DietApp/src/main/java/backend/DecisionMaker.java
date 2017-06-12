package backend;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.*;

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
//        completionHandler.completed(recipes.stream().filter((recipe -> recipe.caloriesLevel)));
    }

    public Recipe[] makeDecision(User user, CaloriesLevel caloriesLevel, CompletionHandler completionHandler) {
        Recipe[] recipes = null;

        return null;
    }
}
