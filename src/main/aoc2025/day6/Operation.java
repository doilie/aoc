package aoc2025.day6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Operation {
    static final String ADD = "+";
    static final String MULTIPLY = "*";

    private final List<String> numbers = new ArrayList<>();
    private String operation;
    private final int columnSize;

    Operation(int columnSize)
    {
        this.columnSize = columnSize;
    }

    int getColumnSize()
    {
        return columnSize;
    }

    void addNumber(String num)
    {
        numbers.add(num);
    }

    void setOperation(String operation)
    {
        this.operation = operation;
    }

    long getResult()
    {
        return getOperationResult(numbers, operation);
    }

    List<String> getCephalopodNumbers()
    {
        int columnSize = numbers.getFirst().length();

        Map<Integer, List<String>> prepMap = new HashMap<>();
        for (String num : numbers)
        {
            for (int i = 0; i < columnSize; i++)
            {
                String charAtCol = num.substring(i, i + 1);
                int prepMapIdx = columnSize - (i + 1);
                if (!prepMap.containsKey(prepMapIdx))
                {
                    prepMap.put(prepMapIdx, new ArrayList<>());
                }
                prepMap.get(prepMapIdx).add(charAtCol);
            }
        }

        List<String> cephalopodNumbers = new ArrayList<>();
        for (int i = 0; i < prepMap.size(); i++)
        {
            cephalopodNumbers.add(String.join("", prepMap.get(i)));
        }

        return cephalopodNumbers;
    }

    long getCephalopodResult()
    {
        return getOperationResult(getCephalopodNumbers(), operation);
    }

    long getOperationResult(List<String> numbers, String operation)
    {
        long result = operation.equals(ADD) ? 0 : 1;
        for (String number : numbers)
        {
            if (!number.trim().isEmpty())
            {
                switch (operation) {
                    case ADD -> result += Long.parseLong(number.trim());
                    case MULTIPLY -> result *= Long.parseLong(number.trim());
                }
            }
        }
        return result;
    }
}
