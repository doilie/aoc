package aoc2015.day1;

import lib.Challenge;

public class Day1_NotQuiteLisp extends Challenge {
    public static void main(String[] args) {
        Day1_NotQuiteLisp day1 = new Day1_NotQuiteLisp();
        day1.doOneStarSolution();
        day1.doTwoStarSolution();
    }

    public Day1_NotQuiteLisp() {
        super("2015/day1-input.txt");
        this.parseFile();
    }

    private final ParenthesisList parenthesisList = new ParenthesisList();

    @Override
    protected void parseFile() {
        for (int i = 0; i < getFileContents().length(); i++) {
            parenthesisList.addParenthesis(Character.toString(getFileContents().charAt(i)));
        }
    }

    @Override
    public void doOneStarSolution() {
        System.out.println("Santa's current floor: " + parenthesisList.getSantaCurrentFloor());
    }

    @Override
    public void doTwoStarSolution() {
        System.out.println("Position of character that enters basement: " + parenthesisList.getPositionThatGoesToBasement());
    }
}
