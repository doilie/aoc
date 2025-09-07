package aoc2023.day11;

import lib.Challenge;

public class Day11_CosmicExpansion extends Challenge {
    public static void main(String[] args)  {
        Day11_CosmicExpansion day11 = new Day11_CosmicExpansion();
        day11.doOneStarSolution();
        day11.doTwoStarSolution();
    }

    public Day11_CosmicExpansion() {
        super("2023/day11-input.txt");
        this.parseFile();
    }

    private final GalaxyImage galaxyImage = new GalaxyImage();

    @Override
    public void parseFile() {
        String[] lines = this.getFileContents().split("\n");
        for (int y = 0; y < lines.length; y++) {
            String line = lines[y];
            for (int x = 0; x < line.length(); x++) {
                galaxyImage.addCosmicPoint(x, y, Character.toString(line.charAt(x)));
            }
        }
        galaxyImage.expand();
    }

    @Override
    public void doOneStarSolution() {
        System.out.println("Sum of length of shortest paths: " + galaxyImage.getSumOfGalaxyDistances());
    }

    @Override
    public void doTwoStarSolution() {
        CosmicPoint.setExpansionFactor(1000000);
        galaxyImage.expand();
        System.out.println("Sum of length of shortest paths with 1M expansion: " + galaxyImage.getSumOfGalaxyDistances());
    }
}
