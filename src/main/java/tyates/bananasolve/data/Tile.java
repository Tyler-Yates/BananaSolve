package tyates.bananasolve.data;

public class Tile {
    private final int row;
    private final int col;
    private final char character;

    public Tile(final int row, final int col, final char character) {
        this.row = row;
        this.col = col;
        this.character = character;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public char getCharacter() {
        return character;
    }

    @Override
    public String toString() {
        return "{'" + character + "' (" + row + "," + col + ")}";
    }
}
