package aoc2019.day5;

import lib.Challenge;

public class Day5_SunnyWithAsteroids extends Challenge {
    public static void main(String[] args) {
        Day5_SunnyWithAsteroids day5 = new Day5_SunnyWithAsteroids();
        day5.doOneStarSolution();
        day5.doTwoStarSolution();
    }

    public Day5_SunnyWithAsteroids() {
        super("2019/day5-input.txt");
    }

    private int[] program;

    @Override
    protected void parseFile() {     
        String[] strProgram = this.getFileContents().trim().split(",");
        program = new int[strProgram.length];
        for (int i = 0; i < strProgram.length; i++)
        {
            program[i] = Integer.parseInt(strProgram[i]);
        }
    }

    @Override
    public void doOneStarSolution() {
        this.parseFile();
        IntcodeComputer computer = new IntcodeComputer(program);
        computer.addInput(1);
        computer.runProgram();
        System.out.println("Diagnostic code (1 star): " + computer.getLastOutput());
    }

    @Override
    public void doTwoStarSolution() {
        this.parseFile();
        // IntcodeComputer computer = new IntcodeComputer(program);
        // computer.addInput(5);
        // computer.runProgram();
        // System.out.println("Diagnostic code (2 star): " + computer.getLastOutput());
    }
}
