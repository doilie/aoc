package aoc2019.day1;

import lib.Challenge;

import java.util.ArrayList;
import java.util.List;

public class Day1_RocketEquation extends Challenge {
    public static void main(String[] args) {
        Day1_RocketEquation day1 = new Day1_RocketEquation();
        day1.doOneStarSolution();
        day1.doTwoStarSolution();
    }

    public Day1_RocketEquation() {
        super("2019/day1-input.txt");
        this.parseFile();
    }

    private final List<Mass> massList = new ArrayList<>();

    @Override
    protected void parseFile() {
        String[] lines = this.getFileContents().split("\n");
        for (String line : lines) {
            massList.add(new Mass(Integer.parseInt(line)));
        }
    }

    @Override
    public void doOneStarSolution() {
        System.out.println("Sum of mass fuel requirements: " + massList.stream().mapToInt(Mass::getFuelForMassOnly).sum());
    }

    @Override
    public void doTwoStarSolution() {
        System.out.println("Sum of total fuel requirements: " + massList.stream().mapToInt(Mass::getTotalFuel).sum());
    }
}
