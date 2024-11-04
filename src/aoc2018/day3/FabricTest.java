package aoc2018.day3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FabricTest {
    private static final String TEST_INPUT = "#123 @ 3,2: 5x4";
    @Test
    void getFabricPositionOwners()
    {
        ElfFabricOwnership efo = new ElfFabricOwnership(TEST_INPUT);
        Fabric f = new Fabric();
        f.addElfOwnership(efo);
        assertEquals(1, f.getFabricPositionOwners(3, 2).size());
        assertEquals(1, f.getFabricPositionOwners(4, 2).size());
        assertEquals(1, f.getFabricPositionOwners(5, 2).size());
        assertEquals(1, f.getFabricPositionOwners(6, 2).size());
        assertEquals(1, f.getFabricPositionOwners(7, 2).size());
        assertNull(f.getFabricPositionOwners(8, 2));
        assertEquals(1, f.getFabricPositionOwners(7, 3).size());
        assertEquals(1, f.getFabricPositionOwners(7, 4).size());
        assertEquals(1, f.getFabricPositionOwners(7, 5).size());
        assertNull(f.getFabricPositionOwners(7, 6));
    }

    @Test
    void countFabricPositionsWithOverlap()
    {
        Fabric f = new Fabric();
        f.addElfOwnership(new ElfFabricOwnership("#1 @ 1,3: 4x4"));
        f.addElfOwnership(new ElfFabricOwnership("#2 @ 3,1: 4x4"));
        f.addElfOwnership(new ElfFabricOwnership("#3 @ 5,5: 2x2"));
        assertEquals(4, f.countFabricPositionsWithOverlap());
    }
}