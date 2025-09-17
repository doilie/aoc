package aoc2018.day5;

import lib.Challenge;

public class Day5_AlchemicalReduction extends Challenge {
    public static void main(String[] args)
    {
        Day5_AlchemicalReduction day5 = new Day5_AlchemicalReduction();
        day5.doOneStarSolution();
        day5.doTwoStarSolution();
    }

    public Day5_AlchemicalReduction() {
        super("2018/day5-input.txt");
        this.parseFile();
    }

    @Override
    protected void parseFile()
    {
    }

    @Override
    public void doOneStarSolution()
    {
        System.out.println("Number of units intact after fully reacting polymer: " + (ReactionRound.reactContinuously(getFileContents().trim())).length());
    }

    @Override
    public void doTwoStarSolution()
    {
        System.out.println("Length of shortest polymer by removing units of one type and fully reacting result: " + (ReactionRound.getShortestPolymerFromRemovingUnit(getFileContents().trim())).length());
    }
}
