package tyates.bananasolve.data;

import tyates.bananasolve.util.CountingMap;
import tyates.bananasolve.util.HashCountingMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashTileGroup implements TileGroup {
    final CountingMap<Character> tiles;

    public HashTileGroup(final List<Character> tiles) {
        this.tiles = new HashCountingMap<>(tiles);
    }

    private HashTileGroup(final CountingMap<Character> tiles) {
        this.tiles = tiles.copy();
    }

    @Override
    public List<Character> getTiles() {
        return tiles.getValues();
    }

    @Override
    public CountingMap<Character> getTileCounts() {
        return tiles.copy();
    }

    @Override
    public TileGroup combinedWith(TileGroup otherTileGroup) {
        final List<Character> combinedTiles = new ArrayList<>();
        combinedTiles.addAll(tiles.getValues());
        combinedTiles.addAll(otherTileGroup.getTiles());
        return new HashTileGroup(combinedTiles);
    }

    @Override
    public TileGroup subtractedBy(TileGroup otherTileGroup) {
        final Map<Character, Integer> counts = new HashMap<>();
        for (final Map.Entry<Character, Integer> entry : getTileCounts().getCounts().entrySet()) {
            final int newCount = entry.getValue() - otherTileGroup.getTileCounts().getCount(entry.getKey());
            if (newCount < 0) {
                return null;
            } else {
                counts.put(entry.getKey(), newCount);
            }
        }
        return new HashTileGroup(new HashCountingMap<>(counts));
    }

    @Override
    public boolean isEmpty() {
        return tiles.isEmpty();
    }

    @Override
    public TileGroup copy() {
        return new HashTileGroup(tiles.copy());
    }
}
