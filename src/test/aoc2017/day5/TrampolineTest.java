package aoc2017.day5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrampolineTest {

    @Test
    void escapeMaze()
    {
        Trampoline trampoline = new Trampoline("""
                0
                3
                0
                1
                -3""");
        assertEquals(5, trampoline.escapeMaze());
    }


    @Test
    void escapeMaze_v2()
    {
        Trampoline trampoline = new Trampoline("""
                0
                3
                0
                1
                -3""");
        assertEquals(10, trampoline.escapeMaze_v2());
    }
}