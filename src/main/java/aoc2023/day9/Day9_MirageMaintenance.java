package aoc2023.day9;

import lib.Challenge;

import java.util.ArrayList;
import java.util.List;

public class Day9_MirageMaintenance extends Challenge {
    public static void main(String[] args)  {
        Day9_MirageMaintenance day9 = new Day9_MirageMaintenance();
        day9.doOneStarSolution();
        day9.doTwoStarSolution();
    }

    public Day9_MirageMaintenance() {
        super("2023/day9-input.txt");
        this.parseFile();
    }

    private final List<NumberSequence> numberSequences = new ArrayList<>();

    @Override
    public void parseFile() {
        String[] lines = this.getFileContents().split("\\n");
        for (String line : lines) {
            numberSequences.add(new NumberSequence(line));
        }
    }

    @Override
    public void doOneStarSolution() {
        int sumExtrapolatedValues = 0;
        for (NumberSequence numberSequence : numberSequences) {
            sumExtrapolatedValues += numberSequence.getNextNumber();
        }
        System.out.println("Sum of extrapolated values: " + sumExtrapolatedValues);
    }

    @Override
    public void doTwoStarSolution() {
        int sumExtrapolatedValuesBackward = 0;
        for (NumberSequence numberSequence : numberSequences) {
            sumExtrapolatedValuesBackward += numberSequence.getFirstNumber();
        }
        System.out.println("Sum of extrapolated values backward: " + sumExtrapolatedValuesBackward);
    }
}
