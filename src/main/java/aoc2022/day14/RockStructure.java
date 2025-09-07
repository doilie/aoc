package aoc2022.day14;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class RockStructure {
    private final Hashtable<String, String> structure = new Hashtable<>();

    public RockStructure(String input) {
        String[] points = input.split(" -> ");
        List<Coordinate> coordinateList = new ArrayList<>();
        for (String point : points) {
            Coordinate coordinate = Coordinate.parseName(point);
            if (coordinate != null) {
                coordinateList.add(coordinate);
            }
        }
        for (int i = 0; i < coordinateList.size() - 1; i++) {
            structure.putAll(Line.draw(coordinateList.get(i), coordinateList.get(i + 1), Cave.Rock));
        }
    }

    public Hashtable<String, String> getStructure() {
        return structure;
    }
}
