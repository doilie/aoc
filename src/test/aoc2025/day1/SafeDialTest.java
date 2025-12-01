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

    @Test
    void testGetPassword()
    {
        SafeDial safeDial = new SafeDial(50);
        String[] instructions = new String[] {
                "L68",
                "L30",
                "R48",
                "L5",
                "R60",
                "L55",
                "L1",
                "L99",
                "R14",
                "L82"
        };
        assertEquals(6, safeDial.getPassword(instructions));
    }

    @Test
    void testGetPasswordR1000()
    {
        SafeDial safeDial = new SafeDial(50);
        String[] instructions = new String[] {
                "R1000"
        };
        assertEquals(10, safeDial.getPassword(instructions));
    }

    @Test
    void testGetPasswordL150()
    {
        SafeDial safeDial = new SafeDial(50);
        String[] instructions = new String[] {
                "L150"
        };
        assertEquals(2, safeDial.getPassword(instructions));
    }

    @Test
    void testGetPasswordL998()
    {
        SafeDial safeDial = new SafeDial(50);
        String[] instructions = new String[] {
                "L998"
        };
        assertEquals(10, safeDial.getPassword(instructions));
    }

    @Test
    void testGetPasswordL998From0()
    {
        SafeDial safeDial = new SafeDial(0);
        String[] instructions = new String[] {
                "L998"
        };
        assertEquals(9, safeDial.getPassword(instructions));
    }

    @Test
    void testGetDialPointsToZero50L68()
    {
        SafeDial safeDial = new SafeDial(50);
        assertEquals(1, safeDial.getDialPointsToZero("L68"));
    }

    @Test
    void testGetDialPointsToZero82L30()
    {
        SafeDial safeDial = new SafeDial(82);
        assertEquals(0, safeDial.getDialPointsToZero("L30"));
    }

    @Test
    void testGetDialPointsToZero52R48()
    {
        SafeDial safeDial = new SafeDial(52);
        assertEquals(1, safeDial.getDialPointsToZero("R48"));
    }

    @Test
    void testGetDialPointsToZero0L5()
    {
        SafeDial safeDial = new SafeDial(0);
        assertEquals(0, safeDial.getDialPointsToZero("L5"));
    }

    @Test
    void testGetDialPointsToZero95R60()
    {
        SafeDial safeDial = new SafeDial(95);
        assertEquals(1, safeDial.getDialPointsToZero("R60"));
    }

    @Test
    void testGetDialPointsToZero55L55()
    {
        SafeDial safeDial = new SafeDial(55);
        assertEquals(1, safeDial.getDialPointsToZero("L55"));
    }

    @Test
    void testGetDialPointsToZero0L1()
    {
        SafeDial safeDial = new SafeDial(0);
        assertEquals(0, safeDial.getDialPointsToZero("L1"));
    }

    @Test
    void testGetDialPointsToZero99L99()
    {
        SafeDial safeDial = new SafeDial(99);
        assertEquals(1, safeDial.getDialPointsToZero("L99"));
    }

    @Test
    void testGetDialPointsToZero0R14()
    {
        SafeDial safeDial = new SafeDial(0);
        assertEquals(0, safeDial.getDialPointsToZero("R14"));
    }

    @Test
    void testGetDialPointsToZero14L82()
    {
        SafeDial safeDial = new SafeDial(14);
        assertEquals(1, safeDial.getDialPointsToZero("L82"));
    }

    @Test
    void testGetDialPointsToZero50L150()
    {
        SafeDial safeDial = new SafeDial(50);
        assertEquals(2, safeDial.getDialPointsToZero("L150"));
    }

    @Test
    void testGetDialPointsToZero50R1000()
    {
        SafeDial safeDial = new SafeDial(50);
        assertEquals(10, safeDial.getDialPointsToZero("R1000"));
    }

    @Test
    void testGetDialPointsToZero99R101()
    {
        SafeDial safeDial = new SafeDial(99);
        assertEquals(2, safeDial.getDialPointsToZero("R101"));
    }

    @Test
    void testGetDialPointsToZero1L101()
    {
        SafeDial safeDial = new SafeDial(1);
        assertEquals(2, safeDial.getDialPointsToZero("L101"));
    }

    @Test
    void testTurnL811()
    {
        SafeDial safeDial = new SafeDial(0);
        safeDial.turn("L811");
        assertEquals(89, safeDial.getCurrentPosition());
    }
}


