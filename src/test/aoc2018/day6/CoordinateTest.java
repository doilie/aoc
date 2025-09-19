package aoc2018.day6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CoordinateTest {
    @Test
    void testGetDistance()
    {
        Coordinate c1 = new Coordinate(1, 1);
        Coordinate c2 = new Coordinate(1, 6);
        assertEquals(5, c1.getDistance(c2));
        assertEquals(5, c2.getDistance(c1));
    }
}
