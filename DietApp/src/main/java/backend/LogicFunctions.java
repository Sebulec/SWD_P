package backend;

public class LogicFunctions {
    public static boolean implies(boolean p, boolean q) {
        return !p || q;
    }

    public static boolean function1(boolean alphaU1, boolean alphaU2, boolean alphaW4, boolean alphaW7) {
        return implies(alphaW4 && (alphaU1 || alphaU2), alphaW7);
    }

    public static boolean function2(boolean alphaW4, boolean alphaW8, boolean alphaU3) {
        return implies(alphaW4 && alphaU3, alphaW8);
    }

    public static boolean function3(boolean alphaW5, boolean alphaW7, boolean alphaU1) {
        return implies(alphaW5 && alphaU1, alphaW7);
    }

    public static boolean function4(boolean alphaW5, boolean alphaW8, boolean alphaU2) {
        return implies(alphaW5 && alphaU2, alphaW8);
    }

    public static boolean function5(boolean alphaW5, boolean alphaW9, boolean alphaU3) {
        return implies(alphaW5 && alphaU3, alphaW9);
    }

    public static boolean function6(boolean alphaU1, boolean alphaU2, boolean alphaW6, boolean alphaW8) {
        return implies(alphaW6 && (alphaU1 || alphaU2), alphaW8);
    }

    public static boolean function7(boolean alphaW6, boolean alphaW9, boolean alphaU3) {
        return implies(alphaW6 && alphaU3, alphaW9);
    }

    public static boolean function8(boolean alphaU4, boolean alphaU6, boolean alphaW7, boolean alphaY1) {
        return implies((alphaU4 || alphaU6) && alphaW7, alphaY1);
    }

    public static boolean function9(boolean alphaU4, boolean alphaU6, boolean alphaW8, boolean alphaW9, boolean alphaY2) {
        return implies((alphaU4 || alphaU6) && (alphaW8 || alphaW9), alphaY2);
    }

    public static boolean function10(boolean alphaU5, boolean alphaW7, boolean alphaW8, boolean alphaY2) {
        return implies(alphaU5 && (alphaW7 || alphaW8), alphaY2);
    }

    public static boolean function11(boolean alphaU5, boolean alphaW9, boolean alphaY3) {
        return implies(alphaU5 && alphaW9, alphaY3);
    }

    public static boolean function12(boolean alphaU4, boolean alphaU6, boolean alphaW7, boolean alphaY1) {
        return implies(!((alphaU4 || alphaU6) && alphaW7), !alphaY1);
    }

    public static boolean function13(boolean alphaU4, boolean alphaU6, boolean alphaW8, boolean alphaW9, boolean alphaY2) {
        return implies(!((alphaU4 || alphaU6) && (alphaW8 || alphaW9)), !alphaY2);
    }

    public static boolean function14(boolean alphaU5, boolean alphaW7, boolean alphaW8, boolean alphaY2) {
        return implies(!(alphaU5 && (alphaW7 || alphaW8)), !alphaY2);
    }

    public static boolean function15(boolean alphaU5, boolean alphaW9, boolean alphaU3) {
        return implies(!(alphaU5 && alphaW9), !alphaU3);
    }

    public static boolean function16(boolean alphaY1, boolean alphaY2, boolean alphaY3) {
        return !(alphaY1 && alphaY2) && !(alphaY2 && alphaY3) && !(alphaY1 && alphaY3);
    }
}
