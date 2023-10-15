package aoc2022.day4;

import aoc2022.day4.cleanup.ElfPairAssignment;
import lib.Challenge;

import java.util.ArrayList;
import java.util.List;

public class Day4_CampCleanup extends Challenge {
    public static void main(String[] args)  {
        Day4_CampCleanup day4 = new Day4_CampCleanup();
        day4.doOneStarSolution();
        day4.doTwoStarSolution();
    }

    public Day4_CampCleanup() {
        super("2022/day4-input.txt");
        parseFile();
    }

    private final List<ElfPairAssignment> elfPairAssignments = new ArrayList<>();

    @Override
    protected void parseFile() {
        String[] lines = this.getFileContents().split("\n");
        for (String line : lines) {
            if (line != null) {
                String[] pairs = line.split(",");
                if (pairs.length == 2) {
                    String[] elf1 = pairs[0].split("-");
                    String[] elf2 = pairs[1].split("-");
                    if (elf1.length == 2 && elf2.length == 2) {
                        elfPairAssignments.add(
                                new ElfPairAssignment(Integer.parseInt(elf1[0]), Integer.parseInt(elf1[1]),
                                                        Integer.parseInt(elf2[0]), Integer.parseInt(elf2[1])));
                    }
                }
            }
        }
    }

    @Override
    public void doOneStarSolution() {
        int fullyOverlapCount = 0;
        for (ElfPairAssignment elfPair : elfPairAssignments) {
            if (elfPair.doesAssignmentFullyOverlap()) {
                fullyOverlapCount++;
            }
        }
        System.out.println("Fully overlap count: " + fullyOverlapCount);
    }

    @Override
    public void doTwoStarSolution() {
        int partialOverlapCount = 0;
        for (ElfPairAssignment elfPair : elfPairAssignments) {
            if (elfPair.doesAssignmentOverlap()) {
                partialOverlapCount++;
            }
        }
        System.out.println("Partial overlap count: " + partialOverlapCount);
    }
}
