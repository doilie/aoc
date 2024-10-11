package aoc2015.day3;

import java.util.HashSet;
import java.util.Set;

public class Sphere {
    private static String getPosition(int x, int y)
    {
        return x + "," + y;
    }

    private final Set<String> visitedPositions = new HashSet<>();

    private int currentX = 0;
    private int currentY = 0;

    public Sphere()
    {
        visitedPositions.add(getPosition(currentX, currentY));
    }

    public void move(String direction)
    {
        switch(direction)
        {
            case ">": currentX++; break;
            case "<": currentX--; break;
            case "^": currentY--; break;
            case "v": currentY++; break;
        }

        String currentPosition = getPosition(currentX, currentY);
        visitedPositions.add(currentPosition);
    }

    public Set<String> getVisitedPositions()
    {
        return visitedPositions;
    }

}
