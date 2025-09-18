package aoc2019.day5;

public enum IntcodeOperation
{
    HALT(99),
    ADDITION(1),
    MULTIPLICATION(2),
    INPUT(3),
    OUTPUT(4),
    JUMP_IF_TRUE(5),
    JUMP_IF_FALSE(6),
    LESS_THAN(7),
    EQUALS(8);

    final int opCode;

    IntcodeOperation(int opCode)
    {
        this.opCode = opCode;
    }

    public static IntcodeOperation fromOpCode(int opCode) {
        for (IntcodeOperation operation : values()) {
            if (operation.opCode == opCode) {
                return operation;
            }
        }
        throw new IllegalArgumentException("Unknown opcode: " + opCode);
    }
}
