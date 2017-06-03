package tyates.bananasolve.dictionary;

public final class Restrictions {
    /**
     * Returns a restriction that requires each word to start with the given character.
     *
     * @param ch the given character
     * @return the restriction
     */
    public static Restriction startsWith(final char ch) {
        return new Restriction() {
            @Override
            public boolean permissible(final String word) {
                return word.startsWith("" + ch);
            }
        };
    }

    /**
     * Returns a restriction that requires each word to end with the given character.
     *
     * @param ch the given character
     * @return the restriction
     */
    public static Restriction endsWith(final char ch) {
        return new Restriction() {
            @Override
            public boolean permissible(final String word) {
                return word.endsWith("" + ch);
            }
        };
    }

    /**
     * Returns a restriction that requires each word to contain the given character.
     *
     * @param ch the given character
     * @return the restriction
     */
    public static Restriction contains(final char ch) {
        return new Restriction() {
            @Override
            public boolean permissible(String word) {
                return word.contains("" + ch);
            }
        };
    }
}
