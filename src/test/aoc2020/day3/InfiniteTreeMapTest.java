package aoc2020.day3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InfiniteTreeMapTest {
    private static final String TEST_INPUT = """
            ..##.......
            #...#...#..
            .#....#..#.
            ..#.#...#.#
            .#...##..#.
            ..#.##.....
            .#.#.#....#
            .#........#
            #.##...#...
            #...##....#
            .#..#...#.#
                    """;

    @Test
    void getValueInPosition()
    {
        InfiniteTreeMap infiniteTreeMap = new InfiniteTreeMap(TEST_INPUT);
        assertEquals(InfiniteTreeMap.OPEN, infiniteTreeMap.getValueInPosition(3, 1));
        assertEquals(InfiniteTreeMap.TREE, infiniteTreeMap.getValueInPosition(6, 2));
        assertEquals(InfiniteTreeMap.TREE, infiniteTreeMap.getValueInPosition(30, 10));
    }
    @Test
    void countTreesInSlope31()
    {
        InfiniteTreeMap infiniteTreeMap = new InfiniteTreeMap(TEST_INPUT);
        assertEquals(7, infiniteTreeMap.countTreesInSlope(3, 1));
    }

    @Test
    void countTreesInSlope11()
    {
        InfiniteTreeMap infiniteTreeMap = new InfiniteTreeMap(TEST_INPUT);
        assertEquals(2, infiniteTreeMap.countTreesInSlope(1, 1));
    }

    @Test
    void countTreesInSlope51()
    {
        InfiniteTreeMap infiniteTreeMap = new InfiniteTreeMap(TEST_INPUT);
        assertEquals(3, infiniteTreeMap.countTreesInSlope(5, 1));
    }

    @Test
    void countTreesInSlope71()
    {
        InfiniteTreeMap infiniteTreeMap = new InfiniteTreeMap(TEST_INPUT);
        assertEquals(4, infiniteTreeMap.countTreesInSlope(7, 1));
    }

    @Test
    void countTreesInSlope12()
    {
        InfiniteTreeMap infiniteTreeMap = new InfiniteTreeMap(TEST_INPUT);
        assertEquals(2, infiniteTreeMap.countTreesInSlope(1, 2));
    }
}