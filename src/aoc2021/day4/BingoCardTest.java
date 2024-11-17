package aoc2021.day4;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BingoCardTest
{

    @Test
    void isPositionMarked()
    {
        BingoCard bingoCard = new BingoCard("""
                22 13 17 11  0
                 8  2 23  4 24
                21  9 14 16  7
                 6 10  3 18  5
                 1 12 20 15 19""");
        assertFalse(bingoCard.isPositionMarked("4,2"));
        bingoCard.markNumber(7);
        assertTrue(bingoCard.isPositionMarked("4,2"));
    }

    @Test
    void hasWon_Loser_1()
    {
        BingoCard bingoCard = new BingoCard("""
                22 13 17 11  0
                 8  2 23  4 24
                21  9 14 16  7
                 6 10  3 18  5
                 1 12 20 15 19""");
        List<Integer> numbersCalled = List.of(7,4,9,5,11,17,23,2,0,14,21,24);
        for (Integer number : numbersCalled)
        {
            bingoCard.markNumber(number);
            assertFalse(bingoCard.hasWon());
        }
    }

    @Test
    void hasWon_Loser_2()
    {
        BingoCard bingoCard = new BingoCard("""
                3 15  0  2 22
                9 18 13 17  5
               19  8  7 25 23
               20 11 10 24  4
               14 21 16 12  6""");
        List<Integer> numbersCalled = List.of(7,4,9,5,11,17,23,2,0,14,21,24);
        for (Integer number : numbersCalled)
        {
            bingoCard.markNumber(number);
            assertFalse(bingoCard.hasWon());
        }
    }

    @Test
    void hasWon_Winner()
    {
        BingoCard bingoCard = new BingoCard("""
                14 21 17 24  4
                10 16 15  9 19
                18  8 23 26 20
                22 11 13  6  5
                 2  0 12  3  7""");
        List<Integer> numbersCalled = List.of(7,4,9,5,11,17,23,2,0,14,21,24);
        for (Integer number : numbersCalled)
        {
            bingoCard.markNumber(number);
            if (number != 24)
            {
                assertFalse(bingoCard.hasWon());
            }
            else
            {
                assertTrue(bingoCard.hasWon());
            }
        }
    }

    @Test
    void computeScore()
    {
        BingoCard bingoCard = new BingoCard("""
                14 21 17 24  4
                10 16 15  9 19
                18  8 23 26 20
                22 11 13  6  5
                 2  0 12  3  7""");
        List<Integer> numbersCalled = List.of(7,4,9,5,11,17,23,2,0,14,21,24);
        for (Integer number : numbersCalled)
        {
            bingoCard.markNumber(number);
        }
        assertEquals(4512, bingoCard.computeScore(24));
    }
}