import java.util.ArrayList;

/**
 * Created by Radek on 2016-06-07.
 */
public class EquationBuilder {
    // Dostajemy samo równanie, np. "A + B"
    public static Equation BuildEquation(ArrayList<String> in) {
        Equation equation = BuildSubequation(in);
        if (in.isEmpty())
            return equation;
        String tmp = in.get(0);
        in.remove(0);
        if ((tmp.equals("+")) || (tmp.equals("*"))) {
            TwoArgumentOperator operator;
            if (tmp.equals("+"))
                operator = new Alternative();
            else
                operator = new Conjunction();
            return new TwoArgumentEquation(equation, BuildEquation(in), operator);
        }
        return equation;
    }

    private static Equation BuildSubequation(ArrayList<String> in) {
        String tmp = in.get(0);
        in.remove(0);
        if (tmp.equals("("))
            return new OneArgumentEquation(BuildEquation(in), new Brackets());
        if (tmp.equals("-")) {
            tmp = in.get(0);
            if (tmp.equals("(")) {
                in.remove(0);
                Equation equation = BuildEquation(in);
                return new OneArgumentEquation(new OneArgumentEquation(equation, new Brackets()), new Negation());
            }
            return new OneArgumentEquation(new Variable(tmp), new Negation());
        }
        return new Variable(tmp);
    }
}
