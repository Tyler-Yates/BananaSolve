package tyates.bananasolve.data;

import tyates.bananasolve.dictionary.Dictionary;
import tyates.bananasolve.util.Direction;

import java.util.*;

import static tyates.bananasolve.util.StringStandardizer.standardize;

public class NodeBoard implements Board {
    private final Dictionary dictionary;

    //Tiles are sorted by row first then column. Iteration will thus be like moving through a matrix starting at
    //the upper-left and moving right.
    private SortedMap<Position, Character> tiles = new TreeMap<>();

    public NodeBoard(final Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    @Override
    public List<Character> addWord(String word, final int r, final int c, final Direction direction) {
        word = standardize(word);
        if (!dictionary.isValidWord(word)) {
            return null;
        }

        final List<Character> charactersAdded = new ArrayList<>();
        final SortedMap<Position, Character> tempTiles = new TreeMap<>(tiles);

        boolean touchedExistingTile = false;
        Position currentPosition = new Position(r, c);
        for (final char currentCharacter : word.toCharArray()) {
            final Character existingCharacter = tiles.get(currentPosition);
            if (existingCharacter == null) {
                tempTiles.put(currentPosition, currentCharacter);
                charactersAdded.add(currentCharacter);
            } else {
                if (existingCharacter == currentCharacter) {
                    touchedExistingTile = true;
                } else {
                    return null;
                }
            }

            currentPosition = Position.move(currentPosition, direction);
        }

        // We need to ensure that tiles are all staying in one "mass"
        if (touchedExistingTile) {
            tiles = tempTiles;
            return charactersAdded;
        } else {
            // If this is the first word being placed then there will obviously not be any existing tiles
            if (tiles.isEmpty()) {
                tiles = tempTiles;
                return charactersAdded;
            } else {
                return null;
            }
        }
    }

    @Override
    public List<Tile> getTiles() {
        final List<Tile> tilesList = new ArrayList<>();
        for (final Map.Entry<Position, Character> entry : tiles.entrySet()) {
            tilesList.add(new Tile(entry.getKey().getRow(), entry.getKey().getCol(), entry.getValue()));
        }
        return tilesList;
    }

    @Override
    public List<String> getWords() {
        final List<String> words = new ArrayList<>();

        final Set<Position> checkedVertical = new HashSet<>();
        final Set<Position> checkedHorizontal = new HashSet<>();

        for (final Position position : tiles.keySet()) {
            if (!checkedVertical.contains(position)) {
                final String word = createWord(position, Direction.DOWN, checkedVertical);
                if (word.length() > 1) {
                    words.add(word);
                }
                checkedVertical.add(position);
            }
            if (!checkedHorizontal.contains(position)) {
                final String word = createWord(position, Direction.RIGHT, checkedHorizontal);
                if (word.length() > 1) {
                    words.add(word);
                }
                checkedHorizontal.add(position);
            }
        }

        return words;
    }

    private String createWord(final Position position, final Direction direction, Set<Position> checked) {
        Position currentPosition = position;
        final StringBuilder stringBuilder = new StringBuilder();
        while (tiles.containsKey(currentPosition)) {
            stringBuilder.append(tiles.get(currentPosition));
            checked.add(currentPosition);
            currentPosition = Position.move(currentPosition, direction);
        }
        return stringBuilder.toString();
    }

    @Override
    public Board copy() {
        final NodeBoard newNodeBoard = new NodeBoard(dictionary);
        newNodeBoard.tiles = new TreeMap<>(tiles);
        return newNodeBoard;
    }

    @Override
    public String toString() {
        int minR = Integer.MAX_VALUE;
        int maxR = Integer.MIN_VALUE;
        int minC = Integer.MAX_VALUE;
        int maxC = Integer.MIN_VALUE;

        for (final Position position : tiles.keySet()) {
            minR = Math.min(minR, position.getRow());
            maxR = Math.max(maxR, position.getRow());
            minC = Math.min(minC, position.getCol());
            maxC = Math.max(maxC, position.getCol());
        }

        if (minR > maxR) {
            return "";
        }

        final StringBuilder stringBuilder = new StringBuilder();

        for (int r = minR; r <= maxR; r++) {
            for (int c = minC; c <= maxC; c++) {
                final Character character = tiles.get(new Position(r, c));
                if (character == null) {
                    stringBuilder.append(" ");
                } else {
                    stringBuilder.append(character);
                }
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
