package backend;

import java.util.Arrays;
import java.util.List;

/**
 * Created by sebastiankotarski on 11.06.2017.
 */
public class DecisionMaker {

    public void makeDecision(User user, RecipeType recipeType, CompletionHandler completionHandler) {
        Recipe[] recipes = null;
        LogicFunctions logicFunctions;
        Integer decisionIndex1 = 0;
        Integer decisionIndex2 = 0;

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
        logicFunctions = new LogicFunctions(decisionIndex1,decisionIndex2);
//        List<Boolean> alphasW
        switch (user.bmi.weightType) {
            case underweight:
                logicFunctions.setAlphasU(Arrays.asList(true,false,false));
                break;
            case normal:
                logicFunctions.setAlphasU(Arrays.asList(false,true,false));
                break;
            case flesh:
                logicFunctions.setAlphasU(Arrays.asList(false,false,true));
                break;
        }
    }

//    public Recipe[] makeDecision(User user, CaloriesLevel caloriesLevel, CompletionHandler completionHandler) {
//        Recipe[] recipes = null;
//
//        return null;
//    }
}
