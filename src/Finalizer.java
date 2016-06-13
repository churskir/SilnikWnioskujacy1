import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Radek on 2016-06-13.
 */
public abstract class Finalizer {
    private static void printFactsNumber(PrintWriter writer, Collection<Fact> facts) {
        writer.println(facts.size());
    }

    private static void printFacts(PrintWriter writer, Collection<Fact> facts) {
        for (Fact fact: facts)
            writer.println(fact);
    }

    private static void printRulesNumber(PrintWriter writer, Collection<Rule> rules) {
        writer.println(rules.size());
    }

    private static void printRules(PrintWriter writer, Collection<Rule> rules) {
        for (Rule rule: rules)
            writer.println(rule);
    }

    private static void printWanted(PrintWriter writer) {
        String wanted = SilnikWnioskujacy.getWanted().toString();
        writer.println(wanted);
    }

    private static void printMode(PrintWriter writer) {
        String mode = SilnikWnioskujacy.getMode().toString();
        writer.println(mode);
    }

    private static void printData(PrintWriter writer, Collection<Fact> facts, Collection<Rule> rules) {
        printFactsNumber(writer, facts);
        printFacts(writer, facts);
        printRulesNumber(writer, rules);
        printRules(writer, rules);
        printWanted(writer);
        printMode(writer);
    }

    public static int save() {
        try {
            try {
                PrintWriter writer = new PrintWriter(SilnikWnioskujacy.sourcePath, "UTF-8");
                Collection<Fact> facts = SilnikWnioskujacy.getFacts();
                Collection<Rule> rules = SilnikWnioskujacy.getRules();
                printData(writer, facts, rules);
                writer.close();
                return 0;
            } catch (UnsupportedEncodingException e) {
                GUI.print("Błąd zapisu: niewspierane kodowanie.");
                return 1;
            }
        } catch (FileNotFoundException e) {
            return 0;
        }
    }
}
