package aoc2015.day7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BitwiseLogicGateOperationTest
{
    @Test
    void testSimpleAssignment()
    {
        BitwiseLogicGateOperation bitwiseLogicGateOperation = new BitwiseLogicGateOperation("123 -> x");
        assertEquals("x", bitwiseLogicGateOperation.getId());
        assertTrue(bitwiseLogicGateOperation.hasResult());
        assertEquals(123, bitwiseLogicGateOperation.getResult());
    }

    @Test
    void testSimpleAssignmentToId()
    {
        BitwiseLogicGateOperation bitwiseLogicGateOperation = new BitwiseLogicGateOperation("ab -> x");
        assertEquals("x", bitwiseLogicGateOperation.getId());
        assertFalse(bitwiseLogicGateOperation.hasResult());
    }

    @Test
    void testNotOperation()
    {
        BitwiseLogicGateOperation bitwiseLogicGateOperation = new BitwiseLogicGateOperation("NOT x -> h");
        assertEquals("h", bitwiseLogicGateOperation.getId());
        bitwiseLogicGateOperation.performOperation();
        assertFalse(bitwiseLogicGateOperation.hasResult());
        bitwiseLogicGateOperation.setOp1(123);
        bitwiseLogicGateOperation.performOperation();
        assertTrue(bitwiseLogicGateOperation.hasResult());
        assertEquals(65412, bitwiseLogicGateOperation.getResult());
    }

    @Test
    void testAndOperation()
    {
        BitwiseLogicGateOperation bitwiseLogicGateOperation = new BitwiseLogicGateOperation("x AND y -> d");
        assertEquals("d", bitwiseLogicGateOperation.getId());
        bitwiseLogicGateOperation.performOperation();
        assertFalse(bitwiseLogicGateOperation.hasResult());
        bitwiseLogicGateOperation.setOp1(123);
        bitwiseLogicGateOperation.setOp2(456);
        bitwiseLogicGateOperation.performOperation();
        assertTrue(bitwiseLogicGateOperation.hasResult());
        assertEquals(72, bitwiseLogicGateOperation.getResult());
    }

    @Test
    void testOrOperation()
    {
        BitwiseLogicGateOperation bitwiseLogicGateOperation = new BitwiseLogicGateOperation("x OR y -> e");
        assertEquals("e", bitwiseLogicGateOperation.getId());
        bitwiseLogicGateOperation.performOperation();
        assertFalse(bitwiseLogicGateOperation.hasResult());
        bitwiseLogicGateOperation.setOp1(123);
        bitwiseLogicGateOperation.setOp2(456);
        bitwiseLogicGateOperation.performOperation();
        assertTrue(bitwiseLogicGateOperation.hasResult());
        assertEquals(507, bitwiseLogicGateOperation.getResult());
    }

    @Test
    void testLShiftOperation()
    {
        BitwiseLogicGateOperation bitwiseLogicGateOperation = new BitwiseLogicGateOperation("x LSHIFT 2 -> f");
        assertEquals("f", bitwiseLogicGateOperation.getId());
        bitwiseLogicGateOperation.performOperation();
        assertFalse(bitwiseLogicGateOperation.hasResult());
        bitwiseLogicGateOperation.setOp1(123);
        bitwiseLogicGateOperation.performOperation();
        assertTrue(bitwiseLogicGateOperation.hasResult());
        assertEquals(492, bitwiseLogicGateOperation.getResult());
    }

    @Test
    void testRShiftOperation()
    {
        BitwiseLogicGateOperation bitwiseLogicGateOperation = new BitwiseLogicGateOperation("y RSHIFT 2 -> g");
        assertEquals("g", bitwiseLogicGateOperation.getId());
        bitwiseLogicGateOperation.performOperation();
        assertFalse(bitwiseLogicGateOperation.hasResult());
        bitwiseLogicGateOperation.setOp1(456);
        bitwiseLogicGateOperation.performOperation();
        assertTrue(bitwiseLogicGateOperation.hasResult());
        assertEquals(114, bitwiseLogicGateOperation.getResult());
    }

}
