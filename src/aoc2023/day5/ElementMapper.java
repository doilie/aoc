package aoc2023.day5;

import java.util.ArrayList;
import java.util.List;

public class ElementMapper {
    public static class ElementMap {
        long destinationStart;
        long sourceStart;
        long range;

        ElementMap(long destinationStart, long sourceStart, long range) {
            this.destinationStart = destinationStart;
            this.sourceStart = sourceStart;
            this.range = range;
        }
    }

    private final List<ElementMap> elementMaps = new ArrayList<>();

    public void addElementMap(String line) {
        String[] lineParts = line.split("\\s+");
        if (lineParts.length == 3) {
            elementMaps.add(new ElementMap(Long.parseLong(lineParts[0]), Long.parseLong(lineParts[1]), Long.parseLong(lineParts[2])));
        }
    }

    public long getDestination(long source) {
        for (ElementMap map : elementMaps) {
            if (source >= map.sourceStart && source <= map.sourceStart + map.range) {
                return map.destinationStart + source - map.sourceStart;
            }
        }
        return source;
    }
}
