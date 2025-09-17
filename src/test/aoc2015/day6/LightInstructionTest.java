package aoc2015.day6;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LightInstructionTest {
    @Test
    public void testTurnOnFullGrid_StringInput() {
        LightInstruction instruction = new LightInstruction("turn on 0,0 through 999,999");

        assertEquals(0, instruction.getStartX());
        assertEquals(0, instruction.getStartY());
        assertEquals(999, instruction.getEndX());
        assertEquals(999, instruction.getEndY());
        assertEquals(LightAction.TURN_ON, instruction.getAction());
    }

    @Test
    public void testToggleFirstLine_StringInput() {
        LightInstruction instruction = new LightInstruction("toggle 0,0 through 999,0");

        assertEquals(0, instruction.getStartX());
        assertEquals(0, instruction.getStartY());
        assertEquals(999, instruction.getEndX());
        assertEquals(0, instruction.getEndY());
        assertEquals(LightAction.TOGGLE, instruction.getAction());
    }
    
    @Test
    public void testTurnOffMiddleFourLights_StringInput() {
        LightInstruction instruction = new LightInstruction("turn off 499,499 through 500,500");

        assertEquals(499, instruction.getStartX());
        assertEquals(499, instruction.getStartY());
        assertEquals(500, instruction.getEndX());
        assertEquals(500, instruction.getEndY());
        assertEquals(LightAction.TURN_OFF, instruction.getAction());
    }
}
