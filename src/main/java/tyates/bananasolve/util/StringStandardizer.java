package tyates.bananasolve.util;

/**
 * Util class for working with Strings.
 */
public class StringStandardizer {
    public static final Character UNINITIALIZED_CHARACTER = '\u0000';

    /**
     * Returns the standardized form of the given String.
     *
     * @param input the given String
     * @return the standardized String
     */
    public static String standardize(final String input) {
        return input.trim().toLowerCase();
    }
}
