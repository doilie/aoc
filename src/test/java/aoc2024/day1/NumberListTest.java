package aoc2024.day1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberListTest
{

    @Test
    void getTotalDistanceByDifference()
    {
        NumberList numberList = new NumberList("""
                3   4
                4   3
                2   5
                1   3
                3   9
                3   3""");
        assertEquals(11, numberList.getTotalDistanceByDifference());
    }

    @Test
    void getTotalDistanceBySimilarity()
    {
        NumberList numberList = new NumberList("""
                3   4
                4   3
                2   5
                1   3
                3   9
                3   3""");
        assertEquals(31, numberList.getTotalDistanceBySimilarity());
    }
}