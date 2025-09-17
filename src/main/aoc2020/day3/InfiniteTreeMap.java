package aoc2020.day3;

import java.util.HashMap;
import java.util.Map;

public class InfiniteTreeMap
{
    public static final char OPEN = '.';
    public static final char TREE = '#';

    private static String getPosition(int x, int y) {
        return x + "," + y;
    }

    private final Map<String, Character> treeMap = new HashMap<>();
    private int treeWidth;
    private int treeHeight;

    public InfiniteTreeMap(String input)
    {
        String[] lines = input.trim().split("\\n");
        if (lines.length > 0)
        {
            treeHeight = lines.length;
            treeWidth = lines[0].length();

            for (int y = 0; y < lines.length; y++)
            {
                for (int x = 0; x < lines[y].length(); x++)
                {
                    treeMap.put(getPosition(x, y), lines[y].charAt(x));
                }
            }
        }
    }

    public int countTreesInSlope(int x, int y)
    {
        int currX = 0;
        int currY = 0;
        int treeCount = 0;
        while (currY < treeHeight)
        {
            if (getValueInPosition(currX, currY) == InfiniteTreeMap.TREE)
            {
                treeCount++;
            }
            currX += x;
            currY += y;
        }
        return treeCount;
    }

    public char getValueInPosition(int x, int y)
    {
        int modX = x % treeWidth;
        return treeMap.get(getPosition(modX, y));
    }
}
