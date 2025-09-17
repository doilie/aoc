package aoc2021.day1;

import lib.Challenge;

public class Day1_SonarSweep extends Challenge {
    public static void main(String[] args)  {
        Day1_SonarSweep day1 = new Day1_SonarSweep();
        day1.doOneStarSolution();
        day1.doTwoStarSolution();
    }


    public Day1_SonarSweep() {
        super("2021/day1-input.txt");
        this.parseFile();
    }

    private final DepthList depthList = new DepthList();

    @Override
    public void parseFile() {
        String[] lines = this.getFileContents().split("\n");
        for (String line : lines) {
            depthList.addDepth(Integer.parseInt(line));
        }
    }

    @Override
    public void doOneStarSolution() {
        System.out.println("Number of measurements are larger than the previous measurement: " + depthList.getIncreaseCount());
    }

    @Override
    public void doTwoStarSolution() {
        System.out.println("Number of measurements are larger than the previous measurement - window size = 3: " + depthList.getIncreaseCountWithWindowSize(3));
    }
}
