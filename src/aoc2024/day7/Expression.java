package aoc2024.day7;

import java.util.*;
import java.util.regex.Pattern;

public class Expression
{
    private static final String ADD = "+";
    private static final String MULTIPLY = "*";
    private final List<String> contents;
    private static final Map<Integer, List<String>> operatorCombinations = new HashMap<>();

    public Expression(List<String> contents)
    {
        this.contents = contents;
    }

    public long evaluate()
    {
        long total = 0;

        Pattern p = Pattern.compile("[1-9][0-9]*");
        Stack<String> stack = new Stack<>();
        for (String s : contents)
        {
            if (p.matcher(s).matches())
            {
                if (stack.isEmpty())
                {
                    total = Long.parseLong(s);
                }
                else if (stack.peek().equals(MULTIPLY))
                {
                    total *= Long.parseLong(s);
                }
                else if (stack.peek().equals(ADD))
                {
                    total += Long.parseLong(s);
                }
            }
            else if (s.equals(MULTIPLY) || s.equals(ADD))
            {
                stack.push(s);
            }
        }

        return total;
    }

    private static final List<String> POSSIBLE_CHARACTERS = List.of(MULTIPLY, ADD);
    public static List<String> generateCombinations(int length)
    {
        if (length == 0)
        {
            return Collections.emptyList();
        }
        if (operatorCombinations.containsKey(length))
        {
            return operatorCombinations.get(length);
        }
        else {
            List<String> combinationsToConcat = generateCombinations(length - 1);
            List<String> combinations = getStrings(length - 1, combinationsToConcat);
            operatorCombinations.put(length, combinations);
            return combinations;
        }
    }

    private static List<String> getStrings(int length, List<String> combinationsToConcat)
    {
        int currentLengthCombinationCount = (int) Math.pow(POSSIBLE_CHARACTERS.size(), length);
        List<String> combinations = new ArrayList<>();
        for (String s : POSSIBLE_CHARACTERS)
        {
            for (int i = 0; i < Math.pow(POSSIBLE_CHARACTERS.size(), length); i++)
            {
                combinations.add(s);
            }
        }
        if (!combinationsToConcat.isEmpty())
        {
            for (int i = 0; i < combinations.size(); i++)
            {
                combinations.set(i, combinations.get(i) + combinationsToConcat.get(i % currentLengthCombinationCount));
            }
        }
        return combinations;
    }
}
