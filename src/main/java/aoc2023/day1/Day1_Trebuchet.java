package aoc2023.day1;

import lib.Challenge;

import java.util.ArrayList;
import java.util.List;

public class Day1_Trebuchet extends Challenge {
    public static void main(String[] args)  {
        Day1_Trebuchet day1 = new Day1_Trebuchet();
        day1.doOneStarSolution();
        day1.doTwoStarSolution();
    }

    public Day1_Trebuchet() {
        super("2023/day1-input.txt");
        this.parseFile();
    }

    private final List<NumberParser> numbers = new ArrayList<>();

    @Override
    public void parseFile() {
        String[] lines = this.getFileContents().split("\n");
        for (String line : lines) {
            numbers.add(new NumberParser(line));
        }
    }

    @Override
    public void doOneStarSolution() {
        System.out.println("Sum of calibration values : " + numbers.stream().mapToInt(NumberParser::getNumberFromSymbols).sum());
    }

    @Override
    public void doTwoStarSolution() {
        System.out.println("Sum of calibration real values : " + numbers.stream().mapToInt(NumberParser::getNumberFromSymbolsAndWords).sum());
    }

}
