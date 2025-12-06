package aoc2025.day5;

import lib.Challenge;

public class Day5_Cafeteria extends Challenge {
    public static void main(String[] args)
    {
        Day5_Cafeteria day5 = new Day5_Cafeteria();
        day5.doOneStarSolution();
        day5.doTwoStarSolution();
    }

    public Day5_Cafeteria() {
        super("2025/day5-input.txt");
        this.parseFile();
    }

        @Override
    public void parseFile()
    {
    }

    @Override
    public void doOneStarSolution() {
        String[] parts = this.getFileContents().split("\\n\\n");
        if (parts.length == 2) {
            String rangePart = parts[0];
            String ingredientsPart = parts[1];

            IngredientsChecker ingredientsChecker = new IngredientsChecker(rangePart);
            System.out.println("Number of fresh ingredients: " + ingredientsChecker.countFreshIngredientsInList(ingredientsPart));
        }
    }

    @Override
    public void doTwoStarSolution() {
        String[] parts = this.getFileContents().split("\\n\\n");
        if (parts.length == 2) {
            String rangePart = parts[0];

            IngredientsChecker ingredientsChecker = new IngredientsChecker(rangePart);
            System.out.println("Number of fresh ingredients in ranges: " + ingredientsChecker.countFreshIngredientsInRanges());
        }
    }

}
