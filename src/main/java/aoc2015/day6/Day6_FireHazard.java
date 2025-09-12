package aoc2015.day6;

import java.util.ArrayList;
import java.util.List;

import lib.Challenge;

public class Day6_FireHazard extends Challenge 
{
        public static void main(String[] args)
    {
        Day6_FireHazard day6 = new Day6_FireHazard();
        day6.doOneStarSolution();
        day6.doTwoStarSolution();
    }

    public Day6_FireHazard()
    {
        super("2015/day6-input.txt");
        this.parseFile();
    }

    private final List<LightInstruction> lightInstructions = new ArrayList<>();

    @Override
    protected void parseFile()
    {
        String[] lines = getFileContents().split("\\n");
        for (String line : lines)
        {
            lightInstructions.add(new LightInstruction(line.trim()));
        }
        System.out.println("Parsed " + lightInstructions.size() + " light instructions");
    }

    @Override
    public void doOneStarSolution() {
        // System.out.println("Number of nice strings: " + niceStrings.stream().filter(NiceString::isNiceString).count());
    }

    @Override
    public void doTwoStarSolution() {
        // System.out.println("Number of nice strings from new rules: " + niceStrings.stream().filter(NiceString::isNiceString_v2).count());
    }

}
