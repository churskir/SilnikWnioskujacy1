import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by Radek on 2016-06-07.
 */
public class SilnikWnioskujacy {
    private static Collection<Fact> facts = new ArrayList<>();
    private static Collection<Rule> rules = new ArrayList<>();
    private static Variable wanted;
    private static Mode mode = Mode.backward;
    public static String sourcePath = "C:/Users/Radek/Documents/dane.txt";

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

    public static boolean isKnown(Fact fact) {
        for (Fact tmp: facts)
            if (tmp.equals(fact))
                return true;
        return false;
    }

    public static void addFact(Fact fact) {
/*        for (Fact tmp: facts)
            if (tmp.equals(fact)) {
                GUI.clearOutput();
                GUI.print(fact.toString() + ": taki fakt już istnieje.");
                return false;
            } */
        if (isKnown(fact)) {
            GUI.clearOutput();
            GUI.print(fact.toString() + ": taki fakt już istnieje.");
            return;
        }
        facts.add(fact);
        GUI.print("Dodano nowy fakt: " + fact);
    }

    public static void addRule(Rule rule) {
        for (Rule tmp: rules)
            if (tmp.equals(rule)) {
                GUI.clearOutput();
                GUI.print(rule + ": reguła już istnieje.");
                return;
            }
        if (rules.size() > 0)
            addRuleInOrder(rule);
        else
            rules.add(rule);
    }

    public static void makeRulesInReverseOrder() {
        ArrayList<Rule> newRules = new ArrayList<>(rules);
        Collections.reverse(newRules);
        rules = newRules;
    }

    public static void deleteThing(int index) {
        if (index > -1) {
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

    private static void addRuleInOrder(Rule rule) {
        ArrayList<Rule> newRules = new ArrayList<>();
        Boolean notAdded = true;
        for (Rule tmp: rules) {
            if ((tmp.getWeight() > rule.getWeight()) && (notAdded)) {
                newRules.add(rule);
                notAdded = false;
            }
            newRules.add(tmp);
        }
        if (rules.size() == newRules.size())
            rules.add(rule);
        else
            rules = newRules;
    }
}