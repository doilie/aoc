package aoc2020.day5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberFinderTest {

    @Test
    void find_FB_128()
    {
        NumberFinder numberFinder = new NumberFinder('F', 'B', 128);
        assertEquals(44, numberFinder.find("FBFBBFF"));
    }

    @Test
    void find_LR_8()
    {
        NumberFinder numberFinder = new NumberFinder('L', 'R', 8);
        assertEquals(5, numberFinder.find("RLR"));
    }
}