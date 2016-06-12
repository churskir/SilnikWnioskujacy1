import java.util.Collection;

/**
 * Created by Radek on 2016-06-07.
 */
public class TwoArgumentEquation extends Equation {
    private Equation left;
    private Equation right;
    private TwoArgumentOperator operator;

    public TwoArgumentEquation(Equation left, Equation right, TwoArgumentOperator operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    public Boolean solve(Collection<Fact> facts) throws NoFactException {
        return operator.solve(left.solve(facts), right.solve(facts));
    }

    public Boolean equals(TwoArgumentEquation equation) {
        return this.left.equals(equation.left) && this.right.equals(equation.left) && this.operator.equals(equation.operator);
    }

    @Override
    public String toString() {
        return left.toString() + " " + operator.toString() + " " + right.toString();
    }
}
