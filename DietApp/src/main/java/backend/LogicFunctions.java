package backend;

import java.util.Arrays;
import java.util.List;

public class LogicFunctions {
    public LogicFunctions() {
        this.alphasU = Arrays.asList(false, false, false, false, false, false);
        this.alphasW = Arrays.asList(false, false, false, false, false, false);
        this.alphasY = Arrays.asList(false, false, false);
    }

    private List<Boolean> alphasU;
    private List<Boolean> alphasW;
    private List<Boolean> alphasY;

    public Boolean alphaU(int index) {
        return alphasU.get(--index);
    }

    public Boolean alphaW(int index) {
        return alphasW.get(--index);
    }

    public Boolean alphaY(int index) {
        return alphasY.get(--index);
    }

    public static boolean implies(boolean p, boolean q) {
        return !p || q;
    }

    public Boolean incrementBooleanList(List<Boolean> list) {
        for (int index = 0; index < list.size(); index++) {
            Boolean cell = list.get(index);
            if (!cell) {
                list.set(index, true);
                return true;
            } else {
                list.set(index, false);
            }
        }
        return false;
    }

    public boolean function1() {
        return implies(alphaW(1) && (alphaU(1) || alphaU(2)), alphaW(4));
    }

    public boolean function2() {
        return implies(alphaW(1) && alphaU(3), alphaW(5));
    }

    public boolean function3() {
        return implies(alphaW(2) && alphaU(1), alphaW(4));
    }

    public boolean function4() {
        return implies(alphaW(2) && alphaU(2), alphaW(5));
    }

    public boolean function5() {
        return implies(alphaW(2) && alphaU(3), alphaW(6));
    }

    public boolean function6() {
        return implies(alphaW(3) && (alphaU(1) || alphaU(2)), alphaW(5));
    }

    public boolean function7() {
        return implies(alphaW(3) && alphaU(3), alphaW(6));
    }

    public boolean function8() {
        return implies((alphaU(4) || alphaU(6)) && alphaW(4), alphaY(1));
    }

    public boolean function9() {
        return implies((alphaU(4) || alphaU(6)) && (alphaW(5) || alphaW(6)), alphaY(2));
    }

    public boolean function10() {
        return implies(alphaU(5) && (alphaW(4) || alphaW(5)), alphaY(2));
    }

    public boolean function11() {
        return implies(alphaU(5) && alphaW(6), alphaY(3));
    }

    public boolean function12() {
        return implies(!((alphaU(4) || alphaU(6)) && alphaW(4)), !alphaY(1));
    }

    public boolean function13() {
        return implies(!((alphaU(4) || alphaU(6)) && (alphaW(5) || alphaW(6))), !alphaY(2));
    }

    public boolean function14() {
        return implies(!(alphaU(5) && (alphaW(4) || alphaW(5))), !alphaY(2));
    }

    public boolean function15() {
        return implies(!(alphaU(5) && alphaW(6)), !alphaU(3));
    }

    public boolean function16() {
        return !(alphaY(1) && alphaY(2)) && !(alphaY(2) && alphaY(3)) && !(alphaY(1) && alphaY(3));
    }

    public boolean functionF() {
        return function1() && function2() && function3() && function4() && function5() && function6() &&
                function7() && function8() && function9() && function10() && function11() && function12() &&
                function13() && function14() && function15() && function15() && function16();
    }
}
