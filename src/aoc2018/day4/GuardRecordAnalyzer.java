package aoc2018.day4;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class GuardRecordAnalyzer
{
    private static String getGuardSleepMinuteKey(int guard, int minute)
    {
        return guard + "_" + minute;
    }

    private static int getGuardIdFromKey(String key)
    {
        String[] keyParts = key.split("_");
        return Integer.parseInt(keyParts[0]);
    }

    private static int getMinuteFromKey(String key)
    {
        String[] keyParts = key.split("_");
        return Integer.parseInt(keyParts[1]);
    }

    private int currentGuard;
    private int currentStartMinute;
    private final Map<String, Integer> guardSleepMinutesPerDay = new HashMap<>();

    public void addRecord(GuardRecord record)
    {
        switch(record.getAction())
        {
            case BeginShift -> currentGuard = record.getGuardId();
            case FallAsleep -> currentStartMinute = record.getMinute();
            case WakeUp -> addSleepMinutes(record.getMinute());
        }
    }

    private void addSleepMinutes(int endMinute)
    {
        for (int i = currentStartMinute; i < endMinute; i++)
        {
            guardSleepMinutesPerDay.merge(getGuardSleepMinuteKey(currentGuard, i), 1, Integer::sum);
        }
    }

    private Map<Integer, Integer> getGuardSleepMinutesPerDay()
    {
        Set<String> keySet = guardSleepMinutesPerDay.keySet();
        Map<Integer, Integer> guardSleepMinutesSummary = new HashMap<>();

        for (String key : keySet)
        {
            int guardId = getGuardIdFromKey(key);
            int minutesCount = guardSleepMinutesPerDay.get(key);
            guardSleepMinutesSummary.merge(guardId, minutesCount, Integer::sum);
        }

        return guardSleepMinutesSummary;
    }

    public int getGuardWithMostSleep()
    {
        Map<Integer, Integer> guardSleepMinutesPerDay = getGuardSleepMinutesPerDay();
        int maxSleep = 0;
        int guardWithMostSleep = 0;
        for (Map.Entry<Integer, Integer> entry : guardSleepMinutesPerDay.entrySet())
        {
            if (entry.getValue() > maxSleep)
            {
                maxSleep = entry.getValue();
                guardWithMostSleep = entry.getKey();
            }
        }
        return guardWithMostSleep;
    }

    public int getMinuteMostlySleptByGuard(int guardId)
    {
        Set<String> guardKeys = guardSleepMinutesPerDay.keySet().stream().filter(k -> k.startsWith(guardId + "_")).collect(Collectors.toSet());
        int maxCount = 0;
        int minuteMostlySlept = 0;
        for (String guardKey : guardKeys)
        {
            if (guardSleepMinutesPerDay.get(guardKey) > maxCount)
            {
                maxCount = guardSleepMinutesPerDay.get(guardKey);
                minuteMostlySlept = getMinuteFromKey(guardKey);
            }
        }
        return minuteMostlySlept;
    }
}
