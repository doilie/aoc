package aoc2024.day3;

import lib.Challenge;

import java.util.ArrayList;
import java.util.List;

public class Day3_MullItOver extends Challenge {
    public static void main(String[] args)
    {
        Day3_MullItOver day3 = new Day3_MullItOver();
        day3.doOneStarSolution();
        day3.doTwoStarSolution();
    }

    public Day3_MullItOver() {
        super("2024/day3-input.txt");
        this.parseFile();
    }

    @Override
    protected void parseFile()
    {

    }

    @Override
    public void doOneStarSolution() {
        InstructionFinder instructionFinder = new InstructionFinder(getFileContents(), "mul\\([1-9][0-9]*,[1-9][0-9]*\\)");
        List<MultiplyInstruction> multiplyInstructions = new ArrayList<>(instructionFinder.getInstructions().stream().map(MultiplyInstruction::new).toList());
        System.out.println("Sum of multiplication results : " + multiplyInstructions.stream().mapToLong(MultiplyInstruction::getResult).sum());
    }

    @Override
    public void doTwoStarSolution() {
        InstructionFinder instructionFinder = new InstructionFinder(getFileContents(), "mul\\([1-9][0-9]*,[1-9][0-9]*\\)", "do()", "don't()");
        List<MultiplyInstruction> multiplyInstructions = new ArrayList<>(instructionFinder.getInstructions().stream().map(MultiplyInstruction::new).toList());
        System.out.println("Sum of enabled multiplication results : " + multiplyInstructions.stream().mapToLong(MultiplyInstruction::getResult).sum());
    }

}
