package aoc2018.day4;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GuardRecordAnalyzerTest {
    private GuardRecordAnalyzer createTestObject()
    {
        String input = """
                [1518-11-01 00:00] Guard #10 begins shift
                [1518-11-01 00:05] falls asleep
                [1518-11-01 00:25] wakes up
                [1518-11-01 00:30] falls asleep
                [1518-11-01 00:55] wakes up
                [1518-11-01 23:58] Guard #99 begins shift
                [1518-11-02 00:40] falls asleep
                [1518-11-02 00:50] wakes up
                [1518-11-03 00:05] Guard #10 begins shift
                [1518-11-03 00:24] falls asleep
                [1518-11-03 00:29] wakes up
                [1518-11-04 00:02] Guard #99 begins shift
                [1518-11-04 00:36] falls asleep
                [1518-11-04 00:46] wakes up
                [1518-11-05 00:03] Guard #99 begins shift
                [1518-11-05 00:45] falls asleep
                [1518-11-05 00:55] wakes up""";

        String[] lines = input.split("\\n");
        List<String> inputList = new ArrayList<>(Arrays.stream(lines).toList());
        GuardRecordAnalyzer recordAnalyzer = new GuardRecordAnalyzer();
        for (String line : inputList)
        {
            recordAnalyzer.addRecord(new GuardRecord(line));
        }
        return recordAnalyzer;
    }

    @Test
    void getGuardWithMostSleep()
    {
        GuardRecordAnalyzer recordAnalyzer = createTestObject();
        assertEquals(10, recordAnalyzer.getGuardWithMostSleep());
    }

    @Test
    void getMinuteMostlySleptByGuard()
    {
        GuardRecordAnalyzer recordAnalyzer = createTestObject();
        assertEquals(24, recordAnalyzer.getMinuteMostlySleptByGuard(10));
    }

    @Test
    void getGuardKeyWithMostSleep()
    {
        GuardRecordAnalyzer recordAnalyzer = createTestObject();
        assertEquals("99_45", recordAnalyzer.getGuardKeyWithMostSleep());
    }

    @Test
    void getProductOfGuardAndMinuteInKey()
    {
        GuardRecordAnalyzer recordAnalyzer = createTestObject();
        String guardKey = recordAnalyzer.getGuardKeyWithMostSleep();
        assertEquals(4455, GuardRecordAnalyzer.getGuardIdFromKey(guardKey) * GuardRecordAnalyzer.getMinuteFromKey(guardKey));
    }
}