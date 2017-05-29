package tyates.bananasolve.data;

import tyates.bananasolve.dictionary.Dictionary;
import tyates.bananasolve.util.Direction;
import tyates.bananasolve.util.MatrixUtil;

import java.util.ArrayList;
import java.util.List;

import static tyates.bananasolve.util.StringStandardizer.UNINITIALIZED_CHARACTER;
import static tyates.bananasolve.util.StringStandardizer.standardize;

/**
 * Implementation of {@link Board} that is backed by an array.
 */
public class ArrayBoard implements Board {
    private final Dictionary dictionary;

    private char[][] tiles = new char[1024][1024];

    /**
     * Creates a new board with the given dictionary.
     *
     * @param dictionary the given dictionary
     */
    public ArrayBoard(final Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    private static List<String> getWords(char[][] board) {
        final boolean[][] checked = new boolean[board.length][board[0].length];
        final List<String> words = new ArrayList<>();

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                if (checked[r][c]) {
                    continue;
                }

                final char ch = board[r][c];
                checked[r][c] = true;

                if (ch == UNINITIALIZED_CHARACTER) {
                    continue;
                }

                String word = buildWord(r, c, Direction.DOWN, board, checked);
                if (word != null) {
                    words.add(word);
                }
                word = buildWord(r, c, Direction.RIGHT, board, checked);
                if (word != null) {
                    words.add(word);
                }
            }
        }

        return words;
    }

    private static String buildWord(int r, int c, final Direction direction, final char[][] board,
                                    final boolean[][] checked) {
        final StringBuilder stringBuilder = new StringBuilder();
        while (board[r][c] != UNINITIALIZED_CHARACTER) {
            checked[r][c] = true;
            stringBuilder.append(board[r][c]);

            if (direction == Direction.DOWN) {
                r++;
            } else {
                c++;
            }
        }
        // A single character should not be considered a "word"
        if (stringBuilder.length() > 1) {
            return stringBuilder.toString();
        } else {
            return null;
        }
    }

    @Override
    public boolean addWord(String word, final int row, final int col, final Direction direction) {
        word = standardize(word);
        if (!dictionary.isValidWord(word)) {
            return false;
        }

        // Create a new matrix so that if we find we cannot add the word successfully, the original board remains
        // unmodified.
        final char[][] newTiles = MatrixUtil.cloneMatrix(tiles);

        // If we are placing the word with direction LEFT or UP we want to start placing with the last character
        if (direction == Direction.LEFT || direction == Direction.UP) {
            word = new StringBuffer(word).reverse().toString();
        }

        int currentRow = row;
        int currentCol = col;
        for (final char ch : word.toCharArray()) {
            // If there is an existing character, ensure that it is the character we want to add
            if (UNINITIALIZED_CHARACTER != newTiles[currentRow][currentCol]) {
                if (newTiles[currentRow][currentCol] != ch) {
                    return false;
                }
            }

            newTiles[currentRow][currentCol] = ch;

            // Change currentRow and currentCol based on direction
            if (direction == Direction.DOWN) {
                currentRow++;
            } else if (direction == Direction.RIGHT) {
                currentCol++;
            } else if (direction == Direction.LEFT) {
                currentCol--;
            } else {
                currentRow--;
            }
        }

        final boolean isValid = boardIsValid(newTiles);
        if (isValid) {
            this.tiles = newTiles;
        }
        return isValid;
    }

    private boolean boardIsValid(final char[][] board) {
        final List<String> words = getWords(board);
        for (final String word : words) {
            if (!dictionary.isValidWord(standardize(word))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public List<String> getWords() {
        return getWords(tiles);
    }

    @Override
    public List<Tile> getTiles() {
        final List<Tile> tilesList = new ArrayList<>();
        for (int r = 0; r < tiles.length; r++) {
            for (int c = 0; c < tiles[r].length; c++) {
                if (tiles[r][c] != UNINITIALIZED_CHARACTER) {
                    tilesList.add(new Tile(r, c, tiles[r][c]));
                }
            }
        }
        return tilesList;
    }

    @Override
    public String toString() {
        return MatrixUtil.prettyPrintMatrix(tiles);
    }
}
