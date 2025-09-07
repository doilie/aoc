package aoc2016.day5;

import aoc2015.day4.MD5HashGenerator;

public class MD5Password
{
    private final MD5HashGenerator md5HashGenerator;

    public MD5Password(String secretKey)
    {
        this.md5HashGenerator = new MD5HashGenerator(secretKey);
    }

    public String crackPassword(int passwordLength, int zeroPrefixLength)
    {
        StringBuilder sb = new StringBuilder(passwordLength);
        long startingIndex = 0;
        for (int i = 0; i < passwordLength; i++)
        {
            long secretKeyDecimalPart = md5HashGenerator.generateSecretKeyDecimalPart(zeroPrefixLength, startingIndex);
            sb.append(md5HashGenerator.generateMD5Hash(secretKeyDecimalPart).charAt(zeroPrefixLength));
            startingIndex = secretKeyDecimalPart;
        }
        return sb.toString();
    }

    public String crackPassword_v2(int passwordLength, int zeroPrefixLength)
    {
        long startingIndex = 0;
        String password = "x".repeat(passwordLength);
        while (password.contains("x"))
        {
            long secretKeyDecimalPart = md5HashGenerator.generateSecretKeyDecimalPart(zeroPrefixLength, startingIndex);
            String hash = md5HashGenerator.generateMD5Hash(secretKeyDecimalPart);
            System.out.println(hash);
            String passwordIdx = hash.substring(zeroPrefixLength, zeroPrefixLength + 1);
            if (passwordIdx.matches("\\d"))
            {
                int index = Integer.parseInt(passwordIdx);
                if (index < passwordLength && password.charAt(index) == 'x')
                {
                    String passwordValueAtIndex = hash.substring(zeroPrefixLength + 1, zeroPrefixLength + 2);
                    password = password.substring(0, index) + passwordValueAtIndex + password.substring(index + 1);
                    System.out.println(password);
                }
            }
            startingIndex = secretKeyDecimalPart;
        }
        return password;
    }
}
