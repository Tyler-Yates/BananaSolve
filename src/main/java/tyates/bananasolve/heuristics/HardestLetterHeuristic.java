package tyates.bananasolve.heuristics;

import com.google.common.collect.ImmutableSet;

import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Class that orders based on the number of "hard characters" in a word. Words with higher numbers of "hard characters"
 * are considered more promising. If there is a tie in number of "hard characters", longer words are preferred.
 */
public class HardestLetterHeuristic implements OrderingHeuristic {
    private static final Set<Character> HARD_CHARACTERS = ImmutableSet.of('z', 'v', 'q', 'x', 'j', 'k');

    private static final Comparator<String> COMPARATOR = new Comparator<String>() {
        @Override
        public int compare(final String s1, final String s2) {
            final int score = numberOfHardCharacters(s2) - numberOfHardCharacters(s1);
            if (score == 0) {
                final int lengthScore = s2.length() - s1.length();
                if (lengthScore == 0) {
                    return s1.compareTo(s2);
                }
                return lengthScore;
            }
            return score;
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
