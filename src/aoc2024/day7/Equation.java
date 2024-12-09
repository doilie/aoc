package aoc2024.day7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Equation
{
    private final List<String> numbers = new ArrayList<>();
    private final List<Expression> expressions = new ArrayList<>();
    private final List<String> possibleOperators = new ArrayList<>();
    private long recordedResult;

    public Equation(String input, List<String> possibleOperators)
    {
        this.possibleOperators.addAll(possibleOperators);
        String[] tokens = input.split(": ");
        if (tokens.length == 2)
        {
            recordedResult = Long.parseLong(tokens[0]);
            extractExpressions(tokens[1]);
        }
    }

    private void extractExpressions(String numberInput)
    {
        numbers.addAll(Arrays.stream(numberInput.split(" ")).toList());
        List<List<String>> operatorCombinations = Expression.generateCombinations(numbers.size() - 1, possibleOperators);
        for (List<String> operator : operatorCombinations)
        {
            List<String> contents = new ArrayList<>();
            for (int i = 0; i < numbers.size(); i++)
            {
                contents.add(numbers.get(i));
                if (i < numbers.size() - 1)
                {
                    contents.add(operator.get(i));
                }
            }
            expressions.add(new Expression(contents));
        }
    }

    public long getRecordedResult()
    {
        return recordedResult;
    }

    public List<Expression> getExpressions()
    {
        return expressions;
    }

    public boolean isValidExpression(Expression e)
    {
        return e.evaluate() == recordedResult;
    }

    public boolean hasValidResult()
    {
        return expressions.stream().anyMatch(this::isValidExpression);
    }
}
