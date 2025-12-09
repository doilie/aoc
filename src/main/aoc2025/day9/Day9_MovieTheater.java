package aoc2025.day9;

import lib.Challenge;

public class Day9_MovieTheater extends Challenge {
    public static void main(String[] args)
    {
        Day9_MovieTheater day6 = new Day9_MovieTheater();
        day6.doOneStarSolution();
        day6.doTwoStarSolution();
    }

    public Day9_MovieTheater() {
        super("2025/day9-input.txt");
        this.parseFile();
    }

        @Override
    public void parseFile()
    {
    }

    @Override
    public void doOneStarSolution() {
        TileFloor tileFloor = new TileFloor(this.getFileContents());
        System.out.println("Largest red rectangle area: " + tileFloor.getLargestRedRectangleArea());
    }

    @Override
    public void doTwoStarSolution() {

    }

}
