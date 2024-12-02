package aoc2024.day1;

import java.util.*;

public class NumberList
{
    private final List<Integer> list1 = new ArrayList<>();
    private final List<Integer> list2 = new ArrayList<>();
    private final Map<Integer, Integer> similarityMap = new HashMap<>();

    public NumberList(String input)
    {
        String[] lines = input.split("\\n");
        for (String line : lines)
        {
            String[] parts = line.split("\\s+");
            if (parts.length == 2)
            {
                list1.add(Integer.parseInt(parts[0]));
                list2.add(Integer.parseInt(parts[1]));
                if (!similarityMap.containsKey(Integer.parseInt(parts[1])))
                {
                    similarityMap.put(Integer.parseInt(parts[1]), 1);
                }
                else {
                    similarityMap.put(Integer.parseInt(parts[1]), similarityMap.get(Integer.parseInt(parts[1])) + 1);
                }
            }
        }
        Collections.sort(list1);
        Collections.sort(list2);
    }

    public int getTotalDistanceByDifference()
    {
        int sum = 0;
        for (int i = 0; i < list1.size(); i++)
        {
            Integer i1 = list1.get(i);
            Integer i2 = list2.get(i);
            if (i1 != null && i2 != null)
            {
                if (i1 > i2)
                {
                    sum += i1 - i2;
                }
                else
                {
                    sum += i2 - i1;
                }
            }
        }
        return sum;
    }

    public int getTotalDistanceBySimilarity()
    {
        int sum = 0;
        for (Integer i1 : list1) {
            if (similarityMap.containsKey(i1)) {
                sum += i1 * similarityMap.get(i1);
            }
        }
        return sum;
    }
}
