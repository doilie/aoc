package aoc2025.day2;

import java.util.HashSet;
import java.util.Set;

public class NumberRange
{
    private final long start;
    private final long end;

    public NumberRange(String line)
    {
        String[] parts = line.split("-");
        if  (parts.length != 2)
        {
            throw new IllegalArgumentException("Invalid line format");
        }
        this.start = Long.parseLong(parts[0]);
        this.end = Long.parseLong(parts[1]);
    }

    Set<Long> getTwoRepeatingSequences()
    {
        Set<Long> result = new HashSet<>();

        for (long num = start; num <= end; num++)
        {
            String longStringValue = Long.toString(num);
            if (longStringValue.length() % 2 == 0)
            {
                int stringMidIdx = longStringValue.length() / 2;
                if (longStringValue.substring(0, stringMidIdx).equals(longStringValue.substring(stringMidIdx)))
                {
                    result.add(Long.parseLong(longStringValue));
                }
            }
        }

        return result;
    }

    Set<Long> getRepeatingSequence()
    {
        Set<Long> result = new HashSet<>();

        for (long num = start; num <= end; num++)
        {
            String longStringValue = Long.toString(num);
            for (int i = 0;  i < longStringValue.length() / 2; i++)
            {
                String substringToCheck = longStringValue.substring(0, i + 1);
                int substringLength = substringToCheck.length();
                if (longStringValue.length() % substringLength == 0)
                {
                    boolean allMatch = true;
                    for (int j = substringLength; j <= longStringValue.length() - substringLength; j += substringLength)
                    {
                        String periodicString = longStringValue.substring(j, j + substringLength);
                        if (!periodicString.equals(substringToCheck)) {
                            allMatch = false;
                            break;
                        }
                    }
                    if (allMatch)
                    {
                        result.add(num);
                    }
                }
            }
        }

        return result;
    }
}
