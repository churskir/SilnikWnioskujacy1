import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;

/**
 * Created by Radek on 2016-06-10.
 */
public class Initializer {

    private static String sourcePath = "C:/Users/Radek/Documents/dane.txt";
    private static Integer factsNumber = 0;
    private static Integer rulesNumber = 0;

    public static void Initialize() {
        BufferedReader br = null;
        try {
            String currentLine;
            br = new BufferedReader(new FileReader(sourcePath));
            setFactsNumber(br);
            readFacts(br);
            setRulesNumber(br);
            readRules(br);
            readWanted(br);
        } catch (FileNotFoundException e) {}
    }

    private static void setFactsNumber(BufferedReader br) {
        try {
            String line = br.readLine();
            if (line != null)
                factsNumber = Integer.parseInt(line);
        } catch (IOException e) {
        }
    }

    private static void setRulesNumber(BufferedReader br) {
        try {
            String line = br.readLine();
            if (line != null)
                rulesNumber = Integer.parseInt(line);
        } catch (IOException e) {
        }
    }

    private static void readFacts(BufferedReader br) {
        Collection<Fact> facts = SilnikWnioskujacy.getFacts();
        for (int i = 0; i < factsNumber; i++) {
            try {
                facts.add(new Fact(br.readLine()));
            } catch (IOException e) {
                return;
            }
        }
    }

    private static void readRules(BufferedReader br) {
        Collection<Rule> rules = SilnikWnioskujacy.getRules();
        for (int i = 0; i < rulesNumber; i++) {
            try {
                rules.add(new Rule(br.readLine()));
            } catch (IOException e) {
                return;
            }
        }
    }

    private static void readWanted(BufferedReader br) {
        try {
            SilnikWnioskujacy.setWanted(new Variable(br.readLine()));
        } catch (IOException e) {
        }
    }

    private static void readMode(BufferedReader br) {
        try {
            if (br.readLine().equals("backward"))
                SilnikWnioskujacy.setMode(Mode.backward);
            else
                SilnikWnioskujacy.setMode(Mode.forward);
        } catch (IOException e) {
            System.out.println("Read file error.");
        }

    }
}
