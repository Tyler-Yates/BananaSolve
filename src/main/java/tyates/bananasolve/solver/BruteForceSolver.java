package tyates.bananasolve.solver;

import tyates.bananasolve.data.ArrayBoard;
import tyates.bananasolve.data.Board;
import tyates.bananasolve.data.HashTileGroup;
import tyates.bananasolve.data.TileGroup;
import tyates.bananasolve.dictionary.Dictionary;
import tyates.bananasolve.heuristics.LongestWordNextHeuristic;
import tyates.bananasolve.util.Direction;

import java.util.List;
import java.util.Set;

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
        while (!solution.isEmpty()) {
            if (board.getTiles().isEmpty()) {
                final Set<String> validWords = dictionary.validWordsPossible(solution);
                final String firstWordToPlace = new LongestWordNextHeuristic().getFirstWordToPlace(validWords);

                final List<Character> addedChars = board.addWord(firstWordToPlace, 100, 100, Direction.RIGHT);
                solution = solution.subtractedBy(new HashTileGroup(addedChars));
            }
        }
        System.out.println(board);
        return board;
    }
}
