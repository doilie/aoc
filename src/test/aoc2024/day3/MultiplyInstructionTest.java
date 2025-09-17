package aoc2024.day3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MultiplyInstructionTest
{

    @Test
    void getResult()
    {
        MultiplyInstruction multiplyInstruction = new MultiplyInstruction("mul(2,4)");
        assertEquals(8, multiplyInstruction.getResult());
    }
}