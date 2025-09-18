package aoc2019.day5;

public class IntcodeController
{
    private final IntcodeComputer computer;

    public IntcodeController(IntcodeComputer computer)
    {
        this.computer = computer;
    }

    public void execute() {
        while (true)
        {
            IntcodeOperationAction operationAction = getIntcodeOperationAction();
            if (operationAction == null)
            {
                break;
            }
            operationAction.execute();
            operationAction.moveInstructionPointer();
        }
    }

    private IntcodeOperationAction getIntcodeOperationAction() {
        int opCode = getOpCode(computer.getInstructionPointerValue());
        IntcodeOperation operation = IntcodeOperation.fromOpCode(opCode);
        boolean isFirstInputImmediate = isFirstInputImmediate(computer.getInstructionPointerValue());boolean isSecondInputImmediate = isSecondInputImmediate(computer.getInstructionPointerValue());

        return switch (operation)
        {
            case ADDITION -> new BinaryOperation(computer, Integer::sum, isFirstInputImmediate, isSecondInputImmediate);
            case MULTIPLICATION -> new BinaryOperation(computer, (a, b) -> a * b, isFirstInputImmediate, isSecondInputImmediate);
            case INPUT -> new InputOperation(computer);
            case OUTPUT -> new OutputOperation(computer, isFirstInputImmediate);
            case JUMP_IF_TRUE -> new JumpOperation(computer, val -> val != 0, isFirstInputImmediate, isSecondInputImmediate);
            case JUMP_IF_FALSE -> new JumpOperation(computer, val -> val == 0, isFirstInputImmediate, isSecondInputImmediate);
            case LESS_THAN -> new BinaryOperation(computer, (a, b) -> (a < b) ? 1 : 0, isFirstInputImmediate, isSecondInputImmediate);
            case EQUALS -> new BinaryOperation(computer, (a, b) -> (a.equals(b)) ? 1 : 0, isFirstInputImmediate, isSecondInputImmediate);
            case HALT -> null;
        };
    }

    private static int getOpCode(int instruction)
    {
        return instruction % 100;
    }

    private static boolean isFirstInputImmediate(int instruction)
    {
        return ((instruction / 100) % 10) == 1;
    }

    private static boolean isSecondInputImmediate(int instruction)
    {
        return ((instruction / 1000) % 10) == 1;
    }

    public static int getTargetPosition(IntcodeComputer computer, int origPosition, boolean isImmediate)
    {
        return isImmediate ? origPosition : computer.getValueInPosition(origPosition);
    }
}
