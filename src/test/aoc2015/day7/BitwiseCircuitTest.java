package aoc2015.day7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BitwiseCircuitTest
{
    @Test
    void testAllAssignmentCircuit()
    {
        BitwiseCircuit bitwiseCircuit = new BitwiseCircuit(new String[]{"123 -> x", "456 -> y"});
        bitwiseCircuit.runCircuit();
        assertEquals("x: 123", bitwiseCircuit.getResult("x"));
        assertEquals("y: 456", bitwiseCircuit.getResult("y"));
        assertEquals("z: not found", bitwiseCircuit.getResult("z"));
    }

    @Test
    void testSimpleCircuit()
    {
        BitwiseCircuit bitwiseCircuit = new BitwiseCircuit(new String[]{
                "123 -> x",
                "456 -> y",
                "x AND y -> d",
                "x OR y -> e",
                "x LSHIFT 2 -> f",
                "y RSHIFT 2 -> g",
                "NOT x -> h",
                "NOT y -> i",
        });
        bitwiseCircuit.runCircuit();
        assertEquals("x: 123", bitwiseCircuit.getResult("x"));
        assertEquals("y: 456", bitwiseCircuit.getResult("y"));
        assertEquals("z: not found", bitwiseCircuit.getResult("z"));
        assertEquals("d: 72", bitwiseCircuit.getResult("d"));
        assertEquals("e: 507", bitwiseCircuit.getResult("e"));
        assertEquals("f: 492", bitwiseCircuit.getResult("f"));
        assertEquals("g: 114", bitwiseCircuit.getResult("g"));
        assertEquals("h: 65412", bitwiseCircuit.getResult("h"));
        assertEquals("i: 65079", bitwiseCircuit.getResult("i"));
    }


    @Test
    void testSimpleCircuitRandomOrder()
    {
        BitwiseCircuit bitwiseCircuit = new BitwiseCircuit(new String[]{
                "x AND y -> d",
                "x OR y -> e",
                "123 -> x",
                "x LSHIFT 2 -> f",
                "NOT y -> i",
                "y RSHIFT 2 -> g",
                "456 -> y",
                "NOT x -> h",
        });
        bitwiseCircuit.runCircuit();
        assertEquals("x: 123", bitwiseCircuit.getResult("x"));
        assertEquals("y: 456", bitwiseCircuit.getResult("y"));
        assertEquals("z: not found", bitwiseCircuit.getResult("z"));
        assertEquals("d: 72", bitwiseCircuit.getResult("d"));
        assertEquals("e: 507", bitwiseCircuit.getResult("e"));
        assertEquals("f: 492", bitwiseCircuit.getResult("f"));
        assertEquals("g: 114", bitwiseCircuit.getResult("g"));
        assertEquals("h: 65412", bitwiseCircuit.getResult("h"));
        assertEquals("i: 65079", bitwiseCircuit.getResult("i"));
    }
}
