package tyates.bananasolve.heuristics;

import com.google.common.collect.ImmutableSet;

import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Class that orders based on the number of "hard characters" in a word. Words with higher numbers of "hard characters"
 * are considered more promising.
 */
public class HardestLetterHeuristic implements OrderingHeuristic {
    private static final Set<Character> HARD_CHARACTERS = ImmutableSet.of('z', 'v', 'q', 'x', 'j', 'k');

    private static final Comparator<String> COMPARATOR = new Comparator<String>() {
        @Override
        public int compare(final String s1, final String s2) {
            return numberOfHardCharacters(s2) - numberOfHardCharacters(s1);
        }
    };

    @Override
    public SortedSet<String> orderWords(Set<String> words) {
        final TreeSet<String> orderedWords = new TreeSet<>(COMPARATOR);
        orderedWords.addAll(words);
        return orderedWords;
    }

    private static int numberOfHardCharacters(final String string) {
        int hardCharacters = 0;
        for (final char ch : string.toLowerCase().toCharArray()) {
            if (HARD_CHARACTERS.contains(ch)) {
                hardCharacters++;
            }
        }
        return hardCharacters;
    }
}
