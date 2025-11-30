package aoc2021.day6;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LanternFishSchool
{
    private static final int NEW_FISH_TIMER_DAYS = 8;
    private static final int FISH_RESET_TIMER_DAYS = NEW_FISH_TIMER_DAYS - 2;

    private Map<Integer, Long> timerDaysFishCount;

    LanternFishSchool(String timerDaysLeftList)
    {
        Map<Integer, Long> initTimerDaysFishCount = new HashMap<>();
        String[] timerDaysLeftArr = timerDaysLeftList.split(",");
        for (String s : timerDaysLeftArr)
        {
            addFishToTimerDays(initTimerDaysFishCount, Integer.parseInt(s), 1);
        }
        setNewTimerDaysFishCount(initTimerDaysFishCount);
    }

    private void setNewTimerDaysFishCount(Map<Integer, Long> timerDaysFishCount)
    {
        this.timerDaysFishCount = timerDaysFishCount;
    }

    private static void addFishToTimerDays(Map<Integer, Long> timerDaysFishCount, int timerDays, long numberOfFish)
    {
        long fishToAdd = numberOfFish;
        if (timerDaysFishCount.containsKey(timerDays))
        {
            fishToAdd += timerDaysFishCount.get(timerDays);
        }
        timerDaysFishCount.put(timerDays, fishToAdd);
    }

    void moveToNextDay()
    {
        Set<Integer> fishGroups = timerDaysFishCount.keySet();
        Map<Integer, Long> nextTimerDaysFishCount = new HashMap<>();
        for (Integer timerDaysLeft : fishGroups)
        {
            if (timerDaysLeft > 0)
            {
                addFishToTimerDays(nextTimerDaysFishCount, timerDaysLeft - 1, timerDaysFishCount.get(timerDaysLeft));
            }
        }

        if (timerDaysFishCount.containsKey(0))
        {
            addFishToTimerDays(nextTimerDaysFishCount, FISH_RESET_TIMER_DAYS, timerDaysFishCount.get(0));
            addFishToTimerDays(nextTimerDaysFishCount, NEW_FISH_TIMER_DAYS, timerDaysFishCount.get(0));
        }

        setNewTimerDaysFishCount(nextTimerDaysFishCount);
    }

    long getNumberOfFishes()
    {
        return timerDaysFishCount.values().stream().mapToLong(Long::longValue).sum();
    }

    long getNumberOfFishes(int timerDays)
    {
        return timerDaysFishCount.get(timerDays);
    }
}
