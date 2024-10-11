package aoc2023.day12;

import lib.Challenge;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    @Override
    public void parseFile() {
        String[] lines = this.getFileContents().split("\\n");
        onsenRows.clear();
        for (String line : lines) {
            onsenRows.add(new OnsenRow(line));
        }
    }

    @Override
    public void doOneStarSolution() {
        int sum = onsenRows.stream().map(OnsenRow::getPossibleArrangements).mapToInt(List::size).sum();
        System.out.println("Sum of possible arrangements: " + sum);
    }

    @Override
    public void doTwoStarSolution() {
//        int sum = onsenRows.stream()
//                .map(
//                    or -> or.getPossibleArrangementsFolded(
//                            new OnsenRow(or.getValueWithUnknownStart(), or.getDamagedGroupsSize()),
//                            new OnsenRow(or.getValueWithUnknownEnd(), or.getDamagedGroupsSize())
//                    ))
//                .mapToInt(Set::size).sum();
//        long sum = onsenRows.stream()
//                .mapToLong(OnsenRow::getPossibleArrangementsFoldedCount)
//                .sum();
        long sum = onsenRows.stream()
                .map(OnsenRow::getPossibleArrangementsFolded)
                .mapToInt(Set::size)
                .sum();
        System.out.println("Sum of possible arrangements folded: " + sum);
    }
}
