package tyates.bananasolve.solver;

import tyates.bananasolve.data.ArrayBoard;
import tyates.bananasolve.data.Board;
import tyates.bananasolve.data.TileGroup;
import tyates.bananasolve.dictionary.Dictionary;

/**
 * A class the uses a brute-force approach to solve a Banagrams hand.
 */
public class BruteForceSolver implements Solver {
    final Dictionary dictionary;

    /**
     * Creates a brute-force solver which will use the given Dictionary.
     *
     * @param dictionary the given dictionary
     */
    public BruteForceSolver(final Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    @Override
    public Board solve(final TileGroup tiles) {
        Board board = new ArrayBoard(dictionary);
        TileGroup solution = tiles.copy();
        while (!tiles.isEmpty()) {

        }
        return board;
    }
}
