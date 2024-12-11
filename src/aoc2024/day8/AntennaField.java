package aoc2024.day8;

import lib.Position;

import java.util.*;

public class AntennaField
{
    private static final String FREE = ".";

    private final Map<String, AntennaGroup> antennaGroups;
    private final Set<String> antinodePositions = new HashSet<>();

    public AntennaField(String input)
    {
        antennaGroups = getAntennas(input);
        for (AntennaGroup group : antennaGroups.values())
        {
            antinodePositions.addAll(group.getAntinodes());
        }
    }

    public AntennaGroup getAntennaGroup(String antenna)
    {
        return antennaGroups.get(antenna);
    }

    public Set<String> getAntinodePositions()
    {
        return antinodePositions;
    }

    public static Map<String, AntennaGroup> getAntennas(String input)
    {
        Map<String, List<String>> antennaPositions = new HashMap<>();
        String[] lines = input.split("\\n");
        for (int y = 0; y < lines.length; y++)
        {
            String line = lines[y];
            String[] values = line.split("");
            for (int x = 0; x < values.length; x++)
            {
                String value = values[x];
                if (!value.equals(FREE))
                {
                    String position = Position.getPosition(x, y);
                    if (!antennaPositions.containsKey(value))
                    {
                        antennaPositions.put(value, new ArrayList<>());
                    }
                    antennaPositions.get(value).add(position);
                }
            }
        }
        Map<String, AntennaGroup> antennas = new HashMap<>();
        for (String value : antennaPositions.keySet())
        {
            antennas.put(value, new AntennaGroup(antennaPositions.get(value)));
        }
        return antennas;
    }

}
