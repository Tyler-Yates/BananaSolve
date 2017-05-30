package tyates.bananasolve.data;

import java.util.List;
import java.util.Map;

/**
 * Interface representing a Player's hand of tiles.
 */
public interface TileHand {
    /**
     * Returns a List of the tiles in the current hand.
     *
     * @return the List of tiles
     */
    List<Character> getTiles();

    /**
     * Returns a Map from character to number of tiles with that character in the player's hand.
     * <p>
     * For example, if a player had three 'A' tiles in their hand the Map would have:<br>
     * {@code 'a' -> 3}
     *
     * @return the Map
     */
    Map<Character, Integer> getTileCounts();
}
