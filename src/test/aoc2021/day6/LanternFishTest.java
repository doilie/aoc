package aoc2021.day6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LanternFishTest
{
    @Test
    void testDecreaseTimer()
    {
        LanternFish fish = new LanternFish(3);
        fish.decreaseTimer();
        assertEquals(2, fish.getTimerDaysLeft());
        fish.decreaseTimer();
        assertEquals(1, fish.getTimerDaysLeft());
        fish.decreaseTimer();
        assertEquals(0, fish.getTimerDaysLeft());
        fish.decreaseTimer();
        assertEquals(6, fish.getTimerDaysLeft());
    }

    @Test
    void testNewLanternFish()
    {
        LanternFish fish = new LanternFish();
        assertEquals(8, fish.getTimerDaysLeft());
    }
}
