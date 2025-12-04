package aoc2025.day4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToiletPaperGridTest {
    @Test
    public void testGetToiletPaperAroundCoordinateBasic()
    {
        ToiletPaperGrid toiletPaperGrid = new ToiletPaperGrid("""
                @@@
                @@@
                @@@
                """);
        assertEquals(8, toiletPaperGrid.getToiletPaperAroundCoordinate("1,1").size());
    }

    @Test
    public void testGetToiletPaperAroundCoordinateSampleInput()
    {
        ToiletPaperGrid toiletPaperGrid = new ToiletPaperGrid("""
                ..@@.@@@@.
                @@@.@.@.@@
                @@@@@.@.@@
                @.@@@@..@.
                @@.@@@@.@@
                .@@@@@@@.@
                .@.@.@.@@@
                @.@@@.@@@@
                .@@@@@@@@.
                @.@.@@@.@.
                """);
        assertEquals(13, toiletPaperGrid.getToiletPaperByForklift().size());
    }

    @Test
    public void testGetToiletPaperUntilLimit()
    {
        ToiletPaperGrid toiletPaperGrid = new ToiletPaperGrid("""
                ..@@.@@@@.
                @@@.@.@.@@
                @@@@@.@.@@
                @.@@@@..@.
                @@.@@@@.@@
                .@@@@@@@.@
                .@.@.@.@@@
                @.@@@.@@@@
                .@@@@@@@@.
                @.@.@@@.@.
                """);
        assertEquals(43, toiletPaperGrid.getToiletPaperUntilLimit().size());
    }
}
