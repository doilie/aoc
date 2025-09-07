package aoc2022.day18;

import java.util.HashSet;
import java.util.Set;

public class Cube {
    private final int x;
    private final int y;
    private final int z;

    public Cube(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static String buildName(int x, int y, int z) {
        return x + "," + y + "," + z;
    }

    public static Cube parseName(String coordinateName) {
        String[] coordinateParts = coordinateName.split(",");
        if (coordinateParts.length == 3) {
            return new Cube(Integer.parseInt(coordinateParts[0]), Integer.parseInt(coordinateParts[1]), Integer.parseInt(coordinateParts[2]));
        }
        return null;
    }

    public boolean isAdjacent(Cube toCompare) {
        return Math.abs(toCompare.x - x) +
                Math.abs(toCompare.y - y) +
                Math.abs(toCompare.z - z) == 1;

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public Set<String> getPossibleNeighbors() {
        Set<String> possibleNeighbors = new HashSet<>();
        possibleNeighbors.add(buildName(x - 1, y, z));
        possibleNeighbors.add(buildName(x + 1, y, z));
        possibleNeighbors.add(buildName(x, y - 1, z));
        possibleNeighbors.add(buildName(x, y + 1, z));
        possibleNeighbors.add(buildName(x, y, z - 1));
        possibleNeighbors.add(buildName(x, y, z + 1));
        return possibleNeighbors;
    }

    public String getName() {
        return buildName(x, y, z);
    }
}
