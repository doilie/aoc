package aoc2025.day3;

import java.util.*;

public class BatteryBank
{
    private final String joltageRating;
    private int numberOfBatteries;

    BatteryBank(String line)
    {
        this.joltageRating = line;
    }

    void setNumberOfBatteriesOn(int numberOfBatteries)
    {
        this.numberOfBatteries = numberOfBatteries;
    }

    int getNumberOfBatteries()
    {
        return numberOfBatteries;
    }

    long getLargestPossibleJoltage()
    {
        long currMax = 0;
        int currMaxIdx = -1;
        long wholeMax = 0;
        for (int i = numberOfBatteries; i > 0; i--)
        {
            for (int j = currMaxIdx + 1; j < joltageRating.length(); j++)
            {
                int currNum = Integer.parseInt(joltageRating.substring(j, j + 1));
                int indexFromEnd = joltageRating.length() - i;
                if (currNum > currMax && j <= indexFromEnd)
                {
                    currMax = currNum;
                    currMaxIdx = j;
                }
            }
            wholeMax += (long) (currMax * Math.pow(10L, i - 1));
            currMax = 0;
        }
        return wholeMax;
    }
}
