package aoc2015.day5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NiceStringTest
{

    @Test
    void isNiceString_ugknbfddgicrmopn()
    {
        assertTrue((new NiceString("ugknbfddgicrmopn")).isNiceString());
    }

    @Test
    void isNiceString_aaa()
    {
        assertTrue((new NiceString("aaa")).isNiceString());
    }

    @Test
    void isNiceString_jchzalrnumimnmhp()
    {
        assertFalse((new NiceString("jchzalrnumimnmhp")).isNiceString());
    }

    @Test
    void isNiceString_haegwjzuvuyypxyu()
    {
        assertFalse((new NiceString("haegwjzuvuyypxyu")).isNiceString());
    }

    @Test
    void isNiceString_dvszwmarrgswjxmb()
    {
        assertFalse((new NiceString("dvszwmarrgswjxmb")).isNiceString());
    }

    @Test
    void isNiceString_v2_qjhvhtzxzqqjkmpb()
    {
        assertTrue((new NiceString("qjhvhtzxzqqjkmpb")).isNiceString_v2());
    }

    @Test
    void isNiceString_v2_xxyxx()
    {
        assertTrue((new NiceString("xxyxx")).isNiceString_v2());
    }

    @Test
    void isNiceString_v2_uurcxstgmygtbstg()
    {
        assertFalse((new NiceString("uurcxstgmygtbstg")).isNiceString_v2());
    }

    @Test
    void isNiceString_v2_ieodomkazucvgmuy()
    {
        assertFalse((new NiceString("ieodomkazucvgmuy")).isNiceString_v2());
    }
}