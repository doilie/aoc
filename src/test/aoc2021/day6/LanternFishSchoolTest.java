package aoc2021.day6;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LanternFishSchoolTest
{
    static final String FISH_SCHOOL_TEST_DATA = "3,4,3,1,2";
    /*
    */

    @Test
    void testCreateFishSchool()
    {
        // Initial state: 3,4,3,1,2
        LanternFishSchool optFishSchool = new LanternFishSchool(FISH_SCHOOL_TEST_DATA);
        assertEquals(5, optFishSchool.getNumberOfFishes());
        assertEquals(2, optFishSchool.getNumberOfFishes(3));
        assertEquals(1, optFishSchool.getNumberOfFishes(4));
        assertEquals(1, optFishSchool.getNumberOfFishes(1));
        assertEquals(1, optFishSchool.getNumberOfFishes(2));
    }

    @Test
    void testDay1()
    {
        // After  1 day:  2,3,2,0,1
        LanternFishSchool optFishSchool = new LanternFishSchool(FISH_SCHOOL_TEST_DATA);
        optFishSchool.moveToNextDay();
        assertEquals(5, optFishSchool.getNumberOfFishes());
        assertEquals(2, optFishSchool.getNumberOfFishes(2));
        assertEquals(1, optFishSchool.getNumberOfFishes(3));
        assertEquals(1, optFishSchool.getNumberOfFishes(0));
        assertEquals(1, optFishSchool.getNumberOfFishes(1));
    }

    @Test
    void testDay2()
    {
        // After  2 days: 1,2,1,6,0,8
        LanternFishSchool optFishSchool = new LanternFishSchool(FISH_SCHOOL_TEST_DATA);
        IntStream.range(0, 2).forEach(i -> optFishSchool.moveToNextDay());
        assertEquals(6, optFishSchool.getNumberOfFishes());
        assertEquals(2, optFishSchool.getNumberOfFishes(1));
        assertEquals(1, optFishSchool.getNumberOfFishes(2));
        assertEquals(1, optFishSchool.getNumberOfFishes(6));
        assertEquals(1, optFishSchool.getNumberOfFishes(0));
        assertEquals(1, optFishSchool.getNumberOfFishes(8));
    }

    @Test
    void testDay3()
    {
        // After  3 days: 0,1,0,5,6,7,8
        LanternFishSchool optFishSchool = new LanternFishSchool(FISH_SCHOOL_TEST_DATA);
        IntStream.range(0, 3).forEach(i -> optFishSchool.moveToNextDay());
        assertEquals(7, optFishSchool.getNumberOfFishes());
        assertEquals(2, optFishSchool.getNumberOfFishes(0));
        assertEquals(1, optFishSchool.getNumberOfFishes(1));
        assertEquals(1, optFishSchool.getNumberOfFishes(5));
        assertEquals(1, optFishSchool.getNumberOfFishes(6));
        assertEquals(1, optFishSchool.getNumberOfFishes(7));
        assertEquals(1, optFishSchool.getNumberOfFishes(8));
    }

    @Test
    void testDay4()
    {
        // After  4 days: 6,0,6,4,5,6,7,8,8
        LanternFishSchool optFishSchool = new LanternFishSchool(FISH_SCHOOL_TEST_DATA);
        IntStream.range(0, 4).forEach(i -> optFishSchool.moveToNextDay());
        assertEquals(9, optFishSchool.getNumberOfFishes());
        assertEquals(3, optFishSchool.getNumberOfFishes(6));
        assertEquals(1, optFishSchool.getNumberOfFishes(0));
        assertEquals(1, optFishSchool.getNumberOfFishes(4));
        assertEquals(1, optFishSchool.getNumberOfFishes(5));
        assertEquals(1, optFishSchool.getNumberOfFishes(7));
        assertEquals(2, optFishSchool.getNumberOfFishes(8));
    }

    @Test
    void testDay5()
    {
        // After  5 days: 5,6,5,3,4,5,6,7,7,8
        LanternFishSchool optFishSchool = new LanternFishSchool(FISH_SCHOOL_TEST_DATA);
        IntStream.range(0, 5).forEach(i -> optFishSchool.moveToNextDay());
        assertEquals(10, optFishSchool.getNumberOfFishes());
        assertEquals(3, optFishSchool.getNumberOfFishes(5));
        assertEquals(2, optFishSchool.getNumberOfFishes(6));
        assertEquals(1, optFishSchool.getNumberOfFishes(3));
        assertEquals(1, optFishSchool.getNumberOfFishes(4));
        assertEquals(2, optFishSchool.getNumberOfFishes(7));
        assertEquals(1, optFishSchool.getNumberOfFishes(8));
    }

    @Test
    void testDay6()
    {
        // After  6 days: 4,5,4,2,3,4,5,6,6,7
        LanternFishSchool optFishSchool = new LanternFishSchool(FISH_SCHOOL_TEST_DATA);
        IntStream.range(0, 6).forEach(i -> optFishSchool.moveToNextDay());
        assertEquals(10, optFishSchool.getNumberOfFishes());
        assertEquals(3, optFishSchool.getNumberOfFishes(4));
        assertEquals(2, optFishSchool.getNumberOfFishes(5));
        assertEquals(1, optFishSchool.getNumberOfFishes(2));
        assertEquals(1, optFishSchool.getNumberOfFishes(3));
        assertEquals(2, optFishSchool.getNumberOfFishes(6));
        assertEquals(1, optFishSchool.getNumberOfFishes(7));
    }

    @Test
    void testDay7()
    {
        // After  7 days: 3,4,3,1,2,3,4,5,5,6
        LanternFishSchool optFishSchool = new LanternFishSchool(FISH_SCHOOL_TEST_DATA);
        IntStream.range(0, 7).forEach(i -> optFishSchool.moveToNextDay());
        assertEquals(10, optFishSchool.getNumberOfFishes());
        assertEquals(3, optFishSchool.getNumberOfFishes(3));
        assertEquals(2, optFishSchool.getNumberOfFishes(4));
        assertEquals(1, optFishSchool.getNumberOfFishes(1));
        assertEquals(1, optFishSchool.getNumberOfFishes(2));
        assertEquals(2, optFishSchool.getNumberOfFishes(5));
        assertEquals(1, optFishSchool.getNumberOfFishes(6));
    }

    @Test
    void testDay8()
    {
        // After  8 days: 2,3,2,0,1,2,3,4,4,5
        LanternFishSchool optFishSchool = new LanternFishSchool(FISH_SCHOOL_TEST_DATA);
        IntStream.range(0, 8).forEach(i -> optFishSchool.moveToNextDay());
        assertEquals(10, optFishSchool.getNumberOfFishes());
        assertEquals(3, optFishSchool.getNumberOfFishes(2));
        assertEquals(2, optFishSchool.getNumberOfFishes(3));
        assertEquals(1, optFishSchool.getNumberOfFishes(0));
        assertEquals(1, optFishSchool.getNumberOfFishes(1));
        assertEquals(2, optFishSchool.getNumberOfFishes(4));
        assertEquals(1, optFishSchool.getNumberOfFishes(5));
    }

    @Test
    void testDay9()
    {
        // After  9 days: 1,2,1,6,0,1,2,3,3,4,8
        LanternFishSchool optFishSchool = new LanternFishSchool(FISH_SCHOOL_TEST_DATA);
        IntStream.range(0, 9).forEach(i -> optFishSchool.moveToNextDay());
        assertEquals(11, optFishSchool.getNumberOfFishes());
        assertEquals(3, optFishSchool.getNumberOfFishes(1));
        assertEquals(2, optFishSchool.getNumberOfFishes(2));
        assertEquals(1, optFishSchool.getNumberOfFishes(6));
        assertEquals(1, optFishSchool.getNumberOfFishes(0));
        assertEquals(2, optFishSchool.getNumberOfFishes(3));
        assertEquals(1, optFishSchool.getNumberOfFishes(4));
        assertEquals(1, optFishSchool.getNumberOfFishes(8));
    }

    @Test
    void testDay10()
    {
        // After 10 days: 0,1,0,5,6,0,1,2,2,3,7,8
        LanternFishSchool optFishSchool = new LanternFishSchool(FISH_SCHOOL_TEST_DATA);
        IntStream.range(0, 10).forEach(i -> optFishSchool.moveToNextDay());
        assertEquals(12, optFishSchool.getNumberOfFishes());
        assertEquals(3, optFishSchool.getNumberOfFishes(0));
        assertEquals(2, optFishSchool.getNumberOfFishes(1));
        assertEquals(1, optFishSchool.getNumberOfFishes(5));
        assertEquals(1, optFishSchool.getNumberOfFishes(6));
        assertEquals(2, optFishSchool.getNumberOfFishes(2));
        assertEquals(1, optFishSchool.getNumberOfFishes(3));
        assertEquals(1, optFishSchool.getNumberOfFishes(7));
        assertEquals(1, optFishSchool.getNumberOfFishes(8));
    }

    @Test
    void testDay11()
    {
        // After 11 days: 6,0,6,4,5,6,0,1,1,2,6,7,8,8,8
        LanternFishSchool optFishSchool = new LanternFishSchool(FISH_SCHOOL_TEST_DATA);
        IntStream.range(0, 11).forEach(i -> optFishSchool.moveToNextDay());
        assertEquals(15, optFishSchool.getNumberOfFishes());
        assertEquals(4, optFishSchool.getNumberOfFishes(6));
        assertEquals(2, optFishSchool.getNumberOfFishes(0));
        assertEquals(1, optFishSchool.getNumberOfFishes(4));
        assertEquals(1, optFishSchool.getNumberOfFishes(5));
        assertEquals(2, optFishSchool.getNumberOfFishes(1));
        assertEquals(1, optFishSchool.getNumberOfFishes(2));
        assertEquals(1, optFishSchool.getNumberOfFishes(7));
        assertEquals(3, optFishSchool.getNumberOfFishes(8));
    }

    @Test
    void testDay12()
    {
        // After 12 days: 5,6,5,3,4,5,6,0,0,1,5,6,7,7,7,8,8
        LanternFishSchool optFishSchool = new LanternFishSchool(FISH_SCHOOL_TEST_DATA);
        IntStream.range(0, 12).forEach(i -> optFishSchool.moveToNextDay());
        assertEquals(17, optFishSchool.getNumberOfFishes());
        assertEquals(4, optFishSchool.getNumberOfFishes(5));
        assertEquals(3, optFishSchool.getNumberOfFishes(6));
        assertEquals(1, optFishSchool.getNumberOfFishes(3));
        assertEquals(1, optFishSchool.getNumberOfFishes(4));
        assertEquals(2, optFishSchool.getNumberOfFishes(0));
        assertEquals(1, optFishSchool.getNumberOfFishes(1));
        assertEquals(3, optFishSchool.getNumberOfFishes(7));
        assertEquals(2, optFishSchool.getNumberOfFishes(8));
    }

    @Test
    void testDay13()
    {
        // After 13 days: 4,5,4,2,3,4,5,6,6,0,4,5,6,6,6,7,7,8,8
        LanternFishSchool optFishSchool = new LanternFishSchool(FISH_SCHOOL_TEST_DATA);
        IntStream.range(0, 13).forEach(i -> optFishSchool.moveToNextDay());
        assertEquals(19, optFishSchool.getNumberOfFishes());
        assertEquals(4, optFishSchool.getNumberOfFishes(4));
        assertEquals(3, optFishSchool.getNumberOfFishes(5));
        assertEquals(1, optFishSchool.getNumberOfFishes(2));
        assertEquals(1, optFishSchool.getNumberOfFishes(3));
        assertEquals(5, optFishSchool.getNumberOfFishes(6));
        assertEquals(1, optFishSchool.getNumberOfFishes(0));
        assertEquals(2, optFishSchool.getNumberOfFishes(7));
        assertEquals(2, optFishSchool.getNumberOfFishes(8));
    }

    @Test
    void testDay14()
    {
        // After 14 days: 3,4,3,1,2,3,4,5,5,6,3,4,5,5,5,6,6,7,7,8
        LanternFishSchool optFishSchool = new LanternFishSchool(FISH_SCHOOL_TEST_DATA);
        IntStream.range(0, 14).forEach(i -> optFishSchool.moveToNextDay());
        assertEquals(20, optFishSchool.getNumberOfFishes());
        assertEquals(4, optFishSchool.getNumberOfFishes(3));
        assertEquals(3, optFishSchool.getNumberOfFishes(4));
        assertEquals(1, optFishSchool.getNumberOfFishes(1));
        assertEquals(1, optFishSchool.getNumberOfFishes(2));
        assertEquals(5, optFishSchool.getNumberOfFishes(5));
        assertEquals(3, optFishSchool.getNumberOfFishes(6));
        assertEquals(2, optFishSchool.getNumberOfFishes(7));
        assertEquals(1, optFishSchool.getNumberOfFishes(8));
    }

    @Test
    void testDay15()
    {
        // After 15 days: 2,3,2,0,1,2,3,4,4,5,2,3,4,4,4,5,5,6,6,7
        LanternFishSchool optFishSchool = new LanternFishSchool(FISH_SCHOOL_TEST_DATA);
        IntStream.range(0, 15).forEach(i -> optFishSchool.moveToNextDay());
        assertEquals(20, optFishSchool.getNumberOfFishes());
        assertEquals(4, optFishSchool.getNumberOfFishes(2));
        assertEquals(3, optFishSchool.getNumberOfFishes(3));
        assertEquals(1, optFishSchool.getNumberOfFishes(0));
        assertEquals(1, optFishSchool.getNumberOfFishes(1));
        assertEquals(5, optFishSchool.getNumberOfFishes(4));
        assertEquals(3, optFishSchool.getNumberOfFishes(5));
        assertEquals(2, optFishSchool.getNumberOfFishes(6));
        assertEquals(1, optFishSchool.getNumberOfFishes(7));
    }

    @Test
    void testDay16()
    {
        // After 16 days: 1,2,1,6,0,1,2,3,3,4,1,2,3,3,3,4,4,5,5,6,8
        LanternFishSchool optFishSchool = new LanternFishSchool(FISH_SCHOOL_TEST_DATA);
        IntStream.range(0, 16).forEach(i -> optFishSchool.moveToNextDay());
        assertEquals(21, optFishSchool.getNumberOfFishes());
        assertEquals(4, optFishSchool.getNumberOfFishes(1));
        assertEquals(3, optFishSchool.getNumberOfFishes(2));
        assertEquals(2, optFishSchool.getNumberOfFishes(6));
        assertEquals(1, optFishSchool.getNumberOfFishes(0));
        assertEquals(5, optFishSchool.getNumberOfFishes(3));
        assertEquals(3, optFishSchool.getNumberOfFishes(4));
        assertEquals(2, optFishSchool.getNumberOfFishes(5));
        assertEquals(1, optFishSchool.getNumberOfFishes(8));
    }

    @Test
    void testDay17()
    {
        // After 17 days: 0,1,0,5,6,0,1,2,2,3,0,1,2,2,2,3,3,4,4,5,7,8
        LanternFishSchool optFishSchool = new LanternFishSchool(FISH_SCHOOL_TEST_DATA);
        IntStream.range(0, 17).forEach(i -> optFishSchool.moveToNextDay());
        assertEquals(22, optFishSchool.getNumberOfFishes());
        assertEquals(4, optFishSchool.getNumberOfFishes(0));
        assertEquals(3, optFishSchool.getNumberOfFishes(1));
        assertEquals(2, optFishSchool.getNumberOfFishes(5));
        assertEquals(1, optFishSchool.getNumberOfFishes(6));
        assertEquals(5, optFishSchool.getNumberOfFishes(2));
        assertEquals(3, optFishSchool.getNumberOfFishes(3));
        assertEquals(2, optFishSchool.getNumberOfFishes(4));
        assertEquals(1, optFishSchool.getNumberOfFishes(7));
        assertEquals(1, optFishSchool.getNumberOfFishes(8));
    }

    @Test
    void testDay18()
    {
        // After 18 days: 6,0,6,4,5,6,0,1,1,2,6,0,1,1,1,2,2,3,3,4,6,7,8,8,8,8
        LanternFishSchool optFishSchool = new LanternFishSchool(FISH_SCHOOL_TEST_DATA);
        IntStream.range(0, 18).forEach(i -> optFishSchool.moveToNextDay());
        assertEquals(26, optFishSchool.getNumberOfFishes());
        assertEquals(5, optFishSchool.getNumberOfFishes(6));
        assertEquals(3, optFishSchool.getNumberOfFishes(0));
        assertEquals(2, optFishSchool.getNumberOfFishes(4));
        assertEquals(1, optFishSchool.getNumberOfFishes(5));
        assertEquals(5, optFishSchool.getNumberOfFishes(1));
        assertEquals(3, optFishSchool.getNumberOfFishes(2));
        assertEquals(2, optFishSchool.getNumberOfFishes(3));
        assertEquals(1, optFishSchool.getNumberOfFishes(7));
        assertEquals(4, optFishSchool.getNumberOfFishes(8));
    }

    @Test
    void testDay256()
    {
        // 26984457539
        LanternFishSchool optFishSchool = new LanternFishSchool(FISH_SCHOOL_TEST_DATA);
        IntStream.range(0, 256).forEach(i -> optFishSchool.moveToNextDay());
        assertEquals(26984457539L, optFishSchool.getNumberOfFishes());
    }
}
