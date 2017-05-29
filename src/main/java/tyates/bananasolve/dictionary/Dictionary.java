package tyates.bananasolve.dictionary;

import java.util.Set;

/**
 * Interface for classes that serve as a dictionary of valid words.
 */
public interface Dictionary {
    /**
     * Returns whether the given word is a valid word.
     *
     * @param word the given word
     * @return {@code true} if the given word is valid, {@code false} otherwise.
     */
    boolean isValidWord(final String word);

    /**
     * Returns the Set of valid words recognized by this Dictionary.
     *
     * @return the Set of valid words
     */
    Set<String> validWords();
}
