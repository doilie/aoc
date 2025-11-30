package aoc2015.day7;

public class BitwiseLogicGateOperation
{
    private final String id;
    private int op1 = -1;
    private int op2 = -1;
    private String op1Id;
    private String op2Id;
    private String op;
    private int result = -1;

    BitwiseLogicGateOperation(String bitwiseOperation)
    {
        String[] parts = bitwiseOperation.split(" -> ");
        if (parts.length == 2)
        {
            id = parts[1].trim();
            String[] operationParts = parts[0].split(" ");
            if (operationParts.length == 1)
            {
                try
                {
                    result = Integer.parseInt(operationParts[0]);
                }
                catch (NumberFormatException e)
                {
                    op1Id = operationParts[0];
                }
            }
            else if (operationParts.length == 2)
            {
                op = operationParts[0];
                op1Id = operationParts[1];
                op2 = -2;
            }
            else if (operationParts.length == 3) {
                try
                {
                    op1 = Integer.parseInt(operationParts[0]);
                }
                catch (NumberFormatException e)
                {
                    op1Id = operationParts[0];
                }
                op = operationParts[1];
                try
                {
                    op2 = Integer.parseInt(operationParts[2]);
                }
                catch (NumberFormatException e)
                {
                    op2Id = operationParts[2];
                }
            }
            else {
                throw new IllegalStateException("Invalid operation");
            }
        } else {
            throw new IllegalStateException("Invalid line");
        }
    }

    boolean hasResult() {
        return result != -1;
    }

    String getId() {
        return id;
    }

    String getOp1Id()
    {
        return op1Id;
    }

    String getOp2Id()
    {
        return op2Id;
    }

    void setOp1(int op1)
    {
        this.op1 = op1;
    }

    void setOp2(int op2)
    {
        this.op2 = op2;
    }

    int getResult()
    {
        return result;
    }

    void performOperation()
    {
        if (!hasResult())
        {
            if (op1 != -1 && op2 != -1)
            {
                switch (op)
                {
                    case "NOT":
                        result = ~op1;
                        break;
                    case "AND":
                        result = op1 & op2;
                        break;
                    case "OR":
                        result = op1 | op2;
                        break;
                    case "LSHIFT":
                        result = op1 << op2;
                        break;
                    case "RSHIFT":
                        result = op1 >> op2;
                        break;
                    default:
                        throw new IllegalStateException("Invalid operation");
                }
                int mask = 0xFFFF;
                result = result & mask;
            }
            else if (op == null && op1 != -1)
            {
                result = op1;
            }
        }
    }

    @Override
    public String toString() {
        String stringRes = id + ": ";
        if (hasResult())
        {
            stringRes += result;
        }
        else
        {
            stringRes += op + "," + op1Id + "(" + op1 + ")," + op2Id + "(" + op2 + ")";
        }
        return stringRes;
    }
}
