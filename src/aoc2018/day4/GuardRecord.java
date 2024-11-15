package aoc2018.day4;

public class GuardRecord
{
    public int getMinute() {
        return minute;
    }

    public GuardAction getAction() {
        return action;
    }

    public int getGuardId() {
        return guardId;
    }

    public enum GuardAction
    {
        BeginShift,
        FallAsleep,
        WakeUp
    }

    private final String record;
    private GuardAction action;
    private int minute;
    private int guardId;

    public GuardRecord(String record)
    {
        this.record = record;
        extractAction();
        extractMinute();
        extractId();
    }

    private void extractMinute()
    {
        int colonIndex = record.indexOf(':');
        int closingBracketIndex = record.indexOf(']');
        if (colonIndex != -1 && closingBracketIndex != -1)
        {
            minute = Integer.parseInt(record.substring(colonIndex + 1, closingBracketIndex));
        }
    }

    private void extractAction()
    {
        if (record.contains("begins shift"))
        {
            action = GuardAction.BeginShift;
        }
        else if (record.contains("falls asleep"))
        {
            action = GuardAction.FallAsleep;
        }
        else if (record.contains("wakes up"))
        {
            action = GuardAction.WakeUp;
        }
    }

    private void extractId()
    {
        int hashIndex = record.indexOf('#');
        int bIndex = record.indexOf('b');
        if (hashIndex != -1 && bIndex != -1)
        {
            guardId = Integer.parseInt(record.substring(hashIndex + 1, bIndex - 1));
        }
    }
}
