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
     * If the word cannot be added, this method returns {@code null}.
     *
     * @param word      the given word
     * @param r         the given row
     * @param c         the given column
     * @param direction the given direction
     * @return The list of characters added to the board if the word was added successfully, {@code null} otherwise
     */
    List<Character> addWord(final String word, final int r, final int c, final Direction direction);

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

    /**
     * Returns a deep copy of the current board.
     *
     * @return the deep copy
     */
    Board copy();
}
