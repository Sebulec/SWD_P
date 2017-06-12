package backend;

import java.util.Arrays;
import java.util.List;

public class LogicFunctions {

    private List<Boolean> alphasU;
    private List<Boolean> alphasW;
    private List<Boolean> alphasY;

    private int internalDecisionIndex;
    private int decisionIndex1;
    private int decisionIndex2;
    private int analysisIndex1;

    public LogicFunctions() {
        this.alphasU = Arrays.asList(false, false, false, false, false, false);
        this.alphasW = Arrays.asList(false, false, false, false, false, false);
        this.alphasY = Arrays.asList(false, false, false);
    }

    public LogicFunctions(int analysisIndex1, int internalDecisionIndex) {
        this();
        this.analysisIndex1 = analysisIndex1;
        this.internalDecisionIndex = internalDecisionIndex;
    }

    public LogicFunctions(int decisionIndex1, int decisionIndex2, int internalDecisionIndex) {
        this();
        this.decisionIndex1 = decisionIndex1;
        this.decisionIndex2 = decisionIndex2;
        this.internalDecisionIndex = internalDecisionIndex;
    }

    public void setAlphasU(List<Boolean> alphasU) {
        this.alphasU = alphasU;
    }

    public void setAlphasW(List<Boolean> alphasW) {
        this.alphasW = alphasW;
    }

    public void setAlphasY(List<Boolean> alphasY) {
        this.alphasY = alphasY;
    }

    public List<Boolean> getAlphasU(){
        return alphasU;
    }

    public List<Boolean> getAlphasY(){
        return alphasY;
    }

    public Boolean alphaU(int index) {
        return alphasU.get(--index);
    }

    public Boolean alphaW(int index) {
        return alphasW.get(--index);
    }

    public Boolean alphaY(int index) {
        return alphasY.get(--index);
    }

    private static boolean implies(boolean p, boolean q) {
        return !p || q;
    }

    private static boolean incrementBooleanList(List<Boolean> list) {
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

    public boolean incrementAlphas() {
        if (!incrementBooleanList(alphasU)) {
            if (!incrementBooleanList(alphasW)) {
                if (!incrementBooleanList(alphasY)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean function1() {
        return implies(alphaW(1) && (alphaU(1) || alphaU(2)), alphaW(4));
    }

    private boolean function2() {
        return implies(alphaW(1) && alphaU(3), alphaW(5));
    }

    private boolean function3() {
        return implies(alphaW(2) && alphaU(1), alphaW(4));
    }

    private boolean function4() {
        return implies(alphaW(2) && alphaU(2), alphaW(5));
    }

    private boolean function5() {
        return implies(alphaW(2) && alphaU(3), alphaW(6));
    }

    private boolean function6() {
        return implies(alphaW(3) && (alphaU(1) || alphaU(2)), alphaW(5));
    }

    private boolean function7() {
        return implies(alphaW(3) && alphaU(3), alphaW(6));
    }

    private boolean function8() {
        return implies((alphaU(4) || alphaU(6)) && alphaW(4), alphaY(1));
    }

    private boolean function9() {
        return implies((alphaU(4) || alphaU(6)) && (alphaW(5) || alphaW(6)), alphaY(2));
    }

    private boolean function10() {
        return implies(alphaU(5) && (alphaW(4) || alphaW(5)), alphaY(2));
    }

    private boolean function11() {
        return implies(alphaU(5) && alphaW(6), alphaY(3));
    }

    private boolean function12() {
        return implies(!((alphaU(4) || alphaU(6)) && alphaW(4)), !alphaY(1));
    }

    private boolean function13() {
        return implies(!((alphaU(4) || alphaU(6)) && (alphaW(5) || alphaW(6))), !alphaY(2));
    }

    private boolean function14() {
        return implies(!(alphaU(5) && (alphaW(4) || alphaW(5))), !alphaY(2));
    }

    private boolean function15() {
        return implies(!(alphaU(5) && alphaW(6)), !alphaY(3));
    }

    private boolean function16() {
        return !(alphaW(4) && alphaW(5)) && !(alphaW(5) && alphaW(6)) && !(alphaW(4) && alphaW(6));
    }

    public boolean functionF() {
        return function1() && function2() && function3() && function4() && function5() && function6() &&
                function7() && function8() && function9() && function10() && function11() && function12() &&
                function13() && function14() && function15() && function15() && function16();
    }

    public boolean functionY() {
        return alphaU(decisionIndex1) && alphaU(decisionIndex2) && alphaW(internalDecisionIndex);
    }

    public boolean functionU() {
        return alphaY(analysisIndex1) && alphaW(internalDecisionIndex);
    }
}
