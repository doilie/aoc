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
}
