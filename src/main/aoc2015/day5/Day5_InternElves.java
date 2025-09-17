package aoc2015.day5;

import lib.Challenge;

import java.util.ArrayList;
import java.util.List;

public class Day5_InternElves extends Challenge
{
    public static void main(String[] args)
    {
        Day5_InternElves day5 = new Day5_InternElves();
        day5.doOneStarSolution();
        day5.doTwoStarSolution();
    }

    public Day5_InternElves()
    {
        super("2015/day5-input.txt");
        this.parseFile();
    }

    private final List<NiceString> niceStrings = new ArrayList<>();

    @Override
    protected void parseFile()
    {
        String[] lines = getFileContents().split("\\n");
        for (String line : lines)
        {
            niceStrings.add(new NiceString(line));
        }
    }

    @Override
    public void doOneStarSolution() {
        System.out.println("Number of nice strings: " + niceStrings.stream().filter(NiceString::isNiceString).count());
    }

    @Override
    public void doTwoStarSolution() {
        System.out.println("Number of nice strings from new rules: " + niceStrings.stream().filter(NiceString::isNiceString_v2).count());
    }
}
