package aoc2018.day6;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Field {
    private final Map<String, Set<String>> points = new HashMap<>();

    public void addPoint(String coordinate)
    {
        points.put(coordinate, new HashSet<>());
    }

    public Set<String> getPoints()
    {
        return points.keySet();
    }

    public String getClosestPoint(String coordinate)
    {
        Set<String> pointSet = points.keySet();
        Coordinate refPoint = Coordinate.createCoordinate(coordinate); 
        int minDistance = Integer.MAX_VALUE;
        String closestPoint = coordinate;
        for (String currPointStr : pointSet)
        {
            if (!currPointStr.equals(coordinate))
            {
                Coordinate currPoint = Coordinate.createCoordinate(currPointStr);
                int distance = refPoint.getDistance(currPoint);
                if (distance < minDistance)
                {
                    minDistance = distance;
                    closestPoint = currPointStr;
                }
            }
        }
        return closestPoint;
    }

}
