package aoc2018.day3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElfFabricOwnershipTest
{
    private static final String TEST_INPUT = "#123 @ 3,2: 5x4";
    @Test
    void getElfId_testInput()
    {
        ElfFabricOwnership efo = new ElfFabricOwnership(TEST_INPUT);
        assertEquals(123, efo.getElfId());
    }

    @Test
    void getElfId_testOneDigitId()
    {
        ElfFabricOwnership efo = new ElfFabricOwnership("#1 @ 551,185: 21x10");
        assertEquals(1, efo.getElfId());
    }

    @Test
    void getStartX() {
        ElfFabricOwnership efo = new ElfFabricOwnership(TEST_INPUT);
        assertEquals(3, efo.getStartX());
    }

    @Test
    void getStartY() {
        ElfFabricOwnership efo = new ElfFabricOwnership(TEST_INPUT);
        assertEquals(2, efo.getStartY());
    }

    @Test
    void getWidth() {
        ElfFabricOwnership efo = new ElfFabricOwnership(TEST_INPUT);
        assertEquals(5, efo.getWidth());
    }

    @Test
    void getHeight() {
        ElfFabricOwnership efo = new ElfFabricOwnership(TEST_INPUT);
        assertEquals(4, efo.getHeight());
    }
}