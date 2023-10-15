package aoc2020.day1;

import lib.Challenge;

import java.util.List;

public class Day1_ReportRepair extends Challenge {
    public static void main(String[] args) {
        Day1_ReportRepair day1 = new Day1_ReportRepair();
        day1.doOneStarSolution();
        day1.doTwoStarSolution();
    }

    public Day1_ReportRepair() {
        super("2020/day1-input.txt");
        this.parseFile();
    }

    private final ExpenseReport expenseReport = new ExpenseReport();

    @Override
    protected void parseFile() {
        String[] lines = this.getFileContents().split("\n");
        for (String line : lines) {
            expenseReport.addEntry(Integer.parseInt(line));
        }
    }

    @Override
    public void doOneStarSolution() {
        List<Integer> sumsTo2020 = expenseReport.findTwoEntriesWithSum(2020);
        if (sumsTo2020.size() == 2) {
            System.out.println("Product of 2 entries with sum = 2020: " + (sumsTo2020.get(0) * sumsTo2020.get(1)));
        }
    }

    @Override
    public void doTwoStarSolution() {
        List<Integer> sumsTo2020 = expenseReport.findThreeEntriesWithSum(2020);
        if (sumsTo2020.size() == 3) {
            System.out.println("Product of 3 entries with sum = 2020: " + (sumsTo2020.get(0) * sumsTo2020.get(1) * sumsTo2020.get(2)));
        }
    }
}
