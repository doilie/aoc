package aoc2018.day4;

import lib.Challenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Day4_ReposeRecord extends Challenge {
    public static void main(String[] args) {
        Day4_ReposeRecord day4 = new Day4_ReposeRecord();
        day4.doOneStarSolution();
        day4.doTwoStarSolution();
    }

    public Day4_ReposeRecord() {
        super("2018/day4-input.txt");
        this.parseFile();
    }

    private final GuardRecordAnalyzer guardRecordAnalyzer = new GuardRecordAnalyzer();

    @Override
    protected void parseFile()
    {
        String[] lines = this.getFileContents().split("\\n");
        List<String> sortedInput = new ArrayList<>(Arrays.stream(lines).toList());
        Collections.sort(sortedInput);
        for (String line : sortedInput)
        {
            guardRecordAnalyzer.addRecord(new GuardRecord(line));
        }
    }

    @Override
    public void doOneStarSolution()
    {
        int guardWithMostSleep = guardRecordAnalyzer.getGuardWithMostSleep();
        int minuteSleptMost = guardRecordAnalyzer.getMinuteMostlySleptByGuard(guardWithMostSleep);
        System.out.println("Product of id of guard with most sleep and the minute he slept most: " + guardWithMostSleep * minuteSleptMost);
    }

    @Override
    public void doTwoStarSolution()
    {
        String guardKey = guardRecordAnalyzer.getGuardKeyWithMostSleep();
        System.out.println("Product of id of guard and the minute he slept most: " + GuardRecordAnalyzer.getGuardIdFromKey(guardKey) * GuardRecordAnalyzer.getMinuteFromKey(guardKey));
    }
}
