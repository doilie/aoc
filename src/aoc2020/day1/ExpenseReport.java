package aoc2020.day1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ExpenseReport {
    private final List<Integer> entries = new ArrayList<>();

    public void addEntry(int entry) {
        this.entries.add(entry);
    }

    public List<Integer> findTwoEntriesWithSum(int sum) {
        List<Integer> entriesWithSum = new ArrayList<>();

        for (int refNum : entries) {
            Integer otherNum = findDifference(refNum, sum);
            if (otherNum != null) {
                entriesWithSum.add(refNum);
                entriesWithSum.add(otherNum);
                break;
            }
        }
        return entriesWithSum;
    }

    public List<Integer> findThreeEntriesWithSum(int sum) {
        List<Integer> entriesWithSum = new ArrayList<>();

        for (int refNum : entries) {
            int remaining = sum - refNum;
            List<Integer> subEntries = findTwoEntriesWithSum(remaining);
            if (subEntries.size() == 2) {
                entriesWithSum.add(refNum);
                entriesWithSum.addAll(subEntries);
                break;
            }
        }

        return entriesWithSum;
    }

    private Integer findDifference(int refNum, int sum) {
        List<Integer> otherNumList = entries.stream().filter(num -> num == (sum - refNum)).collect(Collectors.toList());
        if (!otherNumList.isEmpty()) {
            return otherNumList.get(0);
        }
        return null;
    }
}
