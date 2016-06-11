import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Radek on 2016-06-07.
 */
public class Rule {
    private Variable result;
    private Equation equation;

    public Rule(Variable result, Equation equation) {
        this.result = result;
        this.equation = equation;
    }

    public Rule(String in) {
        String[] inSplited = in.split(" ");
        ArrayList<String> inList = new ArrayList<String>();
        for (String tmp: inSplited)
            inList.add(tmp);

        setResult(new Variable(inList.get(0)));
        inList.remove(0);
        inList.remove(0);

        setEquation(EquationBuilder.BuildEquation(inList));
    }

    public void setResult(Variable result) {
        this.result = result;
    }

    public void setEquation(Equation equation) {
        this.equation = equation;
    }

    public Boolean solve(Collection<Fact> facts) throws NoFactException{
        return equation.solve(facts);
    }

    public Variable getResult() {
        return result;
    }

    @Override
    public String toString() {
        return result.toString() + " = " + equation.toString();
    }
}
