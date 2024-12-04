package aoc2024.day4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class XmasGridTest {

    @Test
    void countXmasMatches()
    {
        XmasGrid xmasGrid = new XmasGrid("""
                MMMSXXMASM
                MSAMXMSMSA
                AMXSXMAAMM
                MSAMASMSMX
                XMASAMXAMM
                XXAMMXXAMA
                SMSMSASXSS
                SAXAMASAAA
                MAMMMXMMMM
                MXMXAXMASX""");
        assertEquals(18, xmasGrid.countXmasMatches());
    }

    @Test
    void countCrossmasMatches()
    {
        XmasGrid xmasGrid = new XmasGrid("""
                MMMSXXMASM
                MSAMXMSMSA
                AMXSXMAAMM
                MSAMASMSMX
                XMASAMXAMM
                XXAMMXXAMA
                SMSMSASXSS
                SAXAMASAAA
                MAMMMXMMMM
                MXMXAXMASX""");
        assertEquals(9, xmasGrid.countCrossmasMatches());
    }

}