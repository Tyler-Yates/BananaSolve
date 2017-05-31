package tyates.bananasolve.solver;

import tyates.bananasolve.data.Board;
import tyates.bananasolve.data.TileGroup;

/**
 * Interface for classes that solve a Bananagram hand.
 */
public interface Solver {
    /**
     * Returns a solution for the given hand of tiles.
     *
     * @param tiles the given hand of tiles
     * @return a solution
     */
    Board solve(final TileGroup tiles);
}
