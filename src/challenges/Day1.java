package challenges;

import lib.InputFileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day1 {
    public static void main(String[] args)  {
        Day1 day1 = new Day1();
        System.out.println("Max Calories: " + day1.getMaxCalories());
        System.out.println("Top 3 Calories: " + day1.getSumOfTopCalories(3));
    }

    private String fileContents = "";

    public Day1() {
        String inputFile = "resources\\day1-input.txt";
        try {
            this.fileContents = InputFileReader.readFileAsString(inputFile);
        } catch (IOException e) {
            System.err.println("Exception: " + e);
        }
    }

    public int getMaxCalories() {
        return getSumOfTopCalories(1);
    }

    public int getSumOfTopCalories(int level) {
        String[] lines = fileContents.split("\n");
        List<Integer> elfCalories = new ArrayList<>();
        int currElfCalories = 0;
        for (String line : lines) {
            if (line.isEmpty()) {
                elfCalories.add(currElfCalories);
                currElfCalories = 0;
            }
            else {
                currElfCalories += Integer.parseInt(line);
            }
        }
        Collections.sort(elfCalories);

        int sumOfTopCalories = 0;
        for (int i = elfCalories.size() - 1; i >= (elfCalories.size() - level); i--) {
            sumOfTopCalories += elfCalories.get(i);
        }

        return sumOfTopCalories;
    }
}
