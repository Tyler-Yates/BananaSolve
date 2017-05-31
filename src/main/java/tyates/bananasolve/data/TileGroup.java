package tyates.bananasolve.data;

import tyates.bananasolve.util.CountingMap;

import java.util.List;

/**
 * Interface representing a group of tiles. The group may contain duplicates.
 */
public interface TileGroup {
    /**
     * Returns a List of the tiles in the current hand.
     * <p>
     * If the user has four 'A' tiles in their hand, then 'a' will show up four times in the returned list.
     *
     * @return the List of tiles
     */
    List<Character> getTiles();

    /**
     * Returns a {@link CountingMap} for tiles in the player's hand.
     *
     * @return the Map
     */
    CountingMap<Character> getTileCounts();

    /**
     * Returns a new tile group that represents the combination of the current tile group and given tile group.
     *
     * @param otherTileGroup the given tile group
     * @return a new tile group
     */
    TileGroup combinedWith(final TileGroup otherTileGroup);

    /**
     * Returns a new tile group that represents the current tile group after removing all tiles in the given tile group.
     * <p>
     * If the current tile group does not have enough tiles to perform the removal, {@code null} is returned.
     *
     * @param otherTileGroup the given tile group
     * @return a new tile group or {@code null} if the current tile group does not have enough tiles
     */
    TileGroup subtractedBy(final TileGroup otherTileGroup);

    /**
     * Returns whether the current tile group is empty.
     *
     * @return {@code true} if the current tile group has not tiles, {@code false} otherwise
     */
    boolean isEmpty();

    /**
     * Creates a deep copy of the current tile group.
     *
     * @return the deep copy
     */
    TileGroup copy();
}
