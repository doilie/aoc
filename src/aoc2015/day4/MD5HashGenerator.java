package aoc2015.day4;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5HashGenerator
{
    private final String secretKey;

    public MD5HashGenerator(String secretKey)
    {
        this.secretKey = secretKey;
    }

    public String generateMD5Hash(long number)
    {
        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update((secretKey + number).getBytes());
            byte[] digest = md.digest();
            return String.format("%032x", new BigInteger(1, digest));
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public long generateSecretKeyDecimalPart(int zeroPrefixLength)
    {
        String adventCoin = "";
        long i = 0;
        String prefix = "0".repeat(zeroPrefixLength);
        while (!adventCoin.startsWith(prefix))
        {
            i++;
            adventCoin = generateMD5Hash(i);
        }
        return i;
    }
}
