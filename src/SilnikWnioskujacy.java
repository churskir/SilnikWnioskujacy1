import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Radek on 2016-06-07.
 */
public class SilnikWnioskujacy {
    private static Collection<Fact> facts = new ArrayList<>();
    private static Collection<Rule> rules = new ArrayList<>();
    private static Variable wanted;
    private static Mode mode = Mode.backward;

    public static void main(String[] args) {
        Initializer.Initialize();
        GUI.openWindow();
    }

    public static Collection<Fact> getFacts() {
        return facts;
    }

    public static Collection<Rule> getRules() {
        return rules;
    }

    public static void setFacts(Collection<Fact> facts) {
        SilnikWnioskujacy.facts = facts;
    }

    public static void setRules(Collection<Rule> rules) {
        SilnikWnioskujacy.rules = rules;
    }

    public static Variable getWanted() {
        return wanted;
    }

    public static Mode getMode() {
        return mode;
    }

    public static void setMode(Mode mod) {
        mode = mod;
    }

    public static void setWanted(Variable in) {
        wanted = in;
    }

    public static void addFact(Fact fact) {
        facts.add(fact);
    }

    public static void addRule(Rule rule) {
        rules.add(rule);
    }

    public static void deleteThing(int index) {
        if (index > -1) {
            System.out.println(facts.size() + " " + index);
            if (facts.size() > index) {
                ArrayList<Fact> list = new ArrayList<>(getFacts());
                list.remove(index);
                setFacts(list);
            } else {
                index -= facts.size();
                ArrayList<Rule> list = new ArrayList<>(getRules());
                list.remove(index);
                setRules(list);
            }
        }
    }
}