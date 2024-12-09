package aoc2024.day7;

import lib.Challenge;

import java.util.ArrayList;
import java.util.List;

public class Day7_BridgeRepair extends Challenge
{
    public static void main(String[] args)
    {
        Day7_BridgeRepair day7 = new Day7_BridgeRepair();
        day7.doOneStarSolution();
        day7.doTwoStarSolution();
    }

    public Day7_BridgeRepair() {
        super("2024/day7-input.txt");
        this.parseFile();
    }

    private final List<Equation> equations = new ArrayList<>();

    @Override
    protected void parseFile()
    {
        String[] lines = getFileContents().split("\\n");
        for (String line : lines)
        {
            equations.add(new Equation(line));
        }
    }

    @Override
    public void doOneStarSolution()
    {
        List<Equation> equationsWithValidResults = equations.stream().filter(Equation::hasValidResult).toList();
        System.out.println("Total calibration result : " + equationsWithValidResults.stream().map(Equation::getRecordedResult).mapToLong(Long::longValue).sum());
    }

    @Override
    public void doTwoStarSolution()
    {
//        System.out.println("Number of positions that cause loop : " + labMap.countPositionsThatCauseLoop());
    }
}
