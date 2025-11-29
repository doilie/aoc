package aoc2021.day6;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LanternFishSchoolTest
{
    static final String FISH_SCHOOL_TEST_DATA = "3,4,3,1,2";
    /*
    Initial state: 3,4,3,1,2
    After  1 day:  2,3,2,0,1
    After  2 days: 1,2,1,6,0,8
    After  3 days: 0,1,0,5,6,7,8
    After  4 days: 6,0,6,4,5,6,7,8,8
    After  5 days: 5,6,5,3,4,5,6,7,7,8
    After  6 days: 4,5,4,2,3,4,5,6,6,7
    After  7 days: 3,4,3,1,2,3,4,5,5,6
    After  8 days: 2,3,2,0,1,2,3,4,4,5
    After  9 days: 1,2,1,6,0,1,2,3,3,4,8
    After 10 days: 0,1,0,5,6,0,1,2,2,3,7,8
    After 11 days: 6,0,6,4,5,6,0,1,1,2,6,7,8,8,8
    After 12 days: 5,6,5,3,4,5,6,0,0,1,5,6,7,7,7,8,8
    After 13 days: 4,5,4,2,3,4,5,6,6,0,4,5,6,6,6,7,7,8,8
    After 14 days: 3,4,3,1,2,3,4,5,5,6,3,4,5,5,5,6,6,7,7,8
    After 15 days: 2,3,2,0,1,2,3,4,4,5,2,3,4,4,4,5,5,6,6,7
    After 16 days: 1,2,1,6,0,1,2,3,3,4,1,2,3,3,3,4,4,5,5,6,8
    After 17 days: 0,1,0,5,6,0,1,2,2,3,0,1,2,2,2,3,3,4,4,5,7,8
    After 18 days: 6,0,6,4,5,6,0,1,1,2,6,0,1,1,1,2,2,3,3,4,6,7,8,8,8,8
    */

    @Test
    void testCreateFishSchool()
    {
        LanternFishSchool fishSchool = new LanternFishSchool(FISH_SCHOOL_TEST_DATA);
        assertEquals(FISH_SCHOOL_TEST_DATA, fishSchool.toString());
    }

    @Test
    void testToDay18()
    {
        LanternFishSchool fishSchool = new LanternFishSchool(FISH_SCHOOL_TEST_DATA);
        // day 1
        fishSchool.moveToNextDay();
        assertEquals("2,3,2,0,1", fishSchool.toString());
        // day 2
        fishSchool.moveToNextDay();
        assertEquals("1,2,1,6,0,8", fishSchool.toString());
        // day 3
        fishSchool.moveToNextDay();
        assertEquals("0,1,0,5,6,7,8", fishSchool.toString());
        // day 4
        fishSchool.moveToNextDay();
        assertEquals("6,0,6,4,5,6,7,8,8", fishSchool.toString());
        // day 5
        fishSchool.moveToNextDay();
        assertEquals("5,6,5,3,4,5,6,7,7,8", fishSchool.toString());
        // day 6
        fishSchool.moveToNextDay();
        assertEquals("4,5,4,2,3,4,5,6,6,7", fishSchool.toString());
        // day 7
        fishSchool.moveToNextDay();
        assertEquals("3,4,3,1,2,3,4,5,5,6", fishSchool.toString());
        // day 8
        fishSchool.moveToNextDay();
        assertEquals("2,3,2,0,1,2,3,4,4,5", fishSchool.toString());
        // day 9
        fishSchool.moveToNextDay();
        assertEquals("1,2,1,6,0,1,2,3,3,4,8", fishSchool.toString());
        // day 10
        fishSchool.moveToNextDay();
        assertEquals("0,1,0,5,6,0,1,2,2,3,7,8", fishSchool.toString());
        // day 11
        fishSchool.moveToNextDay();
        assertEquals("6,0,6,4,5,6,0,1,1,2,6,7,8,8,8", fishSchool.toString());
        // day 12
        fishSchool.moveToNextDay();
        assertEquals("5,6,5,3,4,5,6,0,0,1,5,6,7,7,7,8,8", fishSchool.toString());
        // day 13
        fishSchool.moveToNextDay();
        assertEquals("4,5,4,2,3,4,5,6,6,0,4,5,6,6,6,7,7,8,8", fishSchool.toString());
        // day 14
        fishSchool.moveToNextDay();
        assertEquals("3,4,3,1,2,3,4,5,5,6,3,4,5,5,5,6,6,7,7,8", fishSchool.toString());
        // day 15
        fishSchool.moveToNextDay();
        assertEquals("2,3,2,0,1,2,3,4,4,5,2,3,4,4,4,5,5,6,6,7", fishSchool.toString());
        // day 16
        fishSchool.moveToNextDay();
        assertEquals("1,2,1,6,0,1,2,3,3,4,1,2,3,3,3,4,4,5,5,6,8", fishSchool.toString());
        // day 17
        fishSchool.moveToNextDay();
        assertEquals("0,1,0,5,6,0,1,2,2,3,0,1,2,2,2,3,3,4,4,5,7,8", fishSchool.toString());
        // day 18
        fishSchool.moveToNextDay();
        assertEquals("6,0,6,4,5,6,0,1,1,2,6,0,1,1,1,2,2,3,3,4,6,7,8,8,8,8", fishSchool.toString());
    }

    @Test
    void testNumberOfFishOnDay80()
    {
        LanternFishSchool fishSchool = new LanternFishSchool(FISH_SCHOOL_TEST_DATA);
        IntStream.range(0, 80).forEach(i -> fishSchool.moveToNextDay());
        assertEquals(5934, fishSchool.getNumberOfFishes());
    }
}
