package aoc2024.day4;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class XmasGrid
{
    private static String getPosition(int x, int y)
    {
        return x + "," + y;
    }
    private static int getX(String position)
    {
        String[] split = position.split(",");
        return Integer.parseInt(split[0]);
    }
    private static int getY(String position)
    {
        String[] split = position.split(",");
        return Integer.parseInt(split[1]);
    }

    private final Map<String, Character> xmasGridPositions = new HashMap<>();

    public XmasGrid(String input)
    {
        String[] lines = input.split("\\n");
        for (int y = 0; y < lines.length; y++)
        {
            String line = lines[y];
            for (int x = 0; x < line.length(); x++)
            {
                String position = getPosition(x, y);
                xmasGridPositions.put(position, line.charAt(x));
            }
        }
    }

    public int countXmasMatches()
    {
        Set<String> positions = getPositionsWithLetter('X');
        int count = 0;

        for (String position : positions)
        {
            if (checkNorth(position))
            {
                count++;
            }
            if (checkSouth(position))
            {
                count++;
            }
            if (checkWest(position))
            {
                count++;
            }
            if (checkEast(position))
            {
                count++;
            }
            if (checkNorthWest(position))
            {
                count++;
            }
            if (checkSouthWest(position))
            {
                count++;
            }
            if (checkNorthEast(position))
            {
                count++;
            }
            if (checkSouthEast(position))
            {
                count++;
            }
        }

        return count;
    }

    public int countCrossmasMatches()
    {
        Set<String> positions = getPositionsWithLetter('A');
        int count = 0;

        for (String position : positions)
        {
            if (checkCrossMas_Pattern1(position))
            {
                count++;
            }
            if (checkCrossMas_Pattern2(position))
            {
                count++;
            }
            if (checkCrossMas_Pattern3(position))
            {
                count++;
            }
            if (checkCrossMas_Pattern4(position))
            {
                count++;
            }
        }

        return count;
    }

    private Set<String> getPositionsWithLetter(char letter)
    {
        return xmasGridPositions.entrySet().stream().filter(kv -> kv.getValue() == letter).map(Map.Entry::getKey).collect(Collectors.toSet());
    }

    private boolean checkNorth(String position)
    {
        int startingX = getX(position);
        int startingY = getY(position);

        // N
        return xmasGridPositions.get(position) == 'X'
                && xmasGridPositions.get(getPosition(startingX, startingY - 1)) != null && xmasGridPositions.get(getPosition(startingX, startingY - 1)) == 'M'
                && xmasGridPositions.get(getPosition(startingX, startingY - 2)) != null && xmasGridPositions.get(getPosition(startingX, startingY - 2)) == 'A'
                && xmasGridPositions.get(getPosition(startingX, startingY - 3)) != null && xmasGridPositions.get(getPosition(startingX, startingY - 3)) == 'S';
    }

    private boolean checkSouth(String position)
    {
        int startingX = getX(position);
        int startingY = getY(position);

        // S
        return xmasGridPositions.get(position) == 'X'
                && xmasGridPositions.get(getPosition(startingX, startingY + 1)) != null && xmasGridPositions.get(getPosition(startingX, startingY + 1)) == 'M'
                && xmasGridPositions.get(getPosition(startingX, startingY + 2)) != null && xmasGridPositions.get(getPosition(startingX, startingY + 2)) == 'A'
                && xmasGridPositions.get(getPosition(startingX, startingY + 3)) != null && xmasGridPositions.get(getPosition(startingX, startingY + 3)) == 'S';
    }

    private boolean checkWest(String position)
    {
        int startingX = getX(position);
        int startingY = getY(position);

        // W
        return xmasGridPositions.get(position) == 'X'
                && xmasGridPositions.get(getPosition(startingX - 1, startingY)) != null && xmasGridPositions.get(getPosition(startingX - 1, startingY)) == 'M'
                && xmasGridPositions.get(getPosition(startingX - 2, startingY)) != null && xmasGridPositions.get(getPosition(startingX - 2, startingY)) == 'A'
                && xmasGridPositions.get(getPosition(startingX - 3, startingY)) != null && xmasGridPositions.get(getPosition(startingX - 3, startingY)) == 'S';
    }

    private boolean checkEast(String position)
    {
        int startingX = getX(position);
        int startingY = getY(position);

        // E
        return xmasGridPositions.get(position) == 'X'
                && xmasGridPositions.get(getPosition(startingX + 1, startingY)) != null && xmasGridPositions.get(getPosition(startingX + 1, startingY)) == 'M'
                && xmasGridPositions.get(getPosition(startingX + 2, startingY)) != null && xmasGridPositions.get(getPosition(startingX + 2, startingY)) == 'A'
                && xmasGridPositions.get(getPosition(startingX + 3, startingY)) != null && xmasGridPositions.get(getPosition(startingX + 3, startingY)) == 'S';
    }

    private boolean checkNorthWest(String position)
    {
        int startingX = getX(position);
        int startingY = getY(position);

        // NW
        return xmasGridPositions.get(position) == 'X'
                && xmasGridPositions.get(getPosition(startingX - 1, startingY - 1)) != null && xmasGridPositions.get(getPosition(startingX - 1, startingY - 1)) == 'M'
                && xmasGridPositions.get(getPosition(startingX - 2, startingY - 2)) != null && xmasGridPositions.get(getPosition(startingX - 2, startingY - 2)) == 'A'
                && xmasGridPositions.get(getPosition(startingX - 3, startingY - 3)) != null && xmasGridPositions.get(getPosition(startingX - 3, startingY - 3)) == 'S';
    }

    private boolean checkSouthWest(String position)
    {
        int startingX = getX(position);
        int startingY = getY(position);

        // SW
        return xmasGridPositions.get(position) == 'X'
                && xmasGridPositions.get(getPosition(startingX - 1, startingY + 1)) != null && xmasGridPositions.get(getPosition(startingX - 1, startingY + 1)) == 'M'
                && xmasGridPositions.get(getPosition(startingX - 2, startingY + 2)) != null && xmasGridPositions.get(getPosition(startingX - 2, startingY + 2)) == 'A'
                && xmasGridPositions.get(getPosition(startingX - 3, startingY + 3)) != null && xmasGridPositions.get(getPosition(startingX - 3, startingY + 3)) == 'S';
    }

    private boolean checkNorthEast(String position)
    {
        int startingX = getX(position);
        int startingY = getY(position);

        // NE
        return xmasGridPositions.get(position) == 'X'
                && xmasGridPositions.get(getPosition(startingX + 1, startingY - 1)) != null && xmasGridPositions.get(getPosition(startingX + 1, startingY - 1)) == 'M'
                && xmasGridPositions.get(getPosition(startingX + 2, startingY - 2)) != null && xmasGridPositions.get(getPosition(startingX + 2, startingY - 2)) == 'A'
                && xmasGridPositions.get(getPosition(startingX + 3, startingY - 3)) != null && xmasGridPositions.get(getPosition(startingX + 3, startingY - 3)) == 'S';
    }

    private boolean checkSouthEast(String position)
    {
        int startingX = getX(position);
        int startingY = getY(position);

        // SE
        return xmasGridPositions.get(position) == 'X'
                && xmasGridPositions.get(getPosition(startingX + 1, startingY + 1)) != null && xmasGridPositions.get(getPosition(startingX + 1, startingY + 1)) == 'M'
                && xmasGridPositions.get(getPosition(startingX + 2, startingY + 2)) != null && xmasGridPositions.get(getPosition(startingX + 2, startingY + 2)) == 'A'
                && xmasGridPositions.get(getPosition(startingX + 3, startingY + 3)) != null && xmasGridPositions.get(getPosition(startingX + 3, startingY + 3)) == 'S';
    }

    private boolean checkCrossMas_Pattern1(String position)
    {
        int startingX = getX(position);
        int startingY = getY(position);


        return xmasGridPositions.get(position) == 'A'
                && xmasGridPositions.get(getPosition(startingX - 1, startingY - 1)) != null && xmasGridPositions.get(getPosition(startingX - 1, startingY - 1)) == 'M'
                && xmasGridPositions.get(getPosition(startingX + 1, startingY + 1)) != null && xmasGridPositions.get(getPosition(startingX + 1, startingY + 1)) == 'S'
                && xmasGridPositions.get(getPosition(startingX - 1, startingY + 1)) != null && xmasGridPositions.get(getPosition(startingX - 1, startingY + 1)) == 'M'
                && xmasGridPositions.get(getPosition(startingX + 1, startingY - 1)) != null && xmasGridPositions.get(getPosition(startingX + 1, startingY - 1)) == 'S';
    }

    private boolean checkCrossMas_Pattern2(String position)
    {
        int startingX = getX(position);
        int startingY = getY(position);


        return xmasGridPositions.get(position) == 'A'
                && xmasGridPositions.get(getPosition(startingX - 1, startingY - 1)) != null && xmasGridPositions.get(getPosition(startingX - 1, startingY - 1)) == 'S'
                && xmasGridPositions.get(getPosition(startingX + 1, startingY + 1)) != null && xmasGridPositions.get(getPosition(startingX + 1, startingY + 1)) == 'M'
                && xmasGridPositions.get(getPosition(startingX - 1, startingY + 1)) != null && xmasGridPositions.get(getPosition(startingX - 1, startingY + 1)) == 'S'
                && xmasGridPositions.get(getPosition(startingX + 1, startingY - 1)) != null && xmasGridPositions.get(getPosition(startingX + 1, startingY - 1)) == 'M';
    }

    private boolean checkCrossMas_Pattern3(String position)
    {
        int startingX = getX(position);
        int startingY = getY(position);


        return xmasGridPositions.get(position) == 'A'
                && xmasGridPositions.get(getPosition(startingX - 1, startingY - 1)) != null && xmasGridPositions.get(getPosition(startingX - 1, startingY - 1)) == 'S'
                && xmasGridPositions.get(getPosition(startingX + 1, startingY + 1)) != null && xmasGridPositions.get(getPosition(startingX + 1, startingY + 1)) == 'M'
                && xmasGridPositions.get(getPosition(startingX - 1, startingY + 1)) != null && xmasGridPositions.get(getPosition(startingX - 1, startingY + 1)) == 'M'
                && xmasGridPositions.get(getPosition(startingX + 1, startingY - 1)) != null && xmasGridPositions.get(getPosition(startingX + 1, startingY - 1)) == 'S';
    }

    private boolean checkCrossMas_Pattern4(String position)
    {
        int startingX = getX(position);
        int startingY = getY(position);


        return xmasGridPositions.get(position) == 'A'
                && xmasGridPositions.get(getPosition(startingX - 1, startingY - 1)) != null && xmasGridPositions.get(getPosition(startingX - 1, startingY - 1)) == 'M'
                && xmasGridPositions.get(getPosition(startingX + 1, startingY + 1)) != null && xmasGridPositions.get(getPosition(startingX + 1, startingY + 1)) == 'S'
                && xmasGridPositions.get(getPosition(startingX - 1, startingY + 1)) != null && xmasGridPositions.get(getPosition(startingX - 1, startingY + 1)) == 'S'
                && xmasGridPositions.get(getPosition(startingX + 1, startingY - 1)) != null && xmasGridPositions.get(getPosition(startingX + 1, startingY - 1)) == 'M';
    }
}
