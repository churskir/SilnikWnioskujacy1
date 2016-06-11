import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Radek on 2016-06-11.
 */
public class Solver {
    public static Boolean solve(Collection<Fact> facts, Collection<Rule> rules, Variable wanted, Mode mode) {
        if (mode.equals(Mode.backward))
        try {
                Boolean bool = wanted.solve(facts);
                GUI.print("Wartość zmiennej " + wanted + " jest znana: " + wanted + " = " + bool);
                return bool;
            }catch (NoFactException e) {
                GUI.print("Nie udało się uzyskać wartości zmiennej " + wanted + ".");
                return null;
            }
        return solveForward(facts, rules, wanted);
    }

    private static Boolean solveAnyRule(Collection<Fact> facts, Collection<Rule> rules, Variable wanted) {
        Boolean result;
        for (Rule rule: rules) {
            try {
                result = rule.solve(facts);
                facts.add(new Fact(rule.getResult(), result));
                GUI.print("Z reguły " + rule + " wynika: " + rule.getResult() + " = " + result);
                return result;
            } catch (NoFactException e) {
            }
        }
        return Boolean.FALSE;
    }

    /*
    Zwraca wartość faktu, lub null jeśli nie udało się rozwiązać.
     */
    private static Boolean solveForward(Collection<Fact> facts, Collection<Rule> rules, Variable wanted) {
        try {
            return wanted.solve(facts);
        } catch (NoFactException e) {
        }
        while (solveAnyRule(facts, rules, wanted)) {
            try {
                return wanted.solve(facts);
            } catch (NoFactException e) {
            }
        }
        GUI.print("Nie udało się uzyskać wartości zmiennej " + wanted + ".");
        return null;
    }
}
