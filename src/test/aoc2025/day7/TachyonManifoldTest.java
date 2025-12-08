package aoc2025.day7;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TachyonManifoldTest {

    private static TachyonManifold getTachyonManifoldSample() {
        String input = """
                .......S.......
                ...............
                .......^.......
                ...............
                ......^.^......
                ...............
                .....^.^.^.....
                ...............
                ....^.^...^....
                ...............
                ...^.^...^.^...
                ...............
                ..^...^.....^..
                ...............
                .^.^.^.^.^...^.
                ...............
                """;
        return new TachyonManifold(input);
    }

    @Test
    void testCreateTachyonManifold()
    {
        TachyonManifold tachyonManifold = getTachyonManifoldSample();
        assertEquals("7,0", tachyonManifold.getStartCoordinate());
        Set<String> splitterCoordinates = tachyonManifold.getSplitterCoordinates();
        Set<String> expectedCoordinates = Set.of(
                "7,2",
                "6,4", "8,4",
                "5,6", "7,6", "9,6",
                "4,8", "6,8", "10,8",
                "3,10", "5,10", "9,10", "11,10",
                "2,12", "6,12", "12,12",
                "1,14", "3,14", "5,14", "7,14", "9,14", "13,14");
        assertEquals(expectedCoordinates.size(), splitterCoordinates.size());
        for (String expectedCoordianate : expectedCoordinates)
        {
            assertTrue(splitterCoordinates.contains(expectedCoordianate));
        }
    }

    @Test
    void testSplitBeam()
    {
        TachyonManifold tachyonManifold = getTachyonManifoldSample();
        assertEquals(21, tachyonManifold.splitBeam());
    }


    @Test
    void testCountPaths()
    {
        TachyonManifold tachyonManifold = getTachyonManifoldSample();
        assertEquals(40, tachyonManifold.countPaths());
    }
}
