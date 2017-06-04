package tyates.bananasolve.solver;

import tyates.bananasolve.data.*;
import tyates.bananasolve.dictionary.Dictionary;
import tyates.bananasolve.dictionary.Restrictions;
import tyates.bananasolve.heuristics.HardestLetterHeuristic;
import tyates.bananasolve.heuristics.LongestWordHeuristic;
import tyates.bananasolve.heuristics.OrderingHeuristic;
import tyates.bananasolve.util.Direction;

import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * A class the uses a brute-force approach to solve a Banagrams hand.
 */
public class BruteForceSolver implements Solver {
    private final Dictionary dictionary;
    private final OrderingHeuristic firstWordHeuristic;
    private final OrderingHeuristic subsequentWordHeuristic;

    final ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(8);

    private Board solution = null;

    /**
     * Creates a brute-force solver which will use the given Dictionary.
     *
     * @param dictionary the given dictionary
     */
    public BruteForceSolver(final Dictionary dictionary) {
        this.dictionary = dictionary;
        firstWordHeuristic = new LongestWordHeuristic();
        subsequentWordHeuristic = new HardestLetterHeuristic();
    }

    @Override
    public Board solve(final TileGroup tiles) {
        solution = null;

        final SortedSet<String> validWords = firstWordHeuristic.orderWords(dictionary.validWordsPossible(tiles));
        System.out.println(validWords);
        for (final String word : validWords) {
            final Board board = new ArrayBoard(dictionary);
            final List<Character> addedChars = board.addWord(word, 100, 100, Direction.RIGHT);

            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    work(board, tiles.subtractedBy(new HashTileGroup(addedChars)));
                }
            });
        }

        executorService.shutdown();
        try {
            executorService.awaitTermination(100, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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

        // Placing a word on an existing board
        for (final Tile tile : currentBoard.getTiles()) {
            placeWordsOnTile(currentBoard, currentTiles, tile);
        }
    }

    private void placeWordsOnTile(final Board board, final TileGroup tiles, final Tile tile) {
        final TileGroup tilesPlusTile = tiles.combinedWith(tile);

        // The word must contain the tile we are placing on
        final Set<String> restrictedWords = dictionary.validWordsPossible(tilesPlusTile,
                Restrictions.contains(tile.getCharacter()));
        final SortedSet<String> words = subsequentWordHeuristic.orderWords(restrictedWords);
        for (final String word : words) {
            // The word may have multiple occurrences of the tile. We should attempt each place.
            for (int i = 0; i < word.length(); i++) {
                final char ch = word.charAt(i);
                if (ch == tile.getCharacter()) {
                    tryPlacingWord(word, tiles, board, tile.getRow(), tile.getCol() - i, Direction.RIGHT);
                    tryPlacingWord(word, tiles, board, tile.getRow() - i, tile.getCol(), Direction.DOWN);
                }
            }
        }
    }

    private void tryPlacingWord(final String word, final TileGroup tiles, final Board board, final int r, final int c,
                                final Direction direction) {
        final Board newBoard = board.copy();
        final List<Character> addedChars = newBoard.addWord(word, r, c, direction);
        if (addedChars != null && !addedChars.isEmpty()) {
            work(newBoard, tiles.subtractedBy(new HashTileGroup(addedChars)));
        }
    }
}
