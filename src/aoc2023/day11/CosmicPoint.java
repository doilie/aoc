package aoc2023.day11;

public class CosmicPoint {
    public static final String GALAXY = "#";
    private static long expansionFactor = 2;

    private final int x;
    private final int y;
    private final String value;
    private long xSize = 1;
    private long ySize = 1;

    public CosmicPoint(int x, int y, String value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public static void setExpansionFactor(long value) {
        expansionFactor = value;
    }

    public void expandX() {
        xSize = expansionFactor;
    }

    public void expandY() {
        ySize = expansionFactor;
    }

    public long getXSize() {
        return xSize;
    }

    public long getYSize() {
        return ySize;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return buildName(this.x, this.y) + ": " + this.value;
    }

    public static String buildName(int x, int y) {
        return x + "," + y;
    }
}
