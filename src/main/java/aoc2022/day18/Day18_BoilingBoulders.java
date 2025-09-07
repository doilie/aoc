package aoc2022.day18;

import lib.Challenge;

public class Day18_BoilingBoulders extends Challenge {
    public static void main(String[] args) {
        Day18_BoilingBoulders day18 = new Day18_BoilingBoulders();
        day18.doOneStarSolution();
        day18.doTwoStarSolution();
    }

    public Day18_BoilingBoulders() {
        super("2022/day18-input.txt");
        parseFile();
    }

    private final ObsidianStructure obsidianStructure = new ObsidianStructure();

    @Override
    protected void parseFile() {
        String[] lines = getFileContents().split("\\n");
        for (String line : lines) {
            obsidianStructure.addDroplet(line);
        }
    }

    @Override
    public void doOneStarSolution() {
        System.out.println("Surface area: " + obsidianStructure.computeSurfaceAreaAll());
    }

    @Override
    public void doTwoStarSolution() {
        System.out.println("Surface area without gaps: " + obsidianStructure.computeSurfaceAreaOutside());
    }
}
