package aoc2020.day3;

import lib.Challenge;

public class Day3_TobogganTrajectory extends Challenge {
    public static void main(String[] args) {
        Day3_TobogganTrajectory day2 = new Day3_TobogganTrajectory();
        day2.doOneStarSolution();
        day2.doTwoStarSolution();
    }

    public Day3_TobogganTrajectory() {
        super("2020/day3-input.txt");
        this.parseFile();
    }

    @Override
    protected void parseFile() {
    }

    @Override
    public void doOneStarSolution() {
        InfiniteTreeMap infiniteTreeMap = new InfiniteTreeMap(this.getFileContents());
        System.out.println("Number of trees in right 3, down 1 slope: " + infiniteTreeMap.countTreesInSlope(3, 1));
    }

    @Override
    public void doTwoStarSolution() {
        int[] x = new int[]{1, 3, 5, 7, 1};
        int[] y = new int[]{1, 1, 1, 1, 2};
        long result = 1;
        InfiniteTreeMap infiniteTreeMap = new InfiniteTreeMap(this.getFileContents());
        for (int i = 0; i < x.length; i++)
        {
            result *= infiniteTreeMap.countTreesInSlope(x[i], y[i]);
        }
        System.out.println("Product of number of trees in listed slopes: " + result);
    }
}
