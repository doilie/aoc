package aoc2018.day4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GuardRecordTest {

    @Test
    void beginShiftRecord()
    {
        GuardRecord guardRecord = new GuardRecord("[1518-11-01 00:00] Guard #10 begins shift");
        assertEquals(0, guardRecord.getMinute());
        assertEquals(GuardRecord.GuardAction.BeginShift, guardRecord.getAction());
        assertEquals(10, guardRecord.getGuardId());
    }

    @Test
    void fallAsleepRecord()
    {
        GuardRecord guardRecord = new GuardRecord("[1518-11-01 00:05] falls asleep");
        assertEquals(5, guardRecord.getMinute());
        assertEquals(GuardRecord.GuardAction.FallAsleep, guardRecord.getAction());
        assertEquals(0, guardRecord.getGuardId());
    }

    @Test
    void wakeUpRecord()
    {
        GuardRecord guardRecord = new GuardRecord("[1518-11-01 00:25] wakes up");
        assertEquals(25, guardRecord.getMinute());
        assertEquals(GuardRecord.GuardAction.WakeUp, guardRecord.getAction());
        assertEquals(0, guardRecord.getGuardId());
    }
}