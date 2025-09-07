package aoc2022.day22;

public class MonkeyMapTile implements Comparable<MonkeyMapTile> {
    public static String OpenTile = ".";
    public static String WallTile = "#";
    public static String VisitedRightTile = ">";
    public static String VisitedLeftTile = "<";
    public static String VisitedUpTile = "^";
    public static String VisitedDownTile = "v";

    public MonkeyMapTile(int x, int y, String type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    @Override
    public int compareTo(MonkeyMapTile o) {
        if (o.getX() == this.getX()) {
            return Integer.compare(this.getY(), o.getY());
        }
        else if (o.getY() == this.getY()) {
            return Integer.compare(this.getX(), o.getX());
        }
        return 0;
    }

    public enum TileType {
        Open,
        Wall;
    }
    private final int x;
    private final int y;
    private final String type;

    public String getPositionName() {
        return x + "," + y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getTile() {
        return type;
    }

    public int getCubeFaceX(int cubeFaceSize) {
        return getCubeFaceCoordinate(x, cubeFaceSize);
    }

    public int getCubeFaceY(int cubeFaceSize) {
        return getCubeFaceCoordinate(y, cubeFaceSize);
    }

    public static int getCubeFaceCoordinate(int coordinate, int cubeFaceSize) {
        return (coordinate - 1) % cubeFaceSize + 1;
    }

    @Override
    public String toString() {
        return getTile();
    }

}
