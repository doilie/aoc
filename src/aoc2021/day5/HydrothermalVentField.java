package aoc2021.day5;

import java.util.*;

public class HydrothermalVentField {
    private static String getPosition(int x, int y) {
        return x + "," + y;
    }

    private final Map<String, Integer> positions = new HashMap<>();

    public void drawLine(String input, boolean includeDiagonal)
    {
        String[] tokens = input.split(" -> ");
        if (tokens.length == 2)
        {
            String[] startPoint = tokens[0].split(",");
            if (startPoint.length == 2)
            {
                String[] endPoint = tokens[1].split(",");
                if (endPoint.length == 2)
                {
                    if (includeDiagonal)
                    {
                        drawDiagonalLine(Integer.parseInt(startPoint[0]), Integer.parseInt(startPoint[1]), Integer.parseInt(endPoint[0]), Integer.parseInt(endPoint[1]));
                    }
                    drawNonDiagonalLine(Integer.parseInt(startPoint[0]), Integer.parseInt(startPoint[1]), Integer.parseInt(endPoint[0]), Integer.parseInt(endPoint[1]));
                }
            }
        }
    }

    public Integer getPositionCount(int x, int y) {
        return positions.get(getPosition(x, y));
    }

    public long getNumberOfPointsWhereLinesOverlap()
    {
        return positions.values().stream().filter(num -> num > 1).count();
    }

    private void markPosition(int x, int y)
    {
        Integer positionValue = positions.get(getPosition(x, y));
        if (positionValue == null)
        {
            positionValue = 0;
        }
        positions.put(getPosition(x, y), positionValue + 1);
    }

    private void drawDiagonalLine(int x1, int y1, int x2, int y2)
    {
        if (x1 != x2 && y1 != y2)
        {
            int startingX = x1;
            int endingX = x2;
            int startingY = y1;
            int endingY = y2;
            if (y1 > y2)
            {
                startingY = y2;
                endingY = y1;
                startingX = x2;
                endingX = x1;
            }

            int x = startingX;
            for (int y = startingY; y <= endingY; y++)
            {
                if (startingX <= endingX)
                {
                    markPosition(x++, y);
                }
                else
                {
                    markPosition(x--, y);
                }
            }
        }
    }

    private void drawNonDiagonalLine(int x1, int y1, int x2, int y2)
    {
        if (x1 == x2) // left or right
        {
            int startingY = Math.min(y1, y2);
            int endingY = Math.max(y1, y2);
            for (int y = startingY; y <= endingY; y++)
            {
                markPosition(x1, y);
            }
        }
        else if (y1 == y2) // up or down
        {
            int startingX = Math.min(x1, x2);
            int endingX = Math.max(x1, x2);
            for (int x = startingX; x <= endingX; x++)
            {
                markPosition(x, y1);
            }
        }
    }

}
