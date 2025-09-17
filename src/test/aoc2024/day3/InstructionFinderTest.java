package aoc2024.day3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class InstructionFinderTest
{

    @Test
    void getInstructions()
    {
        InstructionFinder instructionFinder = new InstructionFinder("xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))", "mul\\([1-9][0-9]*,[1-9][0-9]*\\)");
        assertEquals(4, instructionFinder.getInstructions().size());
        assertEquals("mul(2,4)", instructionFinder.getInstructions().get(0));
        assertEquals("mul(5,5)", instructionFinder.getInstructions().get(1));
        assertEquals("mul(11,8)", instructionFinder.getInstructions().get(2));
        assertEquals("mul(8,5)", instructionFinder.getInstructions().get(3));
    }

    @Test
    void getEnabledInstructions()
    {
        InstructionFinder instructionFinder = new InstructionFinder("xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))", "mul\\([1-9][0-9]*,[1-9][0-9]*\\)", "do()", "don't()");
        assertEquals(2, instructionFinder.getInstructions().size());
        assertEquals("mul(2,4)", instructionFinder.getInstructions().get(0));
        assertEquals("mul(8,5)", instructionFinder.getInstructions().get(1));
    }
}