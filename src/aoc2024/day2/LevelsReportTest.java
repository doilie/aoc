package aoc2024.day2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LevelsReportTest
{

    @Test
    void isSafe_1()
    {
        LevelsReport levelsReport = LevelsReport.getLevelsReportFromString("7 6 4 2 1");
        assertTrue(levelsReport.isSafe());
    }

    @Test
    void isSafe_2()
    {
        LevelsReport levelsReport = LevelsReport.getLevelsReportFromString("1 2 7 8 9");
        assertFalse(levelsReport.isSafe());
    }

    @Test
    void isSafe_3()
    {
        LevelsReport levelsReport = LevelsReport.getLevelsReportFromString("9 7 6 2 1");
        assertFalse(levelsReport.isSafe());
    }

    @Test
    void isSafe_4()
    {
        LevelsReport levelsReport = LevelsReport.getLevelsReportFromString("1 3 2 4 5");
        assertFalse(levelsReport.isSafe());
    }

    @Test
    void isSafe_5()
    {
        LevelsReport levelsReport = LevelsReport.getLevelsReportFromString("8 6 4 4 1");
        assertFalse(levelsReport.isSafe());
    }

    @Test
    void isSafe_6()
    {
        LevelsReport levelsReport = LevelsReport.getLevelsReportFromString("1 3 6 7 9");
        assertTrue(levelsReport.isSafe());
    }

    @Test
    void isSafeWithoutOneLevel_1()
    {
        LevelsReport levelsReport = LevelsReport.getLevelsReportFromString("7 6 4 2 1");
        assertTrue(levelsReport.isSafeWithoutOneLevel());
    }

    @Test
    void isSafeWithoutOneLevel_2()
    {
        LevelsReport levelsReport = LevelsReport.getLevelsReportFromString("1 2 7 8 9");
        assertFalse(levelsReport.isSafeWithoutOneLevel());
    }

    @Test
    void isSafeWithoutOneLevel_3()
    {
        LevelsReport levelsReport = LevelsReport.getLevelsReportFromString("9 7 6 2 1");
        assertFalse(levelsReport.isSafeWithoutOneLevel());
    }

    @Test
    void isSafeWithoutOneLevel_4()
    {
        LevelsReport levelsReport = LevelsReport.getLevelsReportFromString("1 3 2 4 5");
        assertTrue(levelsReport.isSafeWithoutOneLevel());
    }

    @Test
    void isSafeWithoutOneLevel_5()
    {
        LevelsReport levelsReport = LevelsReport.getLevelsReportFromString("8 6 4 4 1");
        assertTrue(levelsReport.isSafeWithoutOneLevel());
    }

    @Test
    void isSafeWithoutOneLevel_6()
    {
        LevelsReport levelsReport = LevelsReport.getLevelsReportFromString("1 3 6 7 9");
        assertTrue(levelsReport.isSafeWithoutOneLevel());
    }
}