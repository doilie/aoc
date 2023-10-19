package aoc2022.day14;

import java.util.Hashtable;

public class Line {
    public static Hashtable<String, String> draw(Coordinate start, Coordinate end, String label) {
        Hashtable<String, String> lineCoordinates = new Hashtable<>();
        int startX = Math.min(start.getX(), end.getX());
        int endX = Math.max(start.getX(), end.getX());
        int startY = Math.min(start.getY(), end.getY());
        int endY = Math.max(start.getY(), end.getY());

        if (startX == endX) {
            for (int y = startY; y <= endY; y++) {
                lineCoordinates.put(new Coordinate(startX, y).getName(), label);
            }
        }
        else if (startY == endY) {
            for (int x = startX; x <= endX; x++) {
                lineCoordinates.put(new Coordinate(x, startY).getName(), label);
            }
        }

        return lineCoordinates;
    }
}
