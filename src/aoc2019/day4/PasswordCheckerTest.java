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
}