package aoc2024.day8;

import lib.Position;

import java.util.*;
import java.util.stream.Collectors;

public class AntennaGroup
{
    private static int size = 10;

    private final List<String> antennaPositions = new ArrayList<>();
    private final Set<List<String>> antennaPairs = new HashSet<>();

    public AntennaGroup(List<String> positions)
    {
        antennaPositions.addAll(positions);
        createAntennaPairs();
    }

    private void createAntennaPairs()
    {
        if (antennaPositions.size() > 1) {
            for (String position1 : antennaPositions) {
                for (String position2 : antennaPositions) {
                    if (!position1.equals(position2)) {
                        List<String> positions = new ArrayList<>(List.of(position1, position2));
                        Collections.sort(positions);
                        antennaPairs.add(positions);
                    }
                }
            }
        }
    }

    public Set<List<String>> getAntennaPairs()
    {
        return antennaPairs;
    }

    public Set<String> getAntinodes()
    {
        Set<String> antinodes = new HashSet<>();
        for (List<String> antennaPair : antennaPairs)
        {
            antinodes.addAll(getAntinodePositions(antennaPair.get(0), antennaPair.get(1)).stream().filter(s -> !antennaPositions.contains(s)).collect(Collectors.toSet()));
        }
        return antinodes;
    }

    private static String getPairDifference(String position1, String position2)
    {
        int x1 = Position.getX(position1);
        int y1 = Position.getY(position1);
        int x2 = Position.getX(position2);
        int y2 = Position.getY(position2);
        return Position.getPosition(x2 - x1, y2 - y1);
    }

    public static Set<String> getAntinodePositions(String position1, String position2)
    {
        String difference = getPairDifference(position1, position2);
        int xDiff = Position.getX(difference);
        int yDiff = Position.getY(difference);
        Set<String> antinodePositions = new HashSet<>();
        if (isWithinSize(Position.getX(position1) - xDiff) && isWithinSize(Position.getY(position1) - yDiff))
        {
            antinodePositions.add(Position.getPosition(Position.getX(position1) - xDiff, Position.getY(position1) - yDiff));
        }
        if (isWithinSize(Position.getX(position2) + xDiff) && isWithinSize(Position.getY(position2) + yDiff))
        {
            antinodePositions.add(Position.getPosition(Position.getX(position2) + xDiff, Position.getY(position2) + yDiff));
        }
        return antinodePositions;
    }

    private static boolean isWithinSize(int num)
    {
        return num >= 0 && num < size;
    }

    public static void setSize(int size)
    {
        AntennaGroup.size = size;
    }
}
