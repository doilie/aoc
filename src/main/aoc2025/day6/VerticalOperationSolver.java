package aoc2025.day6;

import java.util.HashMap;
import java.util.Map;

public class VerticalOperationSolver
{
    private final Map<Integer, Operation> operationMap = new HashMap<>();

    VerticalOperationSolver(String input)
    {
        String[] lines = input.split("\\n");

        for (int i = lines.length - 1; i > 0; i--)
        {
            if (!lines[i].isEmpty() && lines[i].trim().startsWith(Operation.ADD) || lines[i].trim().startsWith(Operation.MULTIPLY))
            {
                setColumnLengths(lines[i]);
                addOperationLine(lines[i]);
                break;
            }
        }
        for (String line : lines)
        {
            if (!line.isEmpty() && !line.trim().startsWith(Operation.ADD) && !line.trim().startsWith(Operation.MULTIPLY))
            {
                addNumbersLine(line);
            }
        }
    }

    private void setColumnLengths(String line)
    {
        int spaceCount = 0;
        int columnCount = 0;
        for (int i = 0; i < line.length(); i++)
        {
            char currChar = line.charAt(i);
            if (currChar == Operation.ADD.charAt(0) || currChar == Operation.MULTIPLY.charAt(0) || currChar == '\n' || currChar == '\r')
            {
                if (i != 0)
                {
                    Operation operation = new Operation(spaceCount);
                    operationMap.put(columnCount, operation);
                    columnCount++;
                    spaceCount = 0;
                }
            }
            else if (currChar == ' '){
                spaceCount++;
            }
        }
        if (spaceCount > 0)
        {
            Operation operation = new Operation(spaceCount);
            operationMap.put(columnCount, operation);
        }
    }

    private void addNumbersLine(String line)
    {
        int currIdx = 0;
        for (int i = 0; i < operationMap.size(); i++)
        {
            Operation operation = operationMap.get(i);
            int columnSize = operation.getColumnSize();
            String numToAdd = line.substring(currIdx, Math.min(currIdx + columnSize, line.length()));
            operationMap.get(i).addNumber(numToAdd);
            currIdx = currIdx + columnSize + 1;
        }
    }

    private void addOperationLine(String line)
    {
        String[] operationStr = line.trim().split("\\s+");
        for (int i = 0; i < operationStr.length; i++)
        {
            if (operationMap.containsKey(i))
            {
                operationMap.get(i).setOperation(operationStr[i].trim());
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

    long solveCephalopodally()
    {
        long result = 0;
        for (Operation operation : operationMap.values())
        {
            result += operation.getCephalopodResult();
        }
        return result;
    }
}
