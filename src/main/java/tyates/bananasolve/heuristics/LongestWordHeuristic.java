package tyates.bananasolve.heuristics;

import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Returns the longest word as the word that should be placed first.
 */
public class LongestWordHeuristic implements OrderingHeuristic {
    private static final Comparator<String> COMPARATOR = new Comparator<String>() {
        @Override
        public int compare(final String s1, final String s2) {
            return s2.length() - s1.length();
        }
    };

    @Override
    public SortedSet<String> orderWords(Set<String> words) {
        final TreeSet<String> orderedWords = new TreeSet<>(COMPARATOR);
        orderedWords.addAll(words);
        return orderedWords;
    }
}
