package aoc2016.day5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MD5PasswordTest {

    @Test
    void crackPassword_abc()
    {
        assertEquals("18f47a30", (new MD5Password("abc")).crackPassword(8, 5));
    }

    @Test
    void crackPassword_v2_abc()
    {
        assertEquals("05ace8e3", (new MD5Password("abc")).crackPassword_v2(8, 5));
    }
}