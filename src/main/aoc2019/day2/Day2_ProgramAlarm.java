package aoc2019.day2;

import lib.Challenge;

import java.util.ArrayList;
import java.util.List;

public class Day2_ProgramAlarm extends Challenge {
    public static void main(String[] args) {
        Day2_ProgramAlarm day2 = new Day2_ProgramAlarm();
        day2.doOneStarSolution();
        day2.doTwoStarSolution();
    }

    public Day2_ProgramAlarm() {
        super("2019/day2-input.txt");
        this.parseFile();
    }

    private final List<Integer> codes = new ArrayList<>();

    @Override
    protected void parseFile() {
        String[] lines = this.getFileContents().trim().split(",");
        for (String line : lines) {
            codes.add(Integer.parseInt(line));
        }
    }

    @Override
    public void doOneStarSolution() {
        List<Integer> modifiedCodes = new ArrayList<>(codes);
        Intcode.runProgram(modifiedCodes, 12, 2);
        System.out.println("Value at position 0 after noun = 12, verb = 2: " + modifiedCodes.get(0));
    }

    @Override
    public void doTwoStarSolution() {
        final int minValue = 0;
        final int maxValue = 99;
        final int targetValue = 19690720;
        int noun;
        int verb = 0;
        boolean nounVerbFound = false;
        for (noun = minValue; noun <= maxValue; noun++) {
            for (verb = minValue; verb <= maxValue; verb++) {
                List<Integer> modifiedCodes = new ArrayList<>(codes);
                Intcode.runProgram(modifiedCodes, noun, verb);
                if (modifiedCodes.get(0) == targetValue) {
                    nounVerbFound = true;
                    break;
                }
            }
            if (nounVerbFound) {
                break;
            }
        }
        System.out.println("100 * noun + verb with value at position 0 = 19690720: " + ((100 * noun) + verb));
    }
}
