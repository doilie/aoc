package aoc2016.day7;

import java.util.ArrayList;
import java.util.List;

public class IPv7Address
{
    private static final int ABBA_SIZE = 4;
    private static final int ABA_SIZE = 3;

    private final List<String> stringsOutsideBrackets = new ArrayList<>();
    private final List<String> stringsInsideBrackets = new ArrayList<>();

    IPv7Address(String address)
    {
        int nextOutsideStartIdx = 0;
        int nextOpenBracketIdx = address.indexOf("[");
        int nextCloseBracketIdx = address.indexOf("]");

        while (nextOpenBracketIdx != -1)
        {
            stringsOutsideBrackets.add(address.substring(nextOutsideStartIdx, nextOpenBracketIdx));
            stringsInsideBrackets.add(address.substring(nextOpenBracketIdx + 1, nextCloseBracketIdx));
            nextOutsideStartIdx = nextCloseBracketIdx + 1;
            nextOpenBracketIdx = address.indexOf("[", nextOutsideStartIdx);
            nextCloseBracketIdx = address.indexOf("]", nextOutsideStartIdx);
        }
        stringsOutsideBrackets.add(address.substring(nextOutsideStartIdx).trim());
    }

    List<String> getStringsOutsideBrackets()
    {
        return stringsOutsideBrackets;
    }

    List<String> getStringsInsideBrackets()
    {
        return stringsInsideBrackets;
    }

    boolean isTLS()
    {
        for (String stringInsideBracket : stringsInsideBrackets)
        {
            if (!getPalindromeStrings(stringInsideBracket, ABBA_SIZE).isEmpty())
            {
                return false;
            }
        }
        for (String stringOutsideBracket : stringsOutsideBrackets)
        {
            if (!getPalindromeStrings(stringOutsideBracket, ABBA_SIZE).isEmpty())
            {
                return true;
            }
        }
        return false;
    }

    boolean isSSL()
    {
        List<String> abaFound = new ArrayList<>();
        for (String stringOutsideBracket : stringsOutsideBrackets)
        {
            abaFound.addAll(getPalindromeStrings(stringOutsideBracket, ABA_SIZE));
        }
        List<String> babList = abaFound.stream().map(IPv7Address::reverseLetters).toList();
        for (String stringInsideBracket : stringsInsideBrackets)
        {
            for (String bab : babList)
            {
                if (stringInsideBracket.contains(bab))
                {
                    return true;
                }
            }
        }

        return false;
    }

    private static List<String> getPalindromeStrings(String stringToCheck, int stringWindow)
    {
        List<String> palindromeStrings = new ArrayList<>();
        for (int i = 0; i <= stringToCheck.length() - stringWindow; i++)
        {
            String substring = stringToCheck.substring(i, i + stringWindow);
            if (isPalindromeString(substring, stringWindow))
            {
                palindromeStrings.add(substring);
            }
        }
        return palindromeStrings;
    }

    private static boolean isPalindromeString(String stringToCheck, int stringWindow)
    {
        if (stringToCheck.length() == stringWindow)
        {
            if (stringWindow % 2 == 1)
            {
                stringWindow++;
            }
            if (stringToCheck.charAt((stringWindow / 2) - 1) != stringToCheck.charAt((stringWindow / 2) - 2))
            {
                StringBuilder sb = new StringBuilder(stringToCheck);
                return sb.reverse().toString().equals(stringToCheck);
            }
        }
        return false;
    }

    private static String reverseLetters(String stringToReverse)
    {
        if (stringToReverse.length() == ABA_SIZE)
        {
            return String.valueOf(stringToReverse.charAt(1)) +
                    stringToReverse.charAt(0) +
                    stringToReverse.charAt(1);
        }
        return stringToReverse;
    }
}
