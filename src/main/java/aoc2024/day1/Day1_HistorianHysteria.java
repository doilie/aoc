package aoc2024.day1;

import lib.Challenge;

public class Day1_HistorianHysteria extends Challenge {
    public static void main(String[] args)
    {
        Day1_HistorianHysteria day1 = new Day1_HistorianHysteria();
        day1.doOneStarSolution();
        day1.doTwoStarSolution();
    }

    public Day1_HistorianHysteria() {
        super("2024/day1-input.txt");
        this.parseFile();
    }

    private final NumberList numberList = new NumberList(getFileContents());

    @Override
    public void parseFile()
    {
    }

    @Override
    public void doOneStarSolution() {
        System.out.println("Total distance by difference between lists : " + numberList.getTotalDistanceByDifference());
    }

    @Override
    public void doTwoStarSolution() {
        System.out.println("Total distance by similarity between lists : " + numberList.getTotalDistanceBySimilarity());
    }

}
