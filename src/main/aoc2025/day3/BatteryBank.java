package aoc2025.day3;

import java.util.*;

public class BatteryBank
{
    private final String joltageRating;

    BatteryBank(String line)
    {
        this.joltageRating = line;
    }

    int getLargestPossibleJoltage()
    {
        String str = joltageRating;
        int max = 0;
        for (int i = 0; i < str.length(); i++)
        {
            int firstNum = Integer.parseInt(str.substring(i, i + 1));
            for (int j = i + 1; j < str.length(); j++)
            {
                int secondNum = Integer.parseInt(str.substring(j, j + 1));
                int possibleMaxNum = firstNum * 10 + secondNum;
                if (possibleMaxNum > max)
                {
                    max = possibleMaxNum;
                }
            }
        }
        return max;
    }
}
