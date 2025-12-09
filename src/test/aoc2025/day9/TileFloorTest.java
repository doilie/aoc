package aoc2025.day9;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TileFloorTest {
    private static String getSampleData()
    {
        return """
                7,1
                11,1
                11,7
                9,7
                9,5
                2,5
                2,3
                7,3
                """;
    }

    @Test
    void testGetLargestRedRectangleArea()
    {
        TileFloor tileFloor = new TileFloor(getSampleData());
        assertEquals(BigInteger.valueOf(50), tileFloor.getLargestRedRectangleArea());
    }
}
