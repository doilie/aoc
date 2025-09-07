package aoc2024.day7;

import java.util.*;
import java.util.regex.Pattern;

public class Expression
{
    public static final String ADD = "+";
    public static final String MULTIPLY = "*";
    public static final String CONCATENATE = "||";
    private static final Map<String, List<List<String>>> operatorCombinations = new HashMap<>();
    private static String buildKey(int length, List<String> operatorCombinations)
    {
        StringBuilder key = new StringBuilder();
        for (String s : operatorCombinations)
        {
            key.append(s);
        }
        key.append('-').append(length);
        return key.toString();
    }

    private final List<String> contents;

    public Expression(List<String> contents)
    {
        this.contents = new ArrayList<>(contents);
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
                else if (stack.peek().equals(CONCATENATE))
                {
                    String totalString = String.valueOf(total);
                    total = Long.parseLong(totalString + s);
                }
            }
            else if (s.equals(MULTIPLY) || s.equals(ADD) || s.equals(CONCATENATE))
            {
                stack.push(s);
            }
        }

        return total;
    }

    public static List<List<String>> generateCombinations(int length, List<String> possibleOperators)
    {
        String key = buildKey(length, possibleOperators);
        if (length == 0)
        {
            return Collections.emptyList();
        }
        if (operatorCombinations.containsKey(key))
        {
            return operatorCombinations.get(key);
        }
        else {
            List<List<String>> combinationsToConcat = generateCombinations(length - 1, possibleOperators);
            List<List<String>> combinations = getStrings(length - 1, combinationsToConcat, possibleOperators);
            operatorCombinations.put(key, combinations);
            return combinations;
        }
    }

    private static List<List<String>> getStrings(int length, List<List<String>> combinationsToConcat, List<String> possibleOperators)
    {
        int currentLengthCombinationCount = (int) Math.pow(possibleOperators.size(), length);
        List<List<String>> combinations = new ArrayList<>();
        for (String s : possibleOperators)
        {
            for (int i = 0; i < Math.pow(possibleOperators.size(), length); i++)
            {
                List<String> newCombinations = new ArrayList<>();
                newCombinations.add(s);
                combinations.add(newCombinations);
            }
        }
        if (!combinationsToConcat.isEmpty())
        {
            for (int i = 0; i < combinations.size(); i++)
            {
                combinations.get(i).addAll(combinationsToConcat.get(i % currentLengthCombinationCount));
            }
        }
        return combinations;
    }
}
