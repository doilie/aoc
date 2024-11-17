package aoc2020.day4;

import lib.Challenge;

import java.util.ArrayList;
import java.util.List;

public class Day4_PassportProcessing extends Challenge {
    public static void main(String[] args)
    {
        Day4_PassportProcessing day4 = new Day4_PassportProcessing();
        day4.doOneStarSolution();
        day4.doTwoStarSolution();
    }

    public Day4_PassportProcessing()
    {
        super("2020/day4-input.txt");
        this.parseFile();
    }

    private final List<PassportData> passports = new ArrayList<>();

    @Override
    protected void parseFile()
    {
        String[] lines = getFileContents().split("\\n\\n");
        for (String line : lines)
        {
            passports.add(new PassportData(line));
        }
    }

    @Override
    public void doOneStarSolution()
    {
        System.out.println("Number of valid passports: " + passports.stream().filter(PassportData::isValid).count());
    }

    @Override
    public void doTwoStarSolution()
    {
        System.out.println("Number of valid passports when data is checked: " + passports.stream().filter(PassportData::isValid_v2).count());
    }
}
