package aoc2017.day3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpiralStepsTest {
    @Test
    public void test1()
    {
        assertEquals(0, (new SpiralSteps()).getSteps(1));
    }

    @Test
    public void test9()
    {
        assertEquals(2, (new SpiralSteps()).getSteps(9));
    }

    @Test
    public void test12()
    {
        assertEquals(3, (new SpiralSteps()).getSteps(12));
    }

    @Test
    public void test23()
    {
        assertEquals(2, (new SpiralSteps()).getSteps(23));
    }

    @Test
    public void test1024()
    {
        assertEquals(31, (new SpiralSteps()).getSteps(1024));
    }
}