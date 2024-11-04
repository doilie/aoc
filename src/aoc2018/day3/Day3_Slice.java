package aoc2018.day3;

import lib.Challenge;

import java.util.ArrayList;
import java.util.List;

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

    private final List<ElfFabricOwnership> efoList = new ArrayList<>();
    @Override
    protected void parseFile() {
        String[] lines = this.getFileContents().split("\n");
        for (String line : lines) {
            efoList.add(new ElfFabricOwnership(line));
        }
    }

    @Override
    public void doOneStarSolution() {
        Fabric f = new Fabric();
        for (ElfFabricOwnership elfFabricOwnership : efoList) {
            f.addElfOwnership(elfFabricOwnership);
        }
        System.out.println("Square inch of fabric with 2 or more claims: " + f.countFabricPositionsWithOverlap());
    }

    @Override
    public void doTwoStarSolution() {
//        System.out.println("Common letters for strings with only 1 diff: " + commonLetters);
    }
}
