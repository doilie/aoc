package aoc2019.day5;

import java.util.function.BiFunction;

public class BinaryOperation implements IntcodeOperationAction
{
    private final IntcodeComputerV2 computer;
    private final BiFunction<Integer, Integer, Integer> operation;
    private final boolean isFirstInputImmediate;
    private final boolean isSecondInputImmediate;

    public BinaryOperation(IntcodeComputerV2 memory, BiFunction<Integer, Integer, Integer> operation, boolean isFirstInputImmediate, boolean isSecondInputImmediate)
    {
        this.computer = memory;
        this.operation = operation;
        this.isFirstInputImmediate = isFirstInputImmediate;
        this.isSecondInputImmediate = isSecondInputImmediate;
    }

    @Override
    public void execute()
    {
        int instructionPointer = computer.getInstructionPointerPosition();
        int position1 = isFirstInputImmediate ? instructionPointer + 1 : computer.getValueInPosition(instructionPointer + 1);
        int position2 = isSecondInputImmediate ? instructionPointer + 2 : computer.getValueInPosition(instructionPointer + 2);
        int num1 = computer.getValueInPosition(position1);
        int num2 = computer.getValueInPosition(position2);
        int outputPosition = computer.getValueInPosition(instructionPointer + 3);
        computer.setValueInPosition(outputPosition, operation.apply(num1, num2));
    }

    @Override
    public void moveInstructionPointer() {
        computer.updateInstructionPointer(computer.getInstructionPointerPosition() + 4);
    }
}
