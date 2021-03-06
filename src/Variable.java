import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Radek on 2016-06-07.
 */
public class Variable extends Equation {
    private String name;

    public Variable(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Boolean solve(Collection<Fact> facts) throws NoFactException {
        Mode mode = SilnikWnioskujacy.getMode();
        Collection<Rule> rules = SilnikWnioskujacy.getRules();
        for (Fact fact: facts) {
            if (fact.getVariable().equals(this)) {
                return fact.getValue();
            }
        }
        if (mode.equals(Mode.backward))
            for (Rule rule: rules) {
                if (rule.getResult().equals(this)) {
                    GUI.print("Próbuję rozwiązać " + rule);
                    try {
                        Boolean res = rule.solve(facts);
                        GUI.print("Z reguły " + rule + " wynika:   " + this + " = " + res);
                        SilnikWnioskujacy.addFact(new Fact(this, res));
                        return rule.solve(facts);
                    } catch (NoFactException e) {
                        GUI.print("Z reguły " + rule + " nie udało się znaleźć szukanej.");
                        GUI.print(" ");
                    }
                }
            }
        throw new NoFactException();
    }

    public Boolean equals(Variable variable) {
        return variable.getName().equals(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
