package aoc2025.day4;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ToiletPaperGrid
{
    private static class Coordinate
    {
        static List<String> getSurroundingCoordinates(String referenceCoordinate)
        {
            String[] coordinates = referenceCoordinate.split(",");
            int x = Integer.parseInt(coordinates[0]);
            int y = Integer.parseInt(coordinates[1]);

            return List.of(
                    getCoordinateString(x - 1, y - 1),
                    getCoordinateString(x, y - 1),
                    getCoordinateString(x + 1, y - 1),
                    getCoordinateString(x - 1, y),
                    getCoordinateString(x + 1, y),
                    getCoordinateString(x - 1, y + 1),
                    getCoordinateString(x, y + 1),
                    getCoordinateString(x + 1, y + 1)
            );
        }

        static String getCoordinateString(int x, int y)
        {
            return String.format("%d,%d", x, y);
        }
    }

    private static final char TOILET_PAPER = '@';
    private static final int FORKLIFT_LIMIT = 4;

    private Set<String> toiletPaperCoordinates = new HashSet<>();

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
                        toiletPaperCoordinates.add(Coordinate.getCoordinateString(x, y));
                    }
                }
            }
        }
    }

    Set<String> getToiletPaperUntilLimit()
    {
        Set<String> collectedToiletPapers = new HashSet<>();
        Set<String> toiletPapersRemoved;

        do {
            Set<String> toiletPaperCoordinatesCopy = new HashSet<>(toiletPaperCoordinates);
            toiletPapersRemoved = getToiletPaperByForklift();
            collectedToiletPapers.addAll(toiletPapersRemoved);
            System.out.println("Collected toilet papers: " + toiletPapersRemoved.size());
            toiletPaperCoordinatesCopy.removeAll(toiletPapersRemoved);
            toiletPaperCoordinates = toiletPaperCoordinatesCopy;
        } while (!toiletPapersRemoved.isEmpty());

        return collectedToiletPapers;
    }

    Set<String> getToiletPaperByForklift()
    {
        Set<String> result = new HashSet<>();
        for (String toiletPaperCoordinate : toiletPaperCoordinates)
        {
            if (getToiletPaperAroundCoordinate(toiletPaperCoordinate).size() < FORKLIFT_LIMIT)
            {
                result.add(toiletPaperCoordinate);
            }
        }
        return result;
    }

    Set<String> getToiletPaperAroundCoordinate(String coordinate)
    {
        List<String> surroundingCoordinates = Coordinate.getSurroundingCoordinates(coordinate);
        return toiletPaperCoordinates.stream().filter(surroundingCoordinates::contains).collect(Collectors.toSet());
    }
}
