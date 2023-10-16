package aoc2018.day1;

import lib.Challenge;

public class Day1_ChronalCalibration extends Challenge {
    public static void main(String[] args) {
        Day1_ChronalCalibration day1 = new Day1_ChronalCalibration();
        day1.doOneStarSolution();
        day1.doTwoStarSolution();
    }

    public Day1_ChronalCalibration() {
        super("2018/day1-input.txt");
        this.parseFile();
    }

    private final FrequencyList frequencyList = new FrequencyList();

    @Override
    protected void parseFile() {
        String[] lines = this.getFileContents().split("\n");
        for (String line : lines) {
            frequencyList.addFrequency(Integer.parseInt(line));
        }
    }

    @Override
    public void doOneStarSolution() {
        System.out.println("Delta frequency: " + frequencyList.getDeltaFrequency(0));
    }

    @Override
    public void doTwoStarSolution() {
        System.out.println("First frequency reached twice: " + frequencyList.getFrequencyReachedTwice(0));
    }
}
