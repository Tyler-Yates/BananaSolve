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
        // Every beginning letter needs to be checked both DOWN and RIGHT
        final boolean[][] checkedDown = new boolean[board.length][board[0].length];
        final boolean[][] checkedRight = new boolean[board.length][board[0].length];
        final List<String> words = new ArrayList<>();

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {

                final char ch = board[r][c];

                if (ch == UNINITIALIZED_CHARACTER) {
                    continue;
                }

                if (!checkedDown[r][c]) {
                    final String word = buildWord(r, c, Direction.DOWN, board, checkedDown);
                    if (word != null) {
                        words.add(word);
                    }
                }
                if (!checkedRight[r][c]) {
                    final String word = buildWord(r, c, Direction.RIGHT, board, checkedRight);
                    if (word != null) {
                        words.add(word);
                    }
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

    private static void findTileMass(final int r, final int c, char[][] tiles, boolean[][] tileMass) {
        tileMass[r][c] = true;

        if (r > 0 && tiles[r - 1][c] != UNINITIALIZED_CHARACTER && !tileMass[r - 1][c]) {
            findTileMass(r - 1, c, tiles, tileMass);
        }
        if (r < tiles.length - 1 && tiles[r + 1][c] != UNINITIALIZED_CHARACTER && !tileMass[r + 1][c]) {
            findTileMass(r + 1, c, tiles, tileMass);
        }
        if (c > 0 && tiles[r][c - 1] != UNINITIALIZED_CHARACTER && !tileMass[r][c - 1]) {
            findTileMass(r, c - 1, tiles, tileMass);
        }
        if (c < tiles[r].length - 1 && tiles[r][c + 1] != UNINITIALIZED_CHARACTER && !tileMass[r][c + 1]) {
            findTileMass(r, c + 1, tiles, tileMass);
        }
    }

    @Override
    public List<Character> addWord(String word, final int row, final int col, final Direction direction) {
        word = standardize(word);
        if (!dictionary.isValidWord(word)) {
            return null;
        }

        final List<Character> addedCharacters = new ArrayList<>();

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
                    return null;
                }
            } else {
                newTiles[currentRow][currentCol] = ch;
                addedCharacters.add(ch);
            }

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
        } else {
            return null;
        }
        return addedCharacters;
    }

    private boolean boardIsValid(final char[][] board) {
        final List<String> words = getWords(board);
        for (final String word : words) {
            if (!dictionary.isValidWord(standardize(word))) {
                return false;
            }
        }

        boolean firstCharacter = true;
        final boolean[][] oneTileMass = new boolean[board.length][board[0].length];

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                if (board[r][c] == UNINITIALIZED_CHARACTER) {
                    continue;
                }

                if (firstCharacter) {
                    findTileMass(r, c, board, oneTileMass);
                    firstCharacter = false;
                } else if (!oneTileMass[r][c]) {
                    return false;
                }
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
