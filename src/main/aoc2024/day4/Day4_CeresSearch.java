package aoc2024.day4;

import lib.Challenge;

public class Day4_CeresSearch extends Challenge {
    public static void main(String[] args)
    {
        Day4_CeresSearch day4 = new Day4_CeresSearch();
        day4.doOneStarSolution();
        day4.doTwoStarSolution();
    }

    public Day4_CeresSearch() {
        super("2024/day4-input.txt");
        this.parseFile();
    }

    private final XmasGrid grid = new XmasGrid(getFileContents());
    @Override
    protected void parseFile()
    {

    }

    @Override
    public void doOneStarSolution() {
        System.out.println("Number of matches : " + grid.countXmasMatches());
    }

    @Override
    public void doTwoStarSolution() {
        System.out.println("Number of x-mas matches : " + grid.countCrossmasMatches());
    }

}
