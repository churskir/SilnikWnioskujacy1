import java.util.Collection;

/**
 * Created by Radek on 2016-06-07.
 */
public class OneArgumentEquation extends Equation {
    private Equation equation;
    private OneArgumentOperator operator;

    public OneArgumentEquation(Equation equation, OneArgumentOperator operator) {
        this.equation = equation;
        this.operator = operator;
    }

    public Boolean solve(Collection<Fact> facts) throws NoFactException {
        return operator.solve(equation.solve(facts));
    }

    public Equation getEquation() {
        return equation;
    }

    public Boolean equals(OneArgumentEquation equation) {
        return this.equation.equals(equation.equation) && this.operator.equals(equation.operator);
    }

    @Override
    public String toString() {
        String tmp = operator.toString() + " " + equation.toString();
        if (operator.toString() == "(")
            tmp += " )";
        return tmp;
    }
}
