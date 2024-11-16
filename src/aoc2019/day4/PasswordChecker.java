package aoc2019.day4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PasswordChecker
{
    public boolean isValid(String password)
    {
        return hasSixDigits(password) && isSorted(password) && hasAdjacentDigits(password);
    }

    public boolean isValid_v2(String password)
    {
        return hasSixDigits(password) && isSorted(password) && hasTwoAdjacentDigits(password);
    }

    private boolean hasSixDigits(String password)
    {
        return password.length() == 6;
    }

    private boolean isSorted(String password)
    {
        List<String> passwordLetters = new ArrayList<>(Arrays.stream(password.split("")).toList());
        Collections.sort(passwordLetters);
        return String.join("", passwordLetters).equals(password);
    }

    private boolean hasAdjacentDigits(String password)
    {
        char c = password.charAt(0);
        for (int i = 1; i < password.length(); i++)
        {
            if (password.charAt(i) == c)
            {
                return true;
            }
            c = password.charAt(i);
        }
        return false;
    }

    private boolean hasTwoAdjacentDigits(String password)
    {
        List<String> adjacentDigitStrings = new ArrayList<>();
        char c = password.charAt(0);
        StringBuilder sb = new StringBuilder();
        sb.append(c);
        for (int i = 1; i < password.length(); i++)
        {
            if (password.charAt(i) == c)
            {
                sb.append(c);
                if (i == password.length() - 1)
                {
                    adjacentDigitStrings.add(sb.toString());
                }
            }
            else
            {
                adjacentDigitStrings.add(sb.toString());
                sb.setLength(0);
                sb.append(password.charAt(i));
            }
            c = password.charAt(i);
        }
        return adjacentDigitStrings.stream().anyMatch(s -> s.length() == 2);
    }
}
