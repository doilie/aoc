package aoc2016.day3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TriangleChecker {
    private final List<Integer> sides = new ArrayList<>();
    private final static int numberOfSides = 3;

    public static TriangleChecker createOneTriangleFromRow(String sidesString)
    {
        return new TriangleChecker(Arrays.stream(
                sidesString.split("\\s+"))
                        .filter(s -> !s.isEmpty())
                        .map(Integer::parseInt).toList());
    }

    public static List<TriangleChecker> createThreeTrianglesFromRows(String sidesString)
    {
        String[] rows = sidesString.split("\\n");
        if (rows.length == numberOfSides)
        {
            List<TriangleChecker> tempList = Arrays.stream(rows).map(TriangleChecker::createOneTriangleFromRow).toList();
            List<TriangleChecker> triangleCheckers = new ArrayList<>();
            for (int i = 0; i < numberOfSides; i++)
            {
                int finalI = i;
                List<Integer> sides = tempList.stream().map(tc -> tc.getSides()[finalI]).toList();
                triangleCheckers.add(new TriangleChecker(sides));
            }
            return triangleCheckers;
        }
        return Collections.emptyList();
    }

    public TriangleChecker(List<Integer> sidesList)
    {
        sides.addAll(sidesList);
    }

    public Integer[] getSides()
    {
        return sides.toArray(new Integer[0]);
    }

    public boolean isValid()
    {

        if (hasThreeSides())
        {
            for (int i = 0; i < numberOfSides; i++)
            {
                int nextIdx = (i + 1) % numberOfSides;
                int idxToCompare = (i + 2) % numberOfSides;
                int valueToCompare = sides.get(idxToCompare);
                int sum = sides.get(i) + sides.get(nextIdx);
                if (sum <= valueToCompare)
                {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private boolean hasThreeSides()
    {
        return sides.size() == numberOfSides;
    }
}
