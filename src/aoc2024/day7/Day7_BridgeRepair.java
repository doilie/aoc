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

    private final String[] lines = getFileContents().split("\\n");

    @Override
    protected void parseFile()
    {
    }

    @Override
    public void doOneStarSolution()
    {
        System.out.println("Total calibration result : " + getCalibrationResult(List.of(Expression.MULTIPLY, Expression.ADD)));
    }

    @Override
    public void doTwoStarSolution()
    {
        System.out.println("Total calibration result with concatenate: " + getCalibrationResult(List.of(Expression.MULTIPLY, Expression.ADD, Expression.CONCATENATE)));
    }

    private long getCalibrationResult(List<String> possibleOperators)
    {
        List<Equation> equations = new ArrayList<>();
        for (String line : lines)
        {
            equations.add(new Equation(line, possibleOperators));
        }
        List<Equation> equationsWithValidResults = equations.stream().filter(Equation::hasValidResult).toList();
        return equationsWithValidResults.stream().map(Equation::getRecordedResult).mapToLong(Long::longValue).sum();
    }
}
