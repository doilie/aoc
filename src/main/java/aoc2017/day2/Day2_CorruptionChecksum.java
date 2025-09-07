package aoc2017.day2;

import lib.Challenge;

import java.util.ArrayList;
import java.util.List;

public class Day2_CorruptionChecksum extends Challenge {
    public static void main(String[] args) {
        Day2_CorruptionChecksum day2 = new Day2_CorruptionChecksum();
        day2.doOneStarSolution();
        day2.doTwoStarSolution();
    }

    public Day2_CorruptionChecksum() {
        super("2017/day2-input.txt");
        this.parseFile();
    }

    private final List<SpreadsheetRow> spreadsheetRowList = new ArrayList<>();

    @Override
    protected void parseFile() {
        String[] lines = getFileContents().split("\\n");
        for (String line : lines) {
            spreadsheetRowList.add(new SpreadsheetRow(line));
        }
    }

    @Override
    public void doOneStarSolution() {
        int checkSums = 0;
        for (SpreadsheetRow spreadsheetRow : spreadsheetRowList) {
            checkSums += spreadsheetRow.getChecksum(new DifferenceChecksumMethod());
        }
        System.out.println("Sum of checksums: " + checkSums);
    }

    @Override
    public void doTwoStarSolution() {
        int checkSums = 0;
        for (SpreadsheetRow spreadsheetRow : spreadsheetRowList) {
            checkSums += spreadsheetRow.getChecksum(new ModuloChecksumMethod());
        }
        System.out.println("Sum of checksums: " + checkSums);
    }
}
