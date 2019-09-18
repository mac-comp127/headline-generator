package headlines;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A source of exciting news headlines to assist an aspiring but short-staffed tabloid.
 */
public class HeadlineGenerator {
    // A single long-term source of randomness for choosing substitutions
    private static Random rand = new Random();

    public static void main(String[] args) {
        for (int n = 0; n < 10; n++) {
            System.out.println(generateHeadline());
        }
    }

    /**
     * Generates a new headline at random by starting with the "[headline]" symbol and applying
     * random substitutions.
     */
    public static String generateHeadline() {
        List<String> headlineParts = generateText("headline");
        return String.join("", headlineParts);  // turn list of strings into one big string
    }

    /**
     * Generates random text by starting with the given symbol and repeatedly applying random
     * substitutions.
     */
    private static List<String> generateText(String symbolName) {
        // Your job:
        //   Get the choices from symbolName using Substitutions.getSubstitutionChoices()
        //   Use the chooseRandomSubstitution() method below to pick one at random
        //   Call applySubstitutions() to find symbols in the resulting list and substitute them
        //   Return the result
        return List.of("not implemented yet");
    }

    /**
     * Scans the given list of text fragments for strings enclosed in square braces "[…]",
     * interprets the strings between the braces as symbol names, and expands them using
     * generateText().
     *
     * @return A new list of fully substituted text containing no more unsubstituted symbols.
     */
    private static List<String> applySubstitutions(List<String> words) {
        // Your job:
        //   Create a new empty mutable list to hold the results
        //   For each word of the input:
        //     If the word starts with "[" and ends with "]":
        //       Get the symbol name out from between the braces
        //       Call generateText() with the symbol name
        //       Add what generateText() returned to your result list
        //     Otherwise:
        //       No substitution necessary; just add this word to the results
        //   Return the result
        return List.of("not implemented yet");
    }

    /**
     * Randomly chooses one of the given substitution choices.
     */
    private static List<String> chooseRandomSubstitution(List<List<String>> substitutions) {
        // Your job:
        //   Use rand.nextInt(…) to pick a random index from the substitutions list
        //   Return the element at that index
        return List.of("not implemented yet");
    }
}
