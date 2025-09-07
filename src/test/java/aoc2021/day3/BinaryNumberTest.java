package aoc2021.day3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinaryNumberTest
{

    @Test
    void getMostCommonBit1()
    {
        BinaryNumber binaryNumber = new BinaryNumber("011110011100");
        assertEquals(1, binaryNumber.getMostCommonBit());
    }

    @Test
    void getMostCommonBit2()
    {
        BinaryNumber binaryNumber = new BinaryNumber("010001010101");
        assertEquals(0, binaryNumber.getMostCommonBit());
    }

    @Test
    void getMostCommonBit3()
    {
        BinaryNumber binaryNumber = new BinaryNumber("111111110000");
        assertEquals(1, binaryNumber.getMostCommonBit());
    }

    @Test
    void getMostCommonBit4()
    {
        BinaryNumber binaryNumber = new BinaryNumber("011101100011");
        assertEquals(1, binaryNumber.getMostCommonBit());
    }

    @Test
    void getMostCommonBit5()
    {
        BinaryNumber binaryNumber = new BinaryNumber("000111100100");
        assertEquals(0, binaryNumber.getMostCommonBit());
    }

    @Test
    void getLeastCommonBit1()
    {
        BinaryNumber binaryNumber = new BinaryNumber("011110011100");
        assertEquals(0, binaryNumber.getLeastCommonBit());
    }

    @Test
    void getLeastCommonBit2()
    {
        BinaryNumber binaryNumber = new BinaryNumber("010001010101");
        assertEquals(1, binaryNumber.getLeastCommonBit());
    }

    @Test
    void getLeastCommonBit3()
    {
        BinaryNumber binaryNumber = new BinaryNumber("111111110000");
        assertEquals(0, binaryNumber.getLeastCommonBit());
    }

    @Test
    void getLeastCommonBit4()
    {
        BinaryNumber binaryNumber = new BinaryNumber("011101100011");
        assertEquals(0, binaryNumber.getLeastCommonBit());
    }

    @Test
    void getLeastCommonBit5()
    {
        BinaryNumber binaryNumber = new BinaryNumber("000111100100");
        assertEquals(1, binaryNumber.getLeastCommonBit());
    }

    @Test
    void convertToDecimal1()
    {
        BinaryNumber binaryNumber = new BinaryNumber("10110");
        assertEquals(22, binaryNumber.convertToDecimal());
    }

    @Test
    void convertToDecimal2()
    {
        BinaryNumber binaryNumber = new BinaryNumber("01001");
        assertEquals(9, binaryNumber.convertToDecimal());
    }
}