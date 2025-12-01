package aoc2025.day1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SafeDialTest
{
    @Test
    public void testSampleCase()
    {
        SafeDial safeDial = new SafeDial(50);
        safeDial.turn("L68");
        assertEquals(82, safeDial.getCurrentPosition());
        safeDial.turn("L30");
        assertEquals(52, safeDial.getCurrentPosition());
        safeDial.turn("R48");
        assertEquals(0, safeDial.getCurrentPosition());
        safeDial.turn("L5");
        assertEquals(95, safeDial.getCurrentPosition());
        safeDial.turn("R60");
        assertEquals(55, safeDial.getCurrentPosition());
        safeDial.turn("L55");
        assertEquals(0, safeDial.getCurrentPosition());
        safeDial.turn("L1");
        assertEquals(99, safeDial.getCurrentPosition());
        safeDial.turn("L99");
        assertEquals(0, safeDial.getCurrentPosition());
        safeDial.turn("R14");
        assertEquals(14, safeDial.getCurrentPosition());
        safeDial.turn("L82");
        assertEquals(32, safeDial.getCurrentPosition());
    }
}
