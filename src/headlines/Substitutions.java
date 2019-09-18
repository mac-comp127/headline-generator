package headlines;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Utility for reading configuration files containing lists of substitutions that can generate
 * random text.
 */
public class Substitutions {
    private static Map<String, List<List<String>>> substitutionCache = new HashMap<>();

    /**
     * Returns the possible substitution choices for the given symbol.
     *
     * These choices live in text files in the res/ directory. For example, if asked for the "noun"
     * symbol, this method will look for a file called "noun.txt" in res. Each line of the text file
     * represents one possible substitution choice.
     *
     * @param symbolName The
     * @return A list of possible choices, where each choice is a list of string fragments.
     *  Fragments enclosed in square braces "[…]" indicate references to other symbols. All other
     *  fragments are literal text.
     */
    public static List<List<String>> getSubstitutionChoices(String symbolName) {
        List<List<String>> choices = substitutionCache.get(symbolName);
        if (choices != null) {
            return choices;
        }
        choices = readSubstitutionChoices(symbolName);
        substitutionCache.put(symbolName, choices);
        return choices;
    }

    /**
     * Internal method to override substitution choices for a given symbol for unit testing purposes.
     * @param symbolName
     * @param choices
     */
    static void mockSubstitution(String symbolName, List<List<String>> choices) {
        substitutionCache.put(symbolName, choices);
    }

    private static List<List<String>> readSubstitutionChoices(String symbolName) {
        InputStream resourceStream = Substitutions.class.getResourceAsStream("/" + symbolName + ".txt");
        if (resourceStream == null) {
            throw new RuntimeException("Can't find symbol named “" + symbolName
                    + "” (looking for /res/" + symbolName + ".txt)");
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(resourceStream));

        List<List<String>> results = new ArrayList<>();
        String line;
        while (true) {
            try {
                line = in.readLine();
            } catch (IOException e) {
                throw new RuntimeException("Error while reading " + symbolName, e);
            }
            if (line == null) {
                break;
            }
            if (line.isBlank()) {
                continue;
            }
            results.add(List.of(
                    line.trim().split("(?=\\[)|(?<=])")));
        }
        return Collections.unmodifiableList(results);
    }
}
