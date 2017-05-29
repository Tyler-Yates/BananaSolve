package tyates.bananasolve.heuristics;

import java.util.Set;

/**
 * Interface for classes that return which word should be placed first.
 */
public interface NextWordHeuristic {
    /**
     * Returns the word that this heuristic thinks should be placed first from the Set of possible words.
     *
     * @param possibleWords the Set of possible words to place
     * @return a word
     */
    String getFirstWordToPlace(final Set<String> possibleWords);
}
