package tyates.bananasolve.data;

import tyates.bananasolve.util.Direction;

import java.util.List;

/**
 * Interface representing a game board.
 */
public interface Board {
    /**
     * Adds the given word to the board at the given position with the given direction if possible.
     * <p>
     * If the word cannot be added, this method returns {@code false}.
     *
     * @param word      the given word
     * @param r         the given row
     * @param c         the given column
     * @param direction the given direction
     * @return {@code true} if the word was added successfully, {@code false} otherwise
     */
    boolean addWord(final String word, final int r, final int c, final Direction direction);

    /**
     * Returns a List of {@link Tile}s that are present on the board.
     *
     * @return the list of tiles
     */
    List<Tile> getTiles();

    /**
     * Returns a List of words that are present on the board.
     *
     * @return the list of words
     */
    List<String> getWords();
}
