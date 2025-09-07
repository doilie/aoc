package aoc2022.day14;

import java.util.Hashtable;

public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static String buildName(int x, int y) {
        return x + "," + y;
    }

    public static Coordinate parseName(String coordinateName) {
        String[] coordinateParts = coordinateName.split(",");
        if (coordinateParts.length == 2) {
            return new Coordinate(Integer.parseInt(coordinateParts[0]), Integer.parseInt(coordinateParts[1]));
        }
        return null;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getName() {
        return buildName(x, y);
    }

    public void moveDown(Hashtable<String, String> positions) {
        if (positions.get(buildName(x, y + 1)) == null) {
            y++;
        }
    }

    public void moveDownLeft(Hashtable<String, String> positions) {
        if (positions.get(buildName(x - 1, y + 1)) == null) {
            x--;
            y++;
        }
    }

    public void moveDownRight(Hashtable<String, String> positions) {
        if (positions.get(buildName(x + 1, y + 1)) == null) {
            x++;
            y++;
        }
    }
}
