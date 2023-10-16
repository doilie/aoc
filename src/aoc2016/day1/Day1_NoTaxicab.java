package aoc2016.day1;

import lib.Challenge;

public class Day1_NoTaxicab extends Challenge {
    public static void main(String[] args) {
        Day1_NoTaxicab day1 = new Day1_NoTaxicab();
        day1.doOneStarSolution();
        day1.doTwoStarSolution();
    }

    public Day1_NoTaxicab() {
        super("2016/day1-input.txt");
        this.parseFile();
    }

    private final InstructionList instructionList = new InstructionList();

    @Override
    protected void parseFile() {
        String[] instructions = getFileContents().split(",");
        for (String instruction : instructions) {
            instructionList.addInstruction(instruction.trim());
        }
    }

    @Override
    public void doOneStarSolution() {
        System.out.println("Number of blocks from Easter Bunny HQ (end): " + instructionList.getNumberOfBlocksAwayToEnd());
    }

    @Override
    public void doTwoStarSolution() {
        System.out.println("Number of blocks from Easter Bunny HQ (visited twice): " + instructionList.getNumberOfBlocksAwayToVisitedTwice());
    }
}
