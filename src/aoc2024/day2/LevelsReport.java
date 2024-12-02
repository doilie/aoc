package aoc2024.day2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class LevelsReport
{
    private static final int SAFE_DELTA = 3;
    private enum LevelsMode
    {
        Increasing,
        Decreasing,
        Invalid
    }
    private final List<Integer> numbers = new ArrayList<>();

    public static LevelsReport getLevelsReportFromString(String levelsReport)
    {
        return new LevelsReport(Arrays.stream(levelsReport.split("\\s")).map(Integer::parseInt).toList());
    }

    public LevelsReport(List<Integer> numbers)
    {
        this.numbers.addAll(numbers);
    }

    public boolean isSafe()
    {
        LevelsMode mode = getLevelsMode();
        if (mode == LevelsMode.Invalid)
        {
            return false;
        }
        for (int i = 0; i < numbers.size() - 1; i++)
        {
            int currNum = numbers.get(i);
            int nextNum = numbers.get(i + 1);
            if (Objects.equals(currNum, nextNum))
            {
                return false;
            }
            else if (mode == LevelsMode.Decreasing && (currNum < nextNum || (currNum - nextNum) > SAFE_DELTA))
            {
                return false;
            }
            else if (mode == LevelsMode.Increasing && (currNum > nextNum || (nextNum - currNum) > SAFE_DELTA))
            {
                return false;
            }
        }
        return true;
    }

    private LevelsMode getLevelsMode()
    {
        if (numbers.size() > 1)
        {
            if (numbers.get(0) > numbers.get(1))
            {
                return LevelsMode.Decreasing;
            }
            else if (numbers.get(0) < numbers.get(1))
            {
                return LevelsMode.Increasing;
            }
        }
        return LevelsMode.Invalid;
    }

    private List<List<Integer>> getNumberListPermutations()
    {
        List<List<Integer>> permutations = new ArrayList<>();
        for (int i = 0; i < numbers.size(); i++)
        {
            List<Integer> permutation = new ArrayList<>(numbers);
            permutation.remove(i);
            permutations.add(permutation);
        }
        return permutations;
    }

    public boolean isSafeWithoutOneLevel()
    {
        if (this.isSafe())
        {
            return true;
        }

        List<List<Integer>> permutations = getNumberListPermutations();

        for (List<Integer> permutation : permutations)
        {
            LevelsReport report = new LevelsReport(permutation);
            if (report.isSafe())
            {
                return true;
            }
        }

        return false;
    }
}
