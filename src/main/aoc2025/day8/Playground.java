package aoc2025.day8;

import java.util.*;

public class Playground {
    private static final Map<String, Double> connectionDistances = new HashMap<>();
    private static final Set<String> junctionBoxes = new HashSet<>();

    Playground(String input)
    {
        String[] lines = input.split("\\n");
        for (int i = 0; i < lines.length - 1; i++)
        {
            String point1 = lines[i];
            junctionBoxes.add(point1);
            String[] point1Str = point1.split(",");
            JunctionBox junctionBox1 = new JunctionBox(Integer.parseInt(point1Str[0]), Integer.parseInt(point1Str[1]), Integer.parseInt(point1Str[2]));
            for (int j = i + 1; j < lines.length; j++)
            {
                String point2 = lines[j];
                junctionBoxes.add(point2);
                String[] point2Str = point2.split(",");
                JunctionBox junctionBox2 = new JunctionBox(Integer.parseInt(point2Str[0]), Integer.parseInt(point2Str[1]), Integer.parseInt(point2Str[2]));
                connectionDistances.put(point1 + "-" + point2, junctionBox1.calculateDistance(junctionBox2));
            }
        }
    }

    private List<String> getSortedConnections()
    {
        return connectionDistances.entrySet().stream().sorted(Comparator.comparingDouble(Map.Entry::getValue)).map(Map.Entry::getKey).toList();
    }

    List<Set<String>> getCircuits(int rank)
    {
        List<Set<String>> circuits = new ArrayList<>();
        List<String> sortedConnections = getSortedConnections();
        Set<String> tempJunctionBoxes = new HashSet<>(junctionBoxes);

        for (int i = 0; i < rank; i++)
        {
            String sortedConnection = sortedConnections.get(i);
            String[] points = sortedConnection.split("-");
            String point1 = points[0];
            String point2 = points[1];

            Set<String> point1Circuit = circuits.stream().filter(s -> s.contains(point1)).findFirst().orElse(null);
            Set<String> point2Circuit = circuits.stream().filter(s -> s.contains(point2)).findFirst().orElse(null);
            if (point1Circuit == null && point2Circuit == null)
            {
                Set<String> newCircuit = new HashSet<>();
                newCircuit.add(point1);
                newCircuit.add(point2);
                circuits.add(newCircuit);
            }
            else if (point1Circuit != null && point2Circuit != null && point1Circuit != point2Circuit)
            {
                point1Circuit.addAll(point2Circuit);
                point1Circuit.add(point1);
                point1Circuit.add(point2);
                circuits.remove(point2Circuit);
            }
            else if (point1Circuit != null && point2Circuit == null)
            {
                point1Circuit.add(point2);
            }
            else if (point1Circuit == null)
            {
                point2Circuit.add(point1);
            }
            tempJunctionBoxes.remove(point1);
            tempJunctionBoxes.remove(point2);
            if (tempJunctionBoxes.isEmpty())
            {
                break;
            }
        }

        for (String tempJunctionBox : tempJunctionBoxes)
        {
            Set<String> tempCircuit = new HashSet<>();
            tempCircuit.add(tempJunctionBox);
            circuits.add(tempCircuit);
        }

        return circuits.stream().sorted((s1, s2) -> s2.size() - s1.size()).toList();
    }

    String getCompletingConnection()
    {
        List<Set<String>> circuits = new ArrayList<>();
        List<String> sortedConnections = getSortedConnections();
        Set<String> tempJunctionBoxes = new HashSet<>(junctionBoxes);

        for (int i = 0; i < sortedConnections.size(); i++)
        {
            String sortedConnection = sortedConnections.get(i);
            String[] points = sortedConnection.split("-");
            String point1 = points[0];
            String point2 = points[1];

            Set<String> point1Circuit = circuits.stream().filter(s -> s.contains(point1)).findFirst().orElse(null);
            Set<String> point2Circuit = circuits.stream().filter(s -> s.contains(point2)).findFirst().orElse(null);
            if (point1Circuit == null && point2Circuit == null)
            {
                Set<String> newCircuit = new HashSet<>();
                newCircuit.add(point1);
                newCircuit.add(point2);
                circuits.add(newCircuit);
            }
            else if (point1Circuit != null && point2Circuit != null && point1Circuit != point2Circuit)
            {
                point1Circuit.addAll(point2Circuit);
                point1Circuit.add(point1);
                point1Circuit.add(point2);
                circuits.remove(point2Circuit);
            }
            else if (point1Circuit != null && point2Circuit == null)
            {
                point1Circuit.add(point2);
            }
            else if (point1Circuit == null)
            {
                point2Circuit.add(point1);
            }
            tempJunctionBoxes.remove(point1);
            tempJunctionBoxes.remove(point2);
            if (tempJunctionBoxes.isEmpty())
            {
                return sortedConnection;
            }
        }

        return "";
    }
}
