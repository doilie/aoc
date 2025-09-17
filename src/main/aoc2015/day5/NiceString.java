package aoc2015.day5;

import java.util.*;

public class NiceString
{
    private static final List<Character> VOWELS = List.of('a', 'e', 'i', 'o', 'u');
    private final String str;
    private final Map<Character, List<Integer>> letterIndices =  new HashMap<>();

    public NiceString(String str)
    {
        this.str = str;
        for (int i = 0; i < str.length(); i++)
        {
            char currentChar = str.charAt(i);
            if (!letterIndices.containsKey(currentChar))
            {
                letterIndices.put(currentChar, new ArrayList<>());
            }
            letterIndices.get(currentChar).add(i);
        }
    }

    public boolean isNiceString()
    {
        return hasAtLeastThreeVowels() && hasDoubleLetters() && hasNaughtyPairs();
    }

    public boolean isNiceString_v2()
    {
        return hasTwoLettersThatAppearTwiceWithoutOverlapping() && hasRepeatingLetterWithOneLetterBetween();
    }

    private boolean hasAtLeastThreeVowels()
    {
        return letterIndices.entrySet().stream().filter(entry -> VOWELS.contains(entry.getKey())).map(entry -> entry.getValue().size()).mapToInt(Integer::intValue).sum() >= 3;
    }

    private boolean hasDoubleLetters()
    {
        Set<Character> chars = letterIndices.keySet();
        for (Character c : chars)
        {
            List<Integer> indices = letterIndices.get(c);
            for (int i = 0; i < indices.size() - 1; i++)
            {
                if (indices.get(i + 1) == indices.get(i) + 1)
                {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasNaughtyPairs()
    {
        return !str.contains("ab") && !str.contains("cd") && !str.contains("pq") && !str.contains("xy");
    }

    private boolean hasTwoLettersThatAppearTwiceWithoutOverlapping()
    {
        for (int i = 0; i < str.length() - 2; i++)
        {
            String pair = str.substring(i, i + 2);
            String beforePair = str.substring(0, i);
            String afterPair = str.substring(i + 2);
            if (beforePair.contains(pair) || afterPair.contains(pair))
            {
                return true;
            }
        }
        return false;
    }

    private boolean hasRepeatingLetterWithOneLetterBetween()
    {
        Set<Character> chars = letterIndices.keySet();
        for (Character c : chars)
        {
            List<Integer> indices = letterIndices.get(c);
            for (int i = 0; i < indices.size() - 1; i++)
            {
                if (indices.get(i + 1) == indices.get(i) + 2 || (i + 2 < indices.size() && indices.get(i + 1) == indices.get(i) + 1 && indices.get(i + 2) == indices.get(i) + 2))
                {
                    return true;
                }
            }
        }
        return false;
    }
}
