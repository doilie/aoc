package aoc2023.day3;

import lib.Challenge;

import java.util.List;

public class Day3_GearRatios extends Challenge {
    public static void main(String[] args)  {
        Day3_GearRatios day3 = new Day3_GearRatios();
        day3.doOneStarSolution();
        day3.doTwoStarSolution();
    }

    public Day3_GearRatios() {
        super("2023/day3-input.txt");
        this.parseFile();
    }

    private final Schematic schematic = new Schematic();

    @Override
    public void parseFile() {
        schematic.parseInput(this.getFileContents());
    }

    @Override
    public void doOneStarSolution() {
        List<Integer> partNumbers = schematic.getPartNumbers();
        System.out.println("Sum of part numbers in schematic: " + partNumbers.stream().mapToInt(partNumber -> partNumber).sum());
    }

    @Override
    public void doTwoStarSolution() {
        List<Integer> gearRatios = schematic.getGearRatios();
        System.out.println("Sum of gear ratios in schematic: " + gearRatios.stream().mapToInt(gearRatio -> gearRatio).sum());
    }

}
