package aoc2020.day5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardingPassTest {

    @Test
    void getSeatId_FBFBBFFRLR()
    {
        assertEquals(357, (new BoardingPass("FBFBBFFRLR")).getSeatId());
    }

    @Test
    void getSeatId_BFFFBBFRRR()
    {
        assertEquals(567, (new BoardingPass("BFFFBBFRRR")).getSeatId());
    }

    @Test
    void getSeatId_FFFBBBFRRR()
    {
        assertEquals(119, (new BoardingPass("FFFBBBFRRR")).getSeatId());
    }

    @Test
    void getSeatId_BBFFBBFRLL()
    {
        assertEquals(820, (new BoardingPass("BBFFBBFRLL")).getSeatId());
    }
}