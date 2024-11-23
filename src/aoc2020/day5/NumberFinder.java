package aoc2020.day5;

public class NumberFinder
{
    private final char lowerHalfIndicator;
    private final char upperHalfIndicator;
    private final int range;

    public NumberFinder(char lowerHalfIndicator, char upperHalfIndicator, int range)
    {
        this.lowerHalfIndicator = lowerHalfIndicator;
        this.upperHalfIndicator = upperHalfIndicator;
        this.range = range;
    }

    public int find(String input)
    {
        int lowestNumber = 0;
        int highestNumber = range - 1;
        int currentRange = range / 2;
        int lastNumber = 0;
        for (int i = 0; i < input.length(); i++)
        {
            if (input.charAt(i) == lowerHalfIndicator)
            {
                highestNumber = highestNumber - currentRange;
                lowestNumber = highestNumber - currentRange + 1;
                lastNumber = lowestNumber;
            }
            else if (input.charAt(i) == upperHalfIndicator)
            {
                lowestNumber = lowestNumber + currentRange;
                highestNumber = lowestNumber + currentRange - 1;
                lastNumber = highestNumber;
            }

            currentRange /= 2;
        }

        return lastNumber;
    }
}
