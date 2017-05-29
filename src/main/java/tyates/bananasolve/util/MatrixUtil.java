package tyates.bananasolve.util;

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
}
