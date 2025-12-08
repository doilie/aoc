package aoc2025.day7;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TachyonManifold {
    private static class TachyonCoordinate
    {
        static String getCoordinateString(int x, int y)
        {
            return String.format("%d,%d", x, y);
        }
        static int getX(String coordinate)
        {
            String[] coordinates = coordinate.split(",");
            return Integer.parseInt(coordinates[0]);
        }
        static int getY(String coordinate)
        {
            String[] coordinates = coordinate.split(",");
            return Integer.parseInt(coordinates[1]);
        }
    }

    private static final String START = "S";
    private static final String SPLITTER = "^";

    private String startCoordinate;
    private final Set<String> splitterCoordinates = new HashSet<>();
    private int lastSplitterRow;

    TachyonManifold(String input)
    {
        findStartCoordinate(input);
        findTachyonCoordinates(input);
    }

    private void findStartCoordinate(String input)
    {
        String[] lines = input.split("\\n");
        for (int y = 0; y < lines.length; y++)
        {
            String line = lines[y];
            if (line.contains(START))
            {
                startCoordinate = TachyonCoordinate.getCoordinateString(line.indexOf(START), y);
                break;
            }
        }
    }

    private void findTachyonCoordinates(String input)
    {
        String[] lines = input.split("\\n");
        for (int y = 0; y < lines.length; y++)
        {
            String line = lines[y];
            int splitterIndex = line.indexOf(SPLITTER);
            while (splitterIndex != -1)
            {
                splitterCoordinates.add(TachyonCoordinate.getCoordinateString(splitterIndex, y));
                splitterIndex = line.indexOf(SPLITTER, splitterIndex + 1);
                lastSplitterRow = y;
            }
        }
    }

    String getStartCoordinate()
    {
        return startCoordinate;
    }

    Set<String> getSplitterCoordinates()
    {
        return splitterCoordinates;
    }

    int splitBeam()
    {
        int splitCount = 0;
        Set<Integer> beamColumns = new HashSet<>();
        beamColumns.add(TachyonCoordinate.getX(startCoordinate));
        for (int i = 1; i <= lastSplitterRow; i++)
        {
            int finalI = i;
            List<Integer> splitterColumns = splitterCoordinates.stream().filter(c -> TachyonCoordinate.getY(c) == finalI).map(TachyonCoordinate::getX).sorted().toList();
            for (int splitterColumn : splitterColumns)
            {
                if (beamColumns.contains(splitterColumn))
                {
                    splitCount++;
                    beamColumns.remove(splitterColumn);
                    int left = splitterColumn - 1;
                    int right = splitterColumn + 1;
                    if (left >= 0)
                    {
                        beamColumns.add(left);
                    }
                    beamColumns.add(right);
                }
            }
        }
        return splitCount;
    }
}
