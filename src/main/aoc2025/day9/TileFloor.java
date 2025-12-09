package aoc2025.day9;

import java.math.BigInteger;
import java.util.*;

public class TileFloor {
    private final Map<String, BigInteger> redTileRectangleAreas = new HashMap<>();
    private final Set<String> redTiles = new HashSet<>();

    TileFloor(String input) {
        String[] lines = input.split("\\n");
        for (int i = 0; i < lines.length - 1; i++) {
            String point1 = lines[i];
            redTiles.add(point1);
            for (int j = i + 1; j < lines.length; j++) {
                String point2 = lines[j];
                redTiles.add(point2);
                RectangleFromDiagonal rectangleFromDiagonal = new RectangleFromDiagonal(point1, point2);
                redTileRectangleAreas.put(point1 + "-" + point2, rectangleFromDiagonal.getArea());
            }
        }
    }

    BigInteger getLargestRedRectangleArea()
    {
        List<Map.Entry<String, BigInteger>> largestRedRectangleAreas = redTileRectangleAreas.entrySet().stream().sorted((o1, o2) -> o2.getValue().compareTo(o1.getValue())).toList();
        String key = largestRedRectangleAreas.getFirst().getKey();
        return largestRedRectangleAreas.getFirst().getValue();
    }
}
