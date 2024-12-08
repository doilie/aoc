package aoc2024.day6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LabMapTest
{
    private final String TEST_INPUT = """
            ....#.....
            .........#
            ..........
            ..#.......
            .......#..
            ..........
            .#..^.....
            ........#.
            #.........
            ......#...""";
    @Test
    void testToString()
    {
        LabMap labMap = new LabMap(TEST_INPUT);
        assertEquals("""
                ....#.....
                .........#
                ..........
                ..#.......
                .......#..
                ..........
                .#..^.....
                ........#.
                #.........
                ......#...
                """, labMap.toString());
    }

    @Test
    void testGetCurrentPosition()
    {
        LabMap labMap = new LabMap(TEST_INPUT);
        assertEquals("4,6", labMap.getCurrentPosition());
    }

    @Test
    void testGetVisitedPositionsCount()
    {
        LabMap labMap = new LabMap(TEST_INPUT);
        labMap.hasMovedUntilExit();
        System.out.println(labMap);
        assertEquals(41, labMap.getVisitedPositionsCount());
    }

    @Test
    void testTryAllPositionsAsObstruction()
    {
        LabMap labMap = new LabMap(TEST_INPUT);
        labMap.hasMovedUntilExit();
        System.out.println(labMap);
        assertEquals(6, labMap.countPositionsThatCauseLoop());
    }

    @Test
    void testGetVisitedPositionsCount_1()
    {
        LabMap labMap = new LabMap("""
                .#..
                #..#
                ....
                ^...
                #...
                .#..""");
        labMap.hasMovedUntilExit();
        System.out.println(labMap);
        assertEquals(5, labMap.getVisitedPositionsCount());
    }

    @Test
    void testTryAllPositionsAsObstruction_1()
    {
        LabMap labMap = new LabMap("""
                .#..
                #..#
                ....
                ^...
                #...
                .#..""");
        labMap.hasMovedUntilExit();
        System.out.println(labMap);
        assertEquals(1, labMap.countPositionsThatCauseLoop());
    }
}