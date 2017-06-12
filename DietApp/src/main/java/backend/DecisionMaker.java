package backend;

import javax.security.auth.login.LoginContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sebastiankotarski on 11.06.2017.
 */
public class DecisionMaker {

    public Recipe[] makeDecision(User user, RecipeType recipeType, CompletionHandler completionHandler) {
        Recipe[] recipes = null;
        LogicFunctions logicFunctions = new LogicFunctions();
        switch (recipeType) {
            case breakfast:
                logicFunctions.alphaU(4);
                break;
            case dinner:
                logicFunctions.alphaU(5);
                break;
            case supper:
                logicFunctions.alphaU(6);
                break;
        }
        switch (user.activityType) {
            case low:
                logicFunctions.alphaU(1);
                break;
            case normal:
                logicFunctions.alphaU(2);
                break;
            case high:
                logicFunctions.alphaU(3);
                break;
        }
        return recipes;
    }

//    public Recipe[] makeDecision(User user, CaloriesLevel caloriesLevel, CompletionHandler completionHandler) {
//        Recipe[] recipes = null;
//
//        return null;
//    }
}
