import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Radek on 2016-06-11.
 */
public class Solver {
    public static Boolean solve(Collection<Fact> facts, Collection<Rule> rules, Variable wanted, Mode mode) {
        if (mode.equals(Mode.backward)) {
            try {
                Boolean bool = wanted.solve(facts);
                GUI.print(" ");
                GUI.print("Szukana " + wanted + " = " + bool);
                return bool;
            } catch (NoFactException e) {
                GUI.print("Nie udało się uzyskać wartości zmiennej " + wanted + ".");
                return null;
            }
        }
        return solveForward(facts, rules, wanted);
    }

    private static Boolean solveAnyRule(Collection<Fact> facts, Collection<Rule> rules, Variable wanted) {
        Boolean result;
        for (Rule rule: rules) {
            try {
                result = rule.solve(facts);
                Fact fact = new Fact(rule.getResult(), result);
                if (!SilnikWnioskujacy.isKnown(fact)) {
                    GUI.print("Z reguły " + rule + " wynika: " + rule.getResult() + " = " + result);
                    SilnikWnioskujacy.addFact(fact);
                    return true;
                }
            } catch (NoFactException e) {
            }
        }
        return false;
    }

    /*
    Zwraca wartość faktu, lub null jeśli nie udało się rozwiązać.
     */
    private static Boolean solveForward(Collection<Fact> facts, Collection<Rule> rules, Variable wanted) {
        try {
            Boolean result = wanted.solve(facts);
            GUI.print(" ");
            GUI.print("Szukana " + wanted + " = " + result);
            return result;
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
