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
    }

    @Test
    void testNewLanternFish()
    {
        LanternFish fish = new LanternFish();
        assertEquals(LanternFish.NEW_FISH_TIMER_DAYS, fish.getTimerDaysLeft());
    }

    @Test
    void testHasTimerExpired()
    {
        LanternFish fish = new LanternFish(3);
        fish.decreaseTimer();
        assertFalse(fish.hasTimerExpired());
        fish.decreaseTimer();
        assertFalse(fish.hasTimerExpired());
        fish.decreaseTimer();
        assertFalse(fish.hasTimerExpired());
        fish.decreaseTimer();
        assertTrue(fish.hasTimerExpired());
    }

    @Test
    void testResetTimer()
    {
        LanternFish fish = new LanternFish(3);
        fish.resetTimer();
        assertEquals(LanternFish.FISH_RESET_TIMER_DAYS, fish.getTimerDaysLeft());
    }

    @Test
    void testToString()
    {
        LanternFish fish = new LanternFish(3);
        assertEquals("3", fish.toString());
    }

}
