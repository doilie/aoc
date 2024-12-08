package aoc2024.day6;

import lib.Challenge;

public class Day6_GuardGallivant extends Challenge
{
    public static void main(String[] args)
    {
        Day6_GuardGallivant day6 = new Day6_GuardGallivant();
        day6.doOneStarSolution();
        day6.doTwoStarSolution();
    }

    public Day6_GuardGallivant() {
        super("2024/day6-input.txt");
        this.parseFile();
    }

    private final LabMap labMap = new LabMap(getFileContents());
    @Override
    protected void parseFile()
    {

    }

    @Override
    public void doOneStarSolution()
    {
        labMap.hasMovedUntilExit();
        System.out.println("Distinct positions visited by guard : " + labMap.getVisitedPositionsCount());
    }

    @Override
    public void doTwoStarSolution()
    {
        System.out.println("Number of positions that cause loop : " + labMap.countPositionsThatCauseLoop());
    }
}
