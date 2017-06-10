package tyates.bananasolve.data;

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
