package aoc2025.day2;

import lib.Challenge;

import java.util.HashSet;
import java.util.Set;

public class Day2_GiftShop extends Challenge {
    public static void main(String[] args)
    {
        Day2_GiftShop day2 = new Day2_GiftShop();
        day2.doOneStarSolution();
        day2.doTwoStarSolution();
    }

    public Day2_GiftShop() {
        super("2025/day2-input.txt");
        this.parseFile();
    }

    private final Set<NumberRange> numberRanges = new HashSet<>();
    @Override
    public void parseFile()
    {
        String[] values = this.getFileContents().split(",");
        for (String value : values)
        {
            numberRanges.add(new NumberRange(value.trim()));
        }
    }

    @Override
    public void doOneStarSolution() {
        long sum = 0;
        for (NumberRange range : numberRanges)
        {
            sum += range.getTwoRepeatingSequences().stream().mapToLong(Long::longValue).sum();
        }

        System.out.println("Sum of all invalid id's : " + sum);
    }

    @Override
    public void doTwoStarSolution() {
        long sum = 0;
        for (NumberRange range : numberRanges)
        {
            sum += range.getRepeatingSequence().stream().mapToLong(Long::longValue).sum();
        }

        System.out.println("Sum of all invalid id's new rules : " + sum);
    }

}
