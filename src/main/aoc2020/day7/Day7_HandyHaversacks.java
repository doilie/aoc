package aoc2020.day7;

import lib.Challenge;

import java.util.HashSet;
import java.util.Set;

public class Day7_HandyHaversacks extends Challenge {
    public static void main(String[] args)
    {
        Day7_HandyHaversacks day7 = new Day7_HandyHaversacks();
        day7.doOneStarSolution();
        day7.doTwoStarSolution();
    }

    public Day7_HandyHaversacks()
    {
        super("2020/day7-input.txt");
        this.parseFile();
    }

    private final BagRules bagRules = new BagRules();

    @Override
    protected void parseFile()
    {
        String[] lines = this.getFileContents().split("\\n");
        for (String line : lines)
        {
            if (!line.isEmpty())
            {
                bagRules.addBagRule(line);
            }
        }
    }

    @Override
    public void doOneStarSolution()
    {
        Set<String> containingBags = new HashSet<>();
        bagRules.getContainingBags("shiny gold", containingBags);
        System.out.println("Number of bags that contain at least 1 shiny bag: " + containingBags.size());
    }

    @Override
    public void doTwoStarSolution()
    {

    }
}
