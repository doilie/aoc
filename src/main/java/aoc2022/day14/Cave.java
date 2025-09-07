package aoc2022.day14;

import java.util.Hashtable;
import java.util.Objects;
import java.util.Set;

public class Cave {
    public static final String Rock = "#";
    public static final String Air = ".";
    public static final String SandChar = "o";
    private final Hashtable<String, String> caveElements = new Hashtable<>();
    private int minX = Integer.MAX_VALUE;
    private int maxX = 0;
    private int minY = Integer.MAX_VALUE;
    private int maxY = 0;

    public void addRockStructure(String input) {
        RockStructure rockStructure = new RockStructure(input);
        Hashtable<String, String> rockStructureLine = rockStructure.getStructure();
        caveElements.putAll(rockStructureLine);
        Set<String> rockStructureLinePoints = rockStructureLine.keySet();
        for (String coordinateStr : rockStructureLinePoints) {
            adjustMinMaxCoordinates(coordinateStr);
        }
    }

    public void printCaveElements() {
        final int allowance = 5;
        // print indicator
        for (int col = minX - allowance; col < maxX + allowance; col++) {
            if (col == 500) {
                System.out.print("+");
            }
            else {
                System.out.print(" ");
            }
        }
        System.out.println();

        for (int row = 0; row < maxY + allowance; row++) {
            for (int col = minX - allowance; col < maxX + allowance; col++) {
                String element = caveElements.get(Coordinate.buildName(col, row));
                System.out.print(Objects.requireNonNullElse(element, Air));
            }
            System.out.println();
        }
    }

    private void adjustMinMaxCoordinates(String coordinateStr) {
        Coordinate c = Coordinate.parseName(coordinateStr);
        if (c != null) {
            if (c.getX() < minX) {
                minX = c.getX();
            }
            else if (c.getX() > maxX) {
                maxX = c.getX();
            }
            if (c.getY() < minY) {
                minY = c.getY();
            }
            else if (c.getY() > maxY) {
                maxY = c.getY();
            }
        }
    }

    public Coordinate pourSand() {
        Coordinate c = Sand.fall(caveElements);
        caveElements.put(c.getName(), SandChar);
        adjustMinMaxCoordinates(c.getName());
        return c;
    }

    public void addCaveFloor() {
        caveElements.putAll(Line.draw(new Coordinate(minX - 200, maxY + 2), new Coordinate(maxX + 200, maxY + 2), Cave.Rock));
    }
}
