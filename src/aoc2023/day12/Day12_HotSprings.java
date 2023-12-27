package aoc2023.day12;

import lib.Challenge;

import java.util.ArrayList;
import java.util.List;

public class Day12_HotSprings extends Challenge {
    public static void main(String[] args)  {
        Day12_HotSprings day12 = new Day12_HotSprings();
        day12.doOneStarSolution();
        day12.doTwoStarSolution();
    }

    public Day12_HotSprings() {
        super("2023/day12-input.txt");
        this.parseFile();
    }

    private final List<OnsenRow> onsenRows = new ArrayList<>();
    private int foldSize = 1;

    @Override
    public void parseFile() {
        String[] lines = this.getFileContents().split("\\n");
        onsenRows.clear();
        for (String line : lines) {
            onsenRows.add(new OnsenRow(line, foldSize));
        }
    }

    @Override
    public void doOneStarSolution() {
        int sum = onsenRows.stream().map(OnsenRow::getPossibleArrangements).mapToInt(List::size).sum();
        System.out.println("Sum of possible arrangements: " + sum);
    }

    @Override
    public void doTwoStarSolution() {
    }
}
