package aoc2016.day7;

import java.util.ArrayList;
import java.util.List;

public class IPv7Address
{
    private static final int ABBA_SIZE = 4;

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
            if (hasAbbaString(stringInsideBracket))
            {
                return false;
            }
        }
        for (String stringOutsideBracket : stringsOutsideBrackets)
        {
            if (hasAbbaString(stringOutsideBracket))
            {
                return true;
            }
        }
        return false;
    }

    private static boolean hasAbbaString(String stringToCheck)
    {
        for (int i = 0; i <= stringToCheck.length() - ABBA_SIZE; i++)
        {
            if (isAbbaString(stringToCheck.substring(i, i + ABBA_SIZE)))
            {
                return true;
            }
        }
        return false;
    }

    private static boolean isAbbaString(String stringToCheck)
    {
        if (stringToCheck.length() == ABBA_SIZE)
        {
            if (stringToCheck.charAt((ABBA_SIZE / 2) - 1) != stringToCheck.charAt((ABBA_SIZE / 2) - 2))
            {
                StringBuilder sb = new StringBuilder(stringToCheck);
                return sb.reverse().toString().equals(stringToCheck);
            }
        }
        return false;
    }
}
