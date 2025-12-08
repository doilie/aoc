package aoc2025.day7;

import lib.Challenge;

public class Day7_Laboratories extends Challenge {
    public static void main(String[] args)
    {
        Day7_Laboratories day6 = new Day7_Laboratories();
        day6.doOneStarSolution();
        day6.doTwoStarSolution();
    }

    public Day7_Laboratories() {
        super("2025/day7-input.txt");
        this.parseFile();
    }

        @Override
    public void parseFile()
    {
    }

    @Override
    public void doOneStarSolution() {
        TachyonManifold tachyonManifold = new TachyonManifold(this.getFileContents());
        System.out.println("Number of times beam was split: " + tachyonManifold.splitBeam());
    }

    @Override
    public void doTwoStarSolution() {

    }

}
