package aoc2019.day3;

import java.util.*;
import java.util.stream.Collectors;

public class WirePath {
    private static String getPosition(int x, int y) {
        return x + "," + y;
    }
    private static int getManhattanDistance(String position)
    {
        String[] xy = position.split(",");
        if (xy.length == 2)
        {
            return Math.abs(Integer.parseInt(xy[0])) + Math.abs(Integer.parseInt(xy[1]));
        }
        return 0;
    }

    private int currentX;
    private int currentY;
    private final Set<String> visitedPositions = new HashSet<>();

    public void move(String instruction) {
        if (instruction.length() >= 2) {
            char direction = instruction.charAt(0);
            int steps = Integer.parseInt(instruction.substring(1));

            switch (direction) {
                case 'R':
                    for (int x = currentX; x < (currentX + steps); x++) {
                        moveToPosition(x, currentY);
                    }
                    currentX = currentX + steps;
                    break;
                case 'L':
                    for (int x = currentX; x > (currentX - steps); x--) {
                        moveToPosition(x, currentY);
                    }
                    currentX = currentX - steps;
                    break;
                case 'D':
                    for (int y = currentY; y < (currentY + steps); y++) {
                        moveToPosition(currentX, y);
                    }
                    currentY = currentY + steps;
                    break;
                case 'U':
                    for (int y = currentY; y > (currentY - steps); y--) {
                        moveToPosition(currentX, y);
                    }
                    currentY = currentY - steps;
                    break;
            }
        }
    }

    public int getCurrentX() {
        return currentX;
    }

    public int getCurrentY() {
        return currentY;
    }

    private void moveToPosition(int x, int y) {
        String position = getPosition(x, y);
        visitedPositions.add(position);
    }

    public static Set<String> getPositionsWithIntersection(List<WirePath> wirePaths)
    {
        if (!wirePaths.isEmpty())
        {
            Set<String> positionsWithIntersection = wirePaths.get(0).visitedPositions;

            for (int i = 1; i < wirePaths.size(); i++)
            {
                positionsWithIntersection = positionsWithIntersection.stream().filter(wirePaths.get(i).visitedPositions::contains).collect(Collectors.toSet());
            }
            positionsWithIntersection.remove("0,0");
            return positionsWithIntersection;
        }
        return Set.of();
    }

    public static int getClosestDistanceFromCentralPort(List<WirePath> wirePaths)
    {
        Set<String> positionsWithIntersection = getPositionsWithIntersection(wirePaths);
        OptionalInt result = positionsWithIntersection.stream().map(WirePath::getManhattanDistance).mapToInt(a -> a).min();
        if (result.isPresent())
        {
            return result.getAsInt();
        }
        return 0;
    }
}
