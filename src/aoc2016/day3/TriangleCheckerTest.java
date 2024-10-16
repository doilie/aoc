package aoc2016.day3;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TriangleCheckerTest {
    @Test
    public void checkTriangleSidesIfRow()
    {
        TriangleChecker oneTriangleFromRow = TriangleChecker.createOneTriangleFromRow("  5  10  25");
        assertArrayEquals(new Integer[]{5, 10, 25}, oneTriangleFromRow.getSides());
    }

    @Test
    public void checkTriangleSidesIfColumn()
    {
        List<TriangleChecker> threeTrianglesFromRows = TriangleChecker.createThreeTrianglesFromRows(
                """
                        101 301 501
                        102 302 502
                        103 303 503""");
        assertEquals(3, threeTrianglesFromRows.size());
        assertArrayEquals(new Integer[]{101, 102, 103}, threeTrianglesFromRows.get(0).getSides());
        assertArrayEquals(new Integer[]{301, 302, 303}, threeTrianglesFromRows.get(1).getSides());
        assertArrayEquals(new Integer[]{501, 502, 503}, threeTrianglesFromRows.get(2).getSides());
    }

    @Test
    public void checkInvalidTriangle()
    {
        TriangleChecker triangle = new TriangleChecker(List.of(5, 10, 25));
        assertFalse(triangle.isValid());
    }

    @Test
    public void checkValidTriangle()
    {
        TriangleChecker triangle = new TriangleChecker(List.of(5, 10, 8));
        assertTrue(triangle.isValid());
    }
}