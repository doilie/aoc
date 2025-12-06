package aoc2025.day6;

import java.util.ArrayList;
import java.util.List;

public class Operation {
    static final String ADD = "+";
    static final String MULTIPLY = "*";

    private final List<Integer> numbers = new ArrayList<>();
    private String operation;

    void addNumber(int num)
    {
        numbers.add(num);
    }

    void setOperation(String operation)
    {
        this.operation = operation;
    }

    long getResult()
    {
        long result = operation.equals(ADD) ? 0 : 1;
        for (Integer number : numbers)
        {
            switch(operation)
            {
                case ADD -> result += number;
                case MULTIPLY -> result *= number;
            }
        }
        return result;
    }
}
