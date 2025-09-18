package aoc2019.day5;

import lib.Challenge;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Day5_SunnyWithAsteroids extends Challenge {
    public static void main(String[] args) {
        Day5_SunnyWithAsteroids day5 = new Day5_SunnyWithAsteroids();
        day5.doOneStarSolution();
        day5.doTwoStarSolution();
    }

    public Day5_SunnyWithAsteroids() {
        super("2019/day5-input.txt");
        this.parseFile();
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
        IntcodeComputer computer = new IntcodeComputer(Arrays.stream(program).boxed().collect(Collectors.toList()));
        computer.addInput(1);
        IntcodeController controller = new IntcodeController(computer);
        controller.execute();
        System.out.println("Diagnostic code (1 star): " + computer.readOutput());
    }

    @Override
    public void doTwoStarSolution() {
        IntcodeComputer computer = new IntcodeComputer(Arrays.stream(program).boxed().collect(Collectors.toList()));
        computer.addInput(5);
        IntcodeController controller = new IntcodeController(computer);
        controller.execute();
        System.out.println("Diagnostic code (2 star): " + computer.readOutput());
    }
}
