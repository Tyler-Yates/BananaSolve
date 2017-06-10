package tyates.bananasolve.data;

import tyates.bananasolve.util.Direction;

/**
 * Represents a position on the board.
 */
public class Position implements Comparable<Position> {
    private final int row;
    private final int col;

    public Position(final int row, final int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Returns the position after moving one step in the given direction from the given position.
     *
     * @param position  the given position
     * @param direction the given direction
     * @return the new position
     */
    public static Position move(final Position position, final Direction direction) {
        switch (direction) {
            case UP:
                return new Position(position.getRow() - 1, position.getCol());
            case DOWN:
                return new Position(position.getRow() + 1, position.getCol());
            case LEFT:
                return new Position(position.getRow(), position.getCol() - 1);
            case RIGHT:
                return new Position(position.getRow(), position.getCol() + 1);
        }
        return null;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Position position = (Position) o;
        return row == position.row && col == position.col;
    }

    @Override
    public int hashCode() {
        return 31 * row + col;
    }

    @Override
    public String toString() {
        return "(" + row + "," + col + ")";
    }

    @Override
    public int compareTo(Position o) {
        final int compareRow = Integer.compare(row, o.row);
        if (compareRow != 0) {
            return compareRow;
        }

        return Integer.compare(col, o.col);
    }
}
