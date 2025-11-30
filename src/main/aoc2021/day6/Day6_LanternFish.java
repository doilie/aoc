package aoc2021.day6;

import lib.Challenge;

import java.util.stream.IntStream;

public class Day6_LanternFish extends Challenge
{
    public static void main(String[] args)
    {
        Day6_LanternFish day6 = new Day6_LanternFish();
        day6.doOneStarSolution();
        day6.doTwoStarSolution();
    }

    public Day6_LanternFish()
    {
        super("2021/day6-input.txt");
        this.parseFile();
    }

    @Override
    public void parseFile()
    {

    }

    @Override
    public void doOneStarSolution()
    {
        LanternFishSchool lanternFishSchool = new LanternFishSchool(this.getFileContents());
        IntStream.range(0, 80).forEach(i -> lanternFishSchool.moveToNextDay());
        System.out.println("Number of fish after 80 days: " + lanternFishSchool.getNumberOfFishes());
    }

    @Override
    public void doTwoStarSolution()
    {
        LanternFishSchool lanternFishSchool = new LanternFishSchool(this.getFileContents());
        IntStream.range(0, 256).forEach(i -> lanternFishSchool.moveToNextDay());
        System.out.println("Number of fish after 256 days: " + lanternFishSchool.getNumberOfFishes());
    }
}
