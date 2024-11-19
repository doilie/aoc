package aoc2015.day4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MD5HashGeneratorTest
{
    @Test
    public void generateMD5Hash1()
    {
        MD5HashGenerator md5HashGenerator = new MD5HashGenerator("abcdef");
        String result = md5HashGenerator.generateMD5Hash(1);
        assertNotNull(result);
    }

    @Test
    public void generateMD5Hash609043()
    {
        MD5HashGenerator md5HashGenerator = new MD5HashGenerator("abcdef");
        String result = md5HashGenerator.generateMD5Hash(609043);
        assertEquals("00000", result.substring(0, 5));
    }

    @Test
    public void generateSecretKeyDecimalPart_abcdef()
    {
        MD5HashGenerator md5HashGenerator = new MD5HashGenerator("abcdef");
        long adventCoin = md5HashGenerator.generateSecretKeyDecimalPart(5, 0);
        assertEquals(609043, adventCoin);
    }

    @Test
    public void generateSecretKeyDecimalPart_pqrstuv()
    {
        MD5HashGenerator md5HashGenerator = new MD5HashGenerator("pqrstuv");
        long adventCoin = md5HashGenerator.generateSecretKeyDecimalPart(5, 0);
        assertEquals(1048970, adventCoin);
    }
}