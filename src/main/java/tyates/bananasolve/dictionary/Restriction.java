package tyates.bananasolve.dictionary;

/**
 * Class to restrict what is returned from a {@link Dictionary}.
 */
public interface Restriction {
    /**
     * Returns whether the given word is permissible according to the current restriction.
     *
     * @param word the given word
     * @return {@code true} if the word is permissible, {@code false} otherwise
     */
    boolean permissible(final String word);
}
