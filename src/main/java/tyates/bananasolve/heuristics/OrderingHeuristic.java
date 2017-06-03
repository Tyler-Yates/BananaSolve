package tyates.bananasolve.heuristics;

import java.util.Set;
import java.util.SortedSet;

/**
 * Interface for classes that return which order words should be attempted in.
 */
public interface OrderingHeuristic {
    /**
     * Returns a List of the given words, ordered from most promising to least promising.
     *
     * @param words the given words
     * @return the ordered List of words
     */
    SortedSet<String> orderWords(final Set<String> words);
}
