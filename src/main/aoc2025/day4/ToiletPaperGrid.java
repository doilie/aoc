package aoc2025.day4;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ToiletPaperGrid
{
    private static class Coordinate
    {
        int x;
        int y;

        Coordinate(int x, int y)
        {
            this.x = x;
            this.y = y;
        }

        Coordinate(String coordinate)
        {
            String[] coordinates = coordinate.split(",");
            this.x = Integer.parseInt(coordinates[0]);
            this.y = Integer.parseInt(coordinates[1]);
        }

        List<String> getSurroundingCoordinates()
        {
            return List.of(
                    new Coordinate(this.x - 1, this.y - 1).toString(),
                    new Coordinate(this.x, this.y - 1).toString(),
                    new Coordinate(this.x + 1, this.y - 1).toString(),
                    new Coordinate(this.x - 1, this.y).toString(),
                    new Coordinate(this.x + 1, this.y).toString(),
                    new Coordinate(this.x - 1, this.y + 1).toString(),
                    new Coordinate(this.x, this.y + 1).toString(),
                    new Coordinate(this.x + 1, this.y + 1).toString()
            );
        }

        @Override
        public String toString()
        {
            return x + "," + y;
        }
    }

    static final char TOILET_PAPER = '@';

    private final Set<String> toiletPaperCoordinates = new HashSet<>();

    ToiletPaperGrid(String input)
    {
        String[] lines = input.split("\\n");
        for (int y = 0; y < lines.length; y++)
        {
            String line = lines[y];
            if (!line.isEmpty())
            {
                for (int x = 0; x < line.length(); x++)
                {
                    if (line.charAt(x) == TOILET_PAPER)
                    {
                        toiletPaperCoordinates.add(new Coordinate(x, y).toString());
                    }
                }
            }
        }
    }

    Set<String> getToiletPaperWithLessThanNumAdjacent(int count)
    {
        Set<String> result = new HashSet<>();
        for (String toiletPaperCoordinate : toiletPaperCoordinates)
        {
            if (getToiletPaperAroundCoordinate(toiletPaperCoordinate).size() < count)
            {
                result.add(toiletPaperCoordinate);
            }
        }
        return result;
    }

    Set<String> getToiletPaperAroundCoordinate(String coordinate)
    {
        Coordinate toiletPaperCoordinate = new Coordinate(coordinate);
        List<String> surroundingCoordinates = toiletPaperCoordinate.getSurroundingCoordinates();
        return toiletPaperCoordinates.stream().filter(surroundingCoordinates::contains).collect(Collectors.toSet());
    }
}
