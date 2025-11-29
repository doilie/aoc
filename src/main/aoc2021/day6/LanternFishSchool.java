package aoc2021.day6;

import java.util.*;
import java.util.stream.Stream;

public class LanternFishSchool
{
    private final List<LanternFish> lanternFishList = new ArrayList<>();

    LanternFishSchool(String timerDaysLeftList)
    {
        String[] timerDaysLeftArr = timerDaysLeftList.split(",");
        lanternFishList.addAll(Arrays.stream(timerDaysLeftArr).map(Integer::parseInt).map(LanternFish::new).toList());
    }

    void moveToNextDay()
    {
        lanternFishList.forEach(LanternFish::decreaseTimer);
        List<LanternFish> lanternFishListCopy = new ArrayList<>(lanternFishList);
        Stream<LanternFish> hasExpiredTimers = lanternFishListCopy.stream().filter(LanternFish::hasTimerExpired);
        hasExpiredTimers.forEach(f -> {
            f.resetTimer();
            lanternFishList.add(new LanternFish());
        });
    }

    long getNumberOfFishes()
    {
        return lanternFishList.size();
    }

    @Override
    public String toString()
    {
        return String.join(",", lanternFishList.stream().map(LanternFish::toString).toList());
    }
}
