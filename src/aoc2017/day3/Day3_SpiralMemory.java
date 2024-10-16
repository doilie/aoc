package aoc2017.day3;

import lib.Challenge;

public class Day3_SpiralMemory extends Challenge {
    public static void main(String[] args) {
        Day3_SpiralMemory day2 = new Day3_SpiralMemory();
        day2.doOneStarSolution();
        day2.doTwoStarSolution();
    }

    public Day3_SpiralMemory() {
        super("2017/day3-input.txt");
        this.parseFile();
    }

    private long inputValue;
    @Override
    protected void parseFile() {
        inputValue = Long.parseLong(getFileContents());
    }

    @Override
    public void doOneStarSolution() {
        System.out.println("Steps needed: " + (new SpiralSteps()).getSteps(inputValue));
    }

    @Override
    public void doTwoStarSolution() {

    }
}
