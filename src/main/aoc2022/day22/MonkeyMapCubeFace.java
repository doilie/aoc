package aoc2022.day22;

import java.util.Hashtable;

public class MonkeyMapCubeFace {
    private static class AdjacentFace {
        int index;
        FacingDirection currentDirection;
        FacingDirection resultDirection;

        AdjacentFace(int index, FacingDirection currentDirection, FacingDirection resultDirection) {
            this.index = index;
            this.currentDirection = currentDirection;
            this.resultDirection = resultDirection;
        }
    }
    private final int index;
    private final Hashtable<FacingDirection, AdjacentFace> adjacentFaces = new Hashtable<>();
    private Hashtable<String, MonkeyMapTile> tiles = new Hashtable<>();

    public MonkeyMapCubeFace(int index) {
        this.index = index;
    }

    public void addAdjacentFace(int index, FacingDirection currentDirection, FacingDirection resultDirection) {
        adjacentFaces.put(currentDirection, new AdjacentFace(index, currentDirection, resultDirection));
    }

    public void setTiles(Hashtable<String, MonkeyMapTile> tiles) {
        this.tiles.putAll(tiles);
    }

    public MonkeyMapTile getTile(int x, int y) {
        return tiles.get(x + "," + y);
    }

    public int getIndex() {
        return index;
    }

    public boolean containsTile(MonkeyMapTile tile) {
        return tiles.containsKey(tile.getX() + "," + tile.getY());
    }

    public MonkeyMapCube.CubeFaceDirection getAdjacentFace(FacingDirection direction) {
        int index = adjacentFaces.get(direction).index;
        FacingDirection resultDirection = adjacentFaces.get(direction).resultDirection;
        return new MonkeyMapCube.CubeFaceDirection(index, resultDirection);
    }
}
