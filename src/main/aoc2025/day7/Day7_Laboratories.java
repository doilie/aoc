package aoc2025.day7;

import lib.Challenge;

public class Day7_Laboratories extends Challenge {
    public static void main(String[] args)
    {
        Day7_Laboratories day7 = new Day7_Laboratories();
        day7.doOneStarSolution();
        day7.doTwoStarSolution();
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
        TachyonManifold tachyonManifold = new TachyonManifold(this.getFileContents());
        System.out.println("Number of timelines: " + tachyonManifold.countPaths());
    }

}
