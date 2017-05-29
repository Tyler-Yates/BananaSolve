package tyates.bananasolve.data;

import tyates.bananasolve.dictionary.Dictionary;
import tyates.bananasolve.util.Direction;

import java.util.ArrayList;
import java.util.List;

import static tyates.bananasolve.util.StringStandardizer.UNINITIALIZED_CHARACTER;
import static tyates.bananasolve.util.StringStandardizer.standardize;

/**
 * Implementation of {@link Board} that is backed by an array.
 */
public class ArrayBoard implements Board {
    private final char[][] tiles = new char[1024][1024];
    private final Dictionary dictionary;

    /**
     * Creates a new board with the given dictionary.
     *
     * @param dictionary the given dictionary
     */
    public ArrayBoard(final Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    @Override
    public boolean addWord(String word, final int row, final int col, final Direction direction) {
        word = standardize(word);
        if (!dictionary.isValidWord(word)) {
            return false;
        }

        // If we are placing the word with direction LEFT or UP we want to start placing with the last character
        if (direction == Direction.LEFT || direction == Direction.UP) {
            word = new StringBuffer(word).reverse().toString();
        }

        int currentRow = row;
        int currentCol = col;
        for (final char ch : word.toCharArray()) {
            // If there is an existing character, ensure that it is the character we want to add
            if (UNINITIALIZED_CHARACTER != tiles[currentRow][currentCol]) {
                if (tiles[currentRow][currentCol] != ch) {
                    return false;
                }
            }

            tiles[currentRow][currentCol] = ch;

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
        return boardIsValid();
    }

    private boolean boardIsValid() {
        final List<String> words = getWords();
        for (final String word : words) {
            if (!dictionary.isValidWord(standardize(word))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public List<String> getWords() {
        final boolean[][] checked = new boolean[tiles.length][tiles[0].length];
        final List<String> words = new ArrayList<>();

        for (int r = 0; r < tiles.length; r++) {
            for (int c = 0; c < tiles[r].length; c++) {
                if (checked[r][c]) {
                    continue;
                }

                final char ch = tiles[r][c];
                checked[r][c] = true;

                if (ch == UNINITIALIZED_CHARACTER) {
                    continue;
                }

                String word = buildWord(r, c, Direction.DOWN, checked);
                if (word != null) {
                    words.add(word);
                }
                word = buildWord(r, c, Direction.RIGHT, checked);
                if (word != null) {
                    words.add(word);
                }
            }
        }

        return words;
    }

    private String buildWord(int r, int c, final Direction direction, final boolean[][] checked) {
        final StringBuilder stringBuilder = new StringBuilder();
        while (tiles[r][c] != UNINITIALIZED_CHARACTER) {
            checked[r][c] = true;
            stringBuilder.append(tiles[r][c]);

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
        int firstRow = Integer.MAX_VALUE;
        int firstCol = Integer.MAX_VALUE;
        int lastRow = 0;
        int lastCol = 0;

        for (int r = 0; r < tiles.length; r++) {
            for (int c = 0; c < tiles[r].length; c++) {
                if (tiles[r][c] != UNINITIALIZED_CHARACTER) {
                    firstRow = Math.min(firstRow, r);
                    lastRow = Math.max(lastRow, r);
                    firstCol = Math.min(firstCol, c);
                    lastCol = Math.max(lastCol, c);
                }
            }
        }

        final StringBuilder output = new StringBuilder();
        // Border
        for (int c = firstCol - 2; c <= lastCol + 2; c++) {
            output.append("-");
        }
        output.append("\n");
        // Tiles
        for (int r = firstRow; r <= lastRow; r++) {
            output.append("| "); // Border
            for (int c = firstCol; c <= lastCol; c++) {
                output.append(tiles[r][c]);
            }
            output.append(" |\n"); // Border
        }
        // Border
        for (int c = firstCol - 2; c <= lastCol + 2; c++) {
            output.append("-");
        }
        return output.toString();
    }
}
