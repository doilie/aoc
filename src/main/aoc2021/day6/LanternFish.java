package aoc2021.day6;

public class LanternFish
{
    static final int NEW_FISH_TIMER_DAYS = 8;
    static final int FISH_RESET_TIMER_DAYS = NEW_FISH_TIMER_DAYS - 2;

    private int timerDaysLeft;

    LanternFish(int daysLeft)
    {
        this.timerDaysLeft = daysLeft;
    }

    LanternFish()
    {
        this(NEW_FISH_TIMER_DAYS);
    }

    void decreaseTimer()
    {
        timerDaysLeft--;
        if (hasTimerExpired())
        {
            resetTimer();
        }
    }

    private boolean hasTimerExpired()
    {
        return timerDaysLeft < 0;
    }

    private void resetTimer()
    {
        timerDaysLeft = FISH_RESET_TIMER_DAYS;
    }

    int getTimerDaysLeft()
    {
        return timerDaysLeft;
    }
}
