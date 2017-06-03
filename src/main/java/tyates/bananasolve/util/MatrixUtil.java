package tyates.bananasolve.util;

import static tyates.bananasolve.util.StringStandardizer.UNINITIALIZED_CHARACTER;

/**
 * Utility class for working with Matrices.
 */
public class MatrixUtil {
    /**
     * Deep copies the given matrix.
     *
     * @param input the given matrix
     * @return the deep copy
     */
    public static char[][] cloneMatrix(char[][] input) {
        final char[][] output = new char[input.length][input[0].length];
        for (int i = 0; i < input.length; i++) {
            System.arraycopy(input[i], 0, output[i], 0, input[i].length);
        }
        return output;
    }

    /**
     * Returns the smallest sub-matrix that contains all content from the given input matrix. In other words, all rows
     * and columns with no content are not returned.
     * <p>
     * The returned matrix is a deep copy.
     *
     * @param input the given matrix
     * @return the sub-matrix
     */
    public static char[][] getSubMatrix(final char input[][]) {
        int firstRow = Integer.MAX_VALUE;
        int firstCol = Integer.MAX_VALUE;
        int lastRow = 0;
        int lastCol = 0;

        for (int r = 0; r < input.length; r++) {
            for (int c = 0; c < input[r].length; c++) {
                if (input[r][c] != UNINITIALIZED_CHARACTER) {
                    firstRow = Math.min(firstRow, r);
                    lastRow = Math.max(lastRow, r);
                    firstCol = Math.min(firstCol, c);
                    lastCol = Math.max(lastCol, c);
                }
            }
        }

        if (firstRow > lastRow) {
            firstRow = lastRow = 0;
        }
        if (firstCol > lastCol) {
            firstCol = lastCol = 0;
        }

        final char[][] output = new char[lastRow - firstRow + 1][lastCol - firstCol + 1];
        for (int r = firstRow; r <= lastRow; r++) {
            System.arraycopy(input[r], firstCol, output[r - firstRow], 0, lastCol + 1 - firstCol);
        }
        return output;
    }

    /**
     * Pretty prints the given matrix. Only the smallest content sub-matrix is printed.
     *
     * @param input the given matrix
     * @return a String
     */
    public static String prettyPrintMatrix(final char[][] input) {
        final char[][] subMatrix = getSubMatrix(input);

        final StringBuilder output = new StringBuilder();
        // Border
        for (int c = 0; c <= subMatrix[0].length + 3; c++) {
            output.append("-");
        }
        output.append("\n");
        // Tiles
        for (char[] row : subMatrix) {
            output.append("| "); // Border
            for (char ch : row) {
                if (ch == UNINITIALIZED_CHARACTER) {
                    output.append(' ');
                } else {
                    output.append(ch);
                }
            }
            output.append(" |\n"); // Border
        }
        // Border
        for (int c = 0; c <= subMatrix[0].length + 3; c++) {
            output.append("-");
        }
        return output.toString();
    }
}
