package aoc2018.day3;

import lib.Challenge;

public class Day3_Slice extends Challenge {
    public static void main(String[] args) {
        Day3_Slice day3 = new Day3_Slice();
        day3.doOneStarSolution();
        day3.doTwoStarSolution();
    }

    public Day3_Slice() {
        super("2018/day3-input.txt");
        this.parseFile();
    }

    private final Fabric f = new Fabric();

    @Override
    protected void parseFile() {
        String[] lines = this.getFileContents().split("\n");
        for (String line : lines) {
            f.addElfOwnership(new ElfFabricOwnership(line));
        }
    }

    @Override
    public void doOneStarSolution() {
        System.out.println("Square inch of fabric with 2 or more claims: " + f.countFabricPositionsWithOverlap());
    }

    @Override
    public void doTwoStarSolution() {
        System.out.println("Sole owner elf: " + f.getSoleOwnerOverlap());
    }
}
