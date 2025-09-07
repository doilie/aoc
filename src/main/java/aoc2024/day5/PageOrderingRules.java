package aoc2024.day5;

import java.util.*;

public class PageOrderingRules implements Comparator<Integer>
{
    private final Map<Integer, Set<Integer>> greaterThanMap = new HashMap<>();
    private final Map<Integer, Set<Integer>> lessThanMap = new HashMap<>();

    public PageOrderingRules(String input)
    {
        String[] lines = input.split("\\n");
        for (String line : lines)
        {
            String[] numbers = line.split("\\|");
            if (numbers.length == 2)
            {
                int numberBefore = Integer.parseInt(numbers[0]);
                int numberAfter = Integer.parseInt(numbers[1]);
                addToGreaterThan(numberBefore, numberAfter);
                addToLessThan(numberAfter, numberBefore);
            }
        }
    }

    private void addToGreaterThan(int number, int numberBefore)
    {
        if (!greaterThanMap.containsKey(number))
        {
            greaterThanMap.put(number, new HashSet<>());
        }
        greaterThanMap.get(number).add(numberBefore);
    }

    private void addToLessThan(int number, int numberAfter)
    {
        if (!lessThanMap.containsKey(number))
        {
            lessThanMap.put(number, new HashSet<>());
        }
        lessThanMap.get(number).add(numberAfter);
    }

    @Override
    public int compare(Integer o1, Integer o2)
    {
        if (lessThanMap.get(o1) != null && lessThanMap.get(o1).contains(o2))
        {
            return 1;
        }
        else if (greaterThanMap.get(o1) != null && greaterThanMap.get(o1).contains(o2))
        {
            return -1;
        }
        return 0;
    }
}
