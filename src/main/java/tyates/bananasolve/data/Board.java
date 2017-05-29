package tyates.bananasolve.data;

import tyates.bananasolve.dictionary.Dictionary;
import tyates.bananasolve.util.Direction;

import java.util.ArrayList;
import java.util.List;

import static tyates.bananasolve.util.StringStandardizer.UNINITIALIZED_CHARACTER;

public class Board {
    private final char[][] tiles = new char[1024][1024];
    private final Dictionary dictionary;

    public Board(final Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public boolean addWord(final String word, final int row, final int col, final Direction direction) {
        if (!dictionary.isValidWord(word)) {
            return false;
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
            } else {
                currentCol++;
            }
        }
        return boardIsValid();
    }

    public boolean boardIsValid() {
        final boolean[][] checked = new boolean[tiles.length][tiles[0].length];

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

                final boolean validDown = checkWord(r, c, Direction.DOWN, checked);
                final boolean validRight = checkWord(r, c, Direction.RIGHT, checked);
                if (!validDown || !validRight) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean checkWord(int r, int c, final Direction direction, final boolean[][] checked) {
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
        // A single character should not be checked as a "word"
        return stringBuilder.length() <= 1 || dictionary.isValidWord(stringBuilder.toString());
    }

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
