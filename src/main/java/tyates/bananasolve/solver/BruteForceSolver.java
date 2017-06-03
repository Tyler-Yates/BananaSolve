package tyates.bananasolve.solver;

import tyates.bananasolve.data.*;
import tyates.bananasolve.dictionary.Dictionary;
import tyates.bananasolve.heuristics.LongestWordNextHeuristic;
import tyates.bananasolve.util.Direction;

import java.util.List;
import java.util.Set;

/**
 * A class the uses a brute-force approach to solve a Banagrams hand.
 */
public class BruteForceSolver implements Solver {
    private final Dictionary dictionary;

    private Board solution = null;

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
        solution = null;

        work(new ArrayBoard(dictionary), tiles);

        return solution;
    }

    private void work(final Board currentBoard, final TileGroup currentTiles) {
        // Make sure we have a board and tile group
        if (currentBoard == null || currentTiles == null) {
            return;
        }

        // If a solution has been found we can just kill this recursive call
        if (solution != null) {
            return;
        }

        // If we have used all tiles then we have found a solution
        if (currentTiles.isEmpty()) {
            solution = currentBoard;
            return;
        }

        System.out.println(currentBoard);
        System.out.println(currentTiles);

        // Placing the first word
        if (currentBoard.getTiles().isEmpty()) {
            final Set<String> validWords = dictionary.validWordsPossible(currentTiles);
            final String firstWordToPlace = new LongestWordNextHeuristic().getFirstWordToPlace(validWords);

            final Board newBoard = currentBoard.copy();
            final List<Character> addedChars = newBoard.addWord(firstWordToPlace, 100, 100, Direction.RIGHT);
            work(newBoard, currentTiles.subtractedBy(new HashTileGroup(addedChars)));
        } else {
            // Placing a word on an existing board
            for (final Tile tile : currentBoard.getTiles()) {
                final TileGroup newTileGroup = currentTiles.combinedWith(tile);
                final Set<String> words = dictionary.validWordsPossible(newTileGroup);
                for (final String word : words) {
                    tryPlacingWord(word, currentTiles, currentBoard, tile.getRow(), tile.getCol());
                }
            }
        }
    }

    private void tryPlacingWord(final String word, final TileGroup tiles, final Board board, final int r, final int c) {
        final Board newBoard = board.copy();
        final List<Character> addedChars = newBoard.addWord(word, r, c, Direction.RIGHT);
        if (addedChars != null) {
            work(newBoard, tiles.subtractedBy(new HashTileGroup(addedChars)));
        }

        final Board newBoard2 = board.copy();
        final List<Character> addedChars2 = newBoard2.addWord(word, r, c, Direction.DOWN);
        if (addedChars2 != null) {
            work(newBoard2, tiles.subtractedBy(new HashTileGroup(addedChars2)));
        }
    }
}
