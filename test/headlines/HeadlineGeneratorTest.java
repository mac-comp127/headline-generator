package headlines;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HeadlineGeneratorTest {
    @Test
    void testNoNestedSymbols() {
        Substitutions.mockSubstitution("headline", List.of(List.of("one ", "two three")));
        assertEquals("one two three", HeadlineGenerator.generateHeadline());
    }

    @Test
    void testSinglyNestedSymbol() {
        Substitutions.mockSubstitution("headline", List.of(
            List.of("one ", "[a]", " two")));
        Substitutions.mockSubstitution("a", List.of(List.of("and ", "a")));
        assertEquals("one and a two", HeadlineGenerator.generateHeadline());

        Substitutions.mockSubstitution("headline", List.of(
            List.of("[a]", " one ", "[a]", " two ", "[a]")));
        assertEquals("and a one and a two and a", HeadlineGenerator.generateHeadline());
    }

    @Test
    void testRecursivelyNestedSymbols() {
        Substitutions.mockSubstitution("headline", List.of(
            List.of("[a]", " one ", "[a]", " two ", "[a]")));
        Substitutions.mockSubstitution("a", List.of(List.of("[conjuction]", " a", "[drumroll]")));
        Substitutions.mockSubstitution("conjuction", List.of(List.of("and")));
        Substitutions.mockSubstitution("drumroll", List.of(List.of("...")));
        assertEquals("and a... one and a... two and a...", HeadlineGenerator.generateHeadline());
    }

    @Test
    void testSymbolDetection() {
        Substitutions.mockSubstitution("headline", List.of(
            List.of("[a", " one ", "[a]", " two ", "a]")));
        Substitutions.mockSubstitution("a", List.of(List.of("and ", "a")));
        assertEquals("[a one and a two a]", HeadlineGenerator.generateHeadline());
    }

    @Test
    void testRandomness() {
        Substitutions.mockSubstitution("headline", List.of(
            List.of("[a]", "[b]"),
            List.of("[b]", "[a]")));
        Substitutions.mockSubstitution("a", List.of(List.of("a"), List.of("HEY", "!")));
        Substitutions.mockSubstitution("b", List.of(List.of("buzz", "buzz"), List.of("bee")));

        Set<String> allResults = new TreeSet<>();
        for(int n = 0; n < 1000 && allResults.size() < 8; n++) {
            allResults.add(
                HeadlineGenerator.generateHeadline());
        }
        assertEquals(
            new HashSet<>(List.of(
                "abuzzbuzz",
                "HEY!buzzbuzz",
                "abee",
                "HEY!bee",
                "buzzbuzza",
                "buzzbuzzHEY!",
                "beea",
                "beeHEY!")),
            allResults);
    }
}
