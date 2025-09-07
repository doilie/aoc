package aoc2022.day14;

import lib.Challenge;

import java.util.stream.IntStream;

public class Day14_RegolithReservoir extends Challenge {
    public static void main(String[] args) {
        Day14_RegolithReservoir day14 = new Day14_RegolithReservoir();
        day14.doOneStarSolution();
        day14.doTwoStarSolution();
    }

    public Day14_RegolithReservoir() {
        super("2022/day14-input.txt");
        parseFile();
    }

    private Cave cave = new Cave();

    @Override
    protected void parseFile() {
        String[] lines = this.getFileContents().split("\n");
        cave = new Cave();
        for (String line : lines) {
            cave.addRockStructure(line);
        }
    }

    @Override
    public void doOneStarSolution() {
        parseFile();
        final int sandCount = 610;
        IntStream.range(0, sandCount).forEach(i -> cave.pourSand());
        System.out.println("Sand until abyss:");
        cave.printCaveElements();
    }

    @Override
    public void doTwoStarSolution() {
        parseFile();
        cave.addCaveFloor();
        Coordinate c = null;
        int sandCount = 0;
        while (c == null || (c.getX() != 500 || c.getY() != 0)) {
            c = cave.pourSand();
            sandCount++;
        }
        System.out.println("Sand until floor:");
        cave.printCaveElements();
        System.out.println("Fallen sand: " + sandCount);
    }
}
