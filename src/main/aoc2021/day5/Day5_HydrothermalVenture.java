package aoc2021.day5;

import lib.Challenge;

public class Day5_HydrothermalVenture extends Challenge {
    public static void main(String[] args)
    {
        Day5_HydrothermalVenture day5 = new Day5_HydrothermalVenture();
        day5.doOneStarSolution();
        day5.doTwoStarSolution();
    }


    public Day5_HydrothermalVenture()
    {
        super("2021/day5-input.txt");
        this.parseFile();
    }

    @Override
    public void parseFile()
    {

    }

    @Override
    public void doOneStarSolution()
    {
        HydrothermalVentField hv = new HydrothermalVentField();
        String[] lines = getFileContents().split("\\n");
        for (String line : lines) {
            hv.drawLine(line.trim(), false);
        }
        System.out.println("Number of points where lines overlap: " + hv.getNumberOfPointsWhereLinesOverlap());
    }

    @Override
    public void doTwoStarSolution()
    {
        HydrothermalVentField hv = new HydrothermalVentField();
        String[] lines = getFileContents().split("\\n");
        for (String line : lines) {
            hv.drawLine(line.trim(), true);
        }
        System.out.println("Number of points where lines overlap (diagonal): " + hv.getNumberOfPointsWhereLinesOverlap());
    }
}
