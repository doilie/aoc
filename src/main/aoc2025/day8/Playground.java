package aoc2025.day8;

import java.util.*;
import java.util.stream.Collectors;

public class Playground {
    private static final Map<String, Double> connectionDistances = new HashMap<>();
    private static final Set<String> circuits = new HashSet<>();

    Playground(String input)
    {
        String[] lines = input.split("\\n");
        for (int i = 0; i < lines.length; i++)
        {
            String point1 = lines[i];
            String[] point1Str = point1.split(",");
            JunctionBox junctionBox1 = new JunctionBox(Integer.parseInt(point1Str[0]), Integer.parseInt(point1Str[1]), Integer.parseInt(point1Str[2]));
            for (int j = i + 1; j < lines.length; j++)
            {
                String point2 = lines[j];
                String[] point2Str = point2.split(",");
                JunctionBox junctionBox2 = new JunctionBox(Integer.parseInt(point2Str[0]), Integer.parseInt(point2Str[1]), Integer.parseInt(point2Str[2]));
                connectionDistances.put(point1 + "-" + point2, junctionBox1.calculateDistance(junctionBox2));
            }
        }
        groupJunctionBoxesToCircuits();
    }

    private List<String> getSortedConnections()
    {
        return connectionDistances.entrySet().stream().sorted(Comparator.comparingDouble(Map.Entry::getValue)).map(Map.Entry::getKey).toList();
    }

    private void groupJunctionBoxesToCircuits()
    {
        List<String> sortedConnections = getSortedConnections();
        System.out.println(sortedConnections);
        for (String connection : sortedConnections) {
            String[] points = connection.split("-");
            String point1 = points[0];
            String point2 = points[1];
            boolean p1 = false;
            boolean p2 = false;
            for (String circuit : circuits) {
                if (circuit.contains(point1)) {
                    circuits.add(circuit + "-" + point2);
                    circuits.remove(circuit);
                    p2 = true;
                    break;
                }
                if (circuit.contains(point2)) {
                    circuits.add(circuit + "-" + point1);
                    circuits.remove(circuit);
                    p1 = true;
                    break;
                }
            }
            if (!p1 && !p2)
            {
                circuits.add(connection);
            }
        }
    }

    Set<String> getCircuits()
    {
        return circuits;
    }
}
