package tyates.bananasolve.heuristics;

import java.util.Set;

/**
 * Returns the longest word as the word that should be placed first.
 */
public class LongestWordNextHeuristic implements NextWordHeuristic {

    @Override
    public String getFirstWordToPlace(final Set<String> possibleWords) {
        String longestWord = "";
        for (final String word : possibleWords) {
            if (word.length() > longestWord.length()) {
                longestWord = word;
            }
        }
        return longestWord;
    }
}
