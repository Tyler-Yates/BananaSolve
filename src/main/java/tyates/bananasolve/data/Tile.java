package tyates.bananasolve.data;

public class Tile implements Comparable<Tile> {
    private final Position position;
    private final char character;

    public Tile(final int row, final int col, final char character) {
        this.position = new Position(row, col);
        this.character = character;
    }

    public int getRow() {
        return position.getRow();
    }

    public int getCol() {
        return position.getCol();
    }

    public char getCharacter() {
        return character;
    }

    @Override
    public String toString() {
        return "{'" + character + "' " + position.toString() + "}";
    }

    @Override
    public boolean equals(final Object o) {
        if (o == null || o.getClass() != Tile.class) {
            return false;
        }

        final Tile otherTile = (Tile) o;
        return position.equals(otherTile.position);
    }

    @Override
    public int hashCode() {
        return position.hashCode();
    }

    @Override
    public int compareTo(final Tile o) {
        return position.compareTo(o.position);
    }
}
