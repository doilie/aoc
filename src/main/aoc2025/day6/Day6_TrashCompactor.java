package aoc2025.day6;

import lib.Challenge;

public class Day6_TrashCompactor extends Challenge {
    public static void main(String[] args)
    {
        Day6_TrashCompactor day6 = new Day6_TrashCompactor();
        day6.doOneStarSolution();
        day6.doTwoStarSolution();
    }

    public Day6_TrashCompactor() {
        super("2025/day6-input.txt");
        this.parseFile();
    }

        @Override
    public void parseFile()
    {
    }

    @Override
    public void doOneStarSolution() {
        VerticalOperationSolver verticalOperationSolver = new VerticalOperationSolver(this.getFileContents());
        System.out.println("Grand total: " + verticalOperationSolver.solve());
    }

    @Override
    public void doTwoStarSolution() {

    }

}
