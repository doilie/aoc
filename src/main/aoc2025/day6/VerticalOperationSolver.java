package aoc2025.day6;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;

public class VerticalOperationSolver
{
    private final Map<Integer, Operation> operationMap = new HashMap<>();

    VerticalOperationSolver(String input)
    {
        String[] lines = input.split("\\n");
        for (String line : lines) {
            if (!line.isEmpty()) {
                if (line.trim().startsWith(Operation.ADD) || line.trim().startsWith(Operation.MULTIPLY)) {
                    addOperationLine(line);
                } else {
                    addNumbersLine(line);
                }
            }
        }
    }

    private void addNumbersLine(String line)
    {
        String[] numbersStr = line.trim().split("\\s+");
        for (int i = 0; i < numbersStr.length; i++)
        {
            if (!numbersStr[i].isEmpty())
            {
                if (!operationMap.containsKey(i))
                {
                    operationMap.put(i, new Operation());
                }
                Operation operation = operationMap.get(i);
                operation.addNumber(Integer.parseInt(numbersStr[i].trim()));
            }
        }
    }

    private void addOperationLine(String line)
    {
        String[] operationStr = line.trim().split("\\s+");
        for (int i = 0; i < operationStr.length; i++)
        {
            if (!operationStr[i].isEmpty())
            {
                if (operationMap.containsKey(i))
                {
                    Operation operation = operationMap.get(i);
                    operation.setOperation(operationStr[i].trim());
                }
                else
                {
                    throw new InvalidParameterException(operationStr[i]);
                }
            }
        }
    }

    long solve()
    {
        long result = 0;
        for (Operation operation : operationMap.values())
        {
            result += operation.getResult();
        }
        return result;
    }
}
