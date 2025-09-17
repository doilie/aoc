package aoc2017.day4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PassphraseTest {

    @Test
    void isValid_1()
    {
        Passphrase passphrase = new Passphrase("aa bb cc dd ee");
        assertTrue(passphrase.isValid());
    }

    @Test
    void isValid_2()
    {
        Passphrase passphrase = new Passphrase("aa bb cc dd aa");
        assertFalse(passphrase.isValid());
    }

    @Test
    void isValid_3()
    {
        Passphrase passphrase = new Passphrase("aa bb cc dd aaa");
        assertTrue(passphrase.isValid());
    }

    @Test
    void alphabetizeWords()
    {
        assertEquals(Passphrase.alphabetizeWord("oiii"), Passphrase.alphabetizeWord("ioii"));
    }

    @Test
    void isValid_NoAnagram_1()
    {
        Passphrase passphrase = new Passphrase("abcde fghij");
        assertTrue(passphrase.isValid_NoAnagram());
    }

    @Test
    void isValid_NoAnagram_2()
    {
        Passphrase passphrase = new Passphrase("abcde xyz ecdab");
        assertFalse(passphrase.isValid_NoAnagram());
    }

    @Test
    void isValid_NoAnagram_3()
    {
        Passphrase passphrase = new Passphrase("a ab abc abd abf abj");
        assertTrue(passphrase.isValid_NoAnagram());
    }

    @Test
    void isValid_NoAnagram_4()
    {
        Passphrase passphrase = new Passphrase("iiii oiii ooii oooi oooo");
        assertTrue(passphrase.isValid_NoAnagram());
    }

    @Test
    void isValid_NoAnagram_5()
    {
        Passphrase passphrase = new Passphrase("oiii ioii iioi iiio");
        assertFalse(passphrase.isValid_NoAnagram());
    }
}