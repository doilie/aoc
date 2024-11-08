package aoc2021.day3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinaryDiagnosticTest {
    private static final String TEST_INPUT = """
                00100
                11110
                10110
                10111
                10101
                01111
                00111
                11100
                10000
                11001
                00010
                01010""";

    @Test
    void getGammaRate()
    {
        BinaryDiagnostic bd = new BinaryDiagnostic(TEST_INPUT);
        assertEquals("10110", bd.getGammaRate());
    }

    @Test
    void getEpsilonRate()
    {
        BinaryDiagnostic bd = new BinaryDiagnostic(TEST_INPUT);
        assertEquals("01001", bd.getEpsilonRate());
    }
}