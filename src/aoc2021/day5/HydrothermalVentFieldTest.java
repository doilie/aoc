package aoc2021.day5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HydrothermalVentFieldTest
{

    @Test
    void getPositionCount_1()
    {
        HydrothermalVentField hv = new HydrothermalVentField();
        hv.drawLine("1,1 -> 1,3", false);
        assertEquals(1, hv.getPositionCount(1, 1));
        assertEquals(1, hv.getPositionCount(1, 2));
        assertEquals(1, hv.getPositionCount(1, 3));
        assertNull(hv.getPositionCount(0, 0));
    }

    @Test
    void getPositionCount_2()
    {
        HydrothermalVentField hv = new HydrothermalVentField();
        hv.drawLine("9,7 -> 7,7", false);
        assertEquals(1, hv.getPositionCount(9, 7));
        assertEquals(1, hv.getPositionCount(8, 7));
        assertEquals(1, hv.getPositionCount(7, 7));
        assertNull(hv.getPositionCount(0, 0));
    }

    @Test
    void getPositionCount_Diagonal_1()
    {
        HydrothermalVentField hv = new HydrothermalVentField();
        hv.drawLine("8,0 -> 0,8", true);
        assertEquals(1, hv.getPositionCount(8, 0));
        assertEquals(1, hv.getPositionCount(7, 1));
        assertEquals(1, hv.getPositionCount(6, 2));
        assertEquals(1, hv.getPositionCount(5, 3));
        assertEquals(1, hv.getPositionCount(4, 4));
        assertEquals(1, hv.getPositionCount(3, 5));
        assertEquals(1, hv.getPositionCount(2, 6));
        assertEquals(1, hv.getPositionCount(1, 7));
        assertEquals(1, hv.getPositionCount(0, 8));
    }

    @Test
    void getNumberOfPointsWhereLinesOverlap_noDiagonal()
    {
        String input = """
                0,9 -> 5,9
                8,0 -> 0,8
                9,4 -> 3,4
                2,2 -> 2,1
                7,0 -> 7,4
                6,4 -> 2,0
                0,9 -> 2,9
                3,4 -> 1,4
                0,0 -> 8,8
                5,5 -> 8,2""";
        HydrothermalVentField hv = new HydrothermalVentField();
        String[] lines = input.split("\\n");
        for (String line : lines)
        {
            hv.drawLine(line, false);
        }
        assertEquals(5, hv.getNumberOfPointsWhereLinesOverlap());
    }


    @Test
    void getNumberOfPointsWhereLinesOverlap_withDiagonal()
    {
        String input = """
                0,9 -> 5,9
                8,0 -> 0,8
                9,4 -> 3,4
                2,2 -> 2,1
                7,0 -> 7,4
                6,4 -> 2,0
                0,9 -> 2,9
                3,4 -> 1,4
                0,0 -> 8,8
                5,5 -> 8,2""";
        HydrothermalVentField hv = new HydrothermalVentField();
        String[] lines = input.split("\\n");
        for (String line : lines)
        {
            hv.drawLine(line, true);
        }
        assertEquals(12, hv.getNumberOfPointsWhereLinesOverlap());
    }

    @Test
    void getNumberOfPointsWhereLinesOverlap_sameLines()
    {
        String input = """
                0,9 -> 5,9
                0,9 -> 5,9
                0,9 -> 5,9""";
        HydrothermalVentField hv = new HydrothermalVentField();
        String[] lines = input.split("\\n");
        for (String line : lines)
        {
            hv.drawLine(line, false);
        }
        assertEquals(6, hv.getNumberOfPointsWhereLinesOverlap());
    }

}