package aoc2023.day11;

import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

public class GalaxyImage {
    private static int maxX = -1;
    private static int maxY = -1;
    private final HashMap<String, CosmicPoint> cosmicPoints = new HashMap<>();

    public void addCosmicPoint(int x, int y, String value) {
        cosmicPoints.put(CosmicPoint.buildName(x, y), new CosmicPoint(x, y, value));
        if (x > maxX) {
            maxX = x;
        }
        if (y > maxY) {
            maxY = y;
        }
    }

    public void expand() {
        IntStream.range(0, maxX).forEach(x -> {
            List<CosmicPoint> xPoints = cosmicPoints.values().stream().filter(cp -> cp.getX() == x).toList();
            if (xPoints.stream().noneMatch(cp -> cp.getValue().equals(CosmicPoint.GALAXY))) {
                xPoints.forEach(CosmicPoint::expandX);
            }
        });
        IntStream.range(0, maxY).forEach(y -> {
            List<CosmicPoint> yPoints = cosmicPoints.values().stream().filter(cp -> cp.getY() == y).toList();
            if (yPoints.stream().noneMatch(cp -> cp.getValue().equals(CosmicPoint.GALAXY))) {
                yPoints.forEach(CosmicPoint::expandY);
            }
        });
    }

    public long getSumOfGalaxyDistances() {
        List<CosmicPoint> galaxies = cosmicPoints.values().stream().filter(cp -> cp.getValue().equals(CosmicPoint.GALAXY)).toList();
        long sum = 0;
        for (int i = 0; i < galaxies.size() - 1; i++) {
            for (int j = i + 1; j < galaxies.size(); j++) {
                CosmicPoint galaxy1 = galaxies.get(i);
                CosmicPoint galaxy2 = galaxies.get(j);
                long distance = getDistance(galaxy1, galaxy2);
                sum += distance;
//                System.out.println(galaxy1 + " -> " + galaxy2 + ": " + distance);
            }
        }
        return sum;
    }

    private long getDistance(CosmicPoint galaxy1, CosmicPoint galaxy2) {
        if (!galaxy1.getValue().equals(CosmicPoint.GALAXY) || !galaxy2.getValue().equals(CosmicPoint.GALAXY)) {
            return 0;
        }

        int startX = Math.min(galaxy1.getX(), galaxy2.getX());
        int endX = Math.max(galaxy1.getX(), galaxy2.getX());
        long xSum = cosmicPoints.values().stream().filter(cp -> cp.getX() >= startX && cp.getX() <= endX && cp.getY() == galaxy1.getY()).map(CosmicPoint::getXSize).mapToLong(xSize -> xSize).sum() - 1;

        int startY = Math.min(galaxy1.getY(), galaxy2.getY());
        int endY = Math.max(galaxy1.getY(), galaxy2.getY());
        long ySum = cosmicPoints.values().stream().filter(cp -> cp.getY() >= startY && cp.getY() <= endY && cp.getX() == galaxy2.getX()).map(CosmicPoint::getYSize).mapToLong(ySize -> ySize).sum() - 1;

        return xSum + ySum;
    }
}
