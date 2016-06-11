/**
 * Created by Radek on 2016-06-07.
 */
public class Fact {
    private Variable variable;
    private Boolean value;

    public Fact(Variable variable, Boolean value) {
        this.variable = variable;
        this.value = value;
    }

    public Fact(String in) {
        String[] splitedIn = in.split(" = ");
        this.variable = new Variable(splitedIn[0]);
        this.value = !splitedIn[1].equals("0");
    }

    public Variable getVariable() {
        return variable;
    }

    public Boolean getValue() {
        return value;
    }

    @Override
    public String toString() {
        return variable.toString() + " = " + value;
    }
}
