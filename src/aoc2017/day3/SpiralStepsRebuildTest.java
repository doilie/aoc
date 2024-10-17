package aoc2017.day3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpiralStepsRebuildTest {
    private long runTest(long lastValue)
    {
        SpiralStepsRebuild spiralStepsRebuild = new SpiralStepsRebuild(lastValue);
        spiralStepsRebuild.buildSpiral();
        return spiralStepsRebuild.getLastValue();
    }
    @Test
    public void test1()
    {
        assertEquals(2, runTest(1));
    }

    @Test
    public void test3()
    {
        assertEquals(4, runTest(3));
    }


    @Test
    public void test150()
    {
        assertEquals(304, runTest(150));
    }


    @Test
    public void test800()
    {
        assertEquals(806, runTest(800));
    }


}