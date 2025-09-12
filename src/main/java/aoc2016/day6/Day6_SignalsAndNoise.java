package aoc2016.day6;

import lib.Challenge;

public class Day6_SignalsAndNoise extends Challenge {
    public static void main(String[] args) {
        Day6_SignalsAndNoise day6 = new Day6_SignalsAndNoise();
        day6.doOneStarSolution();
        day6.doTwoStarSolution();
    }

    public Day6_SignalsAndNoise() {
        super("2016/day6-input.txt");
        this.parseFile();
    }

    @Override
    protected void parseFile() {

    }

    @Override
    public void doOneStarSolution() {
        Signal signal = new Signal(getFileContents());
        System.out.println("Message: " + signal.decodeMessage(false));
    }

    @Override
    public void doTwoStarSolution() {
        Signal signal = new Signal(getFileContents());
        System.out.println("Message (v2): " + signal.decodeMessage(true));
    }

}
