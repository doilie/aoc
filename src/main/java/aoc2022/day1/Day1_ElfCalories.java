package aoc2022.day1;

import aoc2022.day1.calories.ElfCalories;
import lib.Challenge;

public class Day1_ElfCalories extends Challenge {
    public static void main(String[] args)  {
        Day1_ElfCalories day1 = new Day1_ElfCalories();
        day1.doOneStarSolution();
        day1.doTwoStarSolution();
    }

    private final ElfCalories elfCalories = new ElfCalories();
    public Day1_ElfCalories() {
        super("2022/day1-input.txt");
        this.parseFile();
    }

    @Override
    public void parseFile() {
        String[] lines = this.getFileContents().split("\n");
        int currElfCalories = 0;
        for (String line : lines) {
            if (line.isEmpty()) {
                elfCalories.addElfCalories(currElfCalories);
                currElfCalories = 0;
            }
            else {
                currElfCalories += Integer.parseInt(line);
            }
        }
    }

    @Override
    public void doOneStarSolution() {
        System.out.println("Max Calories: " + elfCalories.getMaxCalories());
    }

    @Override
    public void doTwoStarSolution() {
        System.out.printf("Top 3 Calories: " + elfCalories.getSumOfTopCalories(3));
    }
}
