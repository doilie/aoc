package aoc2019.day4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordCheckerTest {

    @Test
    void isValid_111111()
    {
        PasswordChecker pc = new PasswordChecker();
        assertTrue(pc.isValid("111111"));
    }

    @Test
    void isValid_223450()
    {
        PasswordChecker pc = new PasswordChecker();
        assertFalse(pc.isValid("223450"));
    }

    @Test
    void isValid_123789()
    {
        PasswordChecker pc = new PasswordChecker();
        assertFalse(pc.isValid("123789"));
    }

    @Test
    void isValid_v2_112233()
    {
        PasswordChecker pc = new PasswordChecker();
        assertTrue(pc.isValid_v2("112233"));
    }

    @Test
    void isValid_v2_123444()
    {
        PasswordChecker pc = new PasswordChecker();
        assertFalse(pc.isValid_v2("123444"));
    }

    @Test
    void isValid_v2_111122()
    {
        PasswordChecker pc = new PasswordChecker();
        assertTrue(pc.isValid_v2("111122"));
    }

}