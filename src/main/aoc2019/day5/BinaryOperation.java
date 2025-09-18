package aoc2019.day5;

import java.util.function.BiFunction;

public class BinaryOperation implements IntcodeOperationAction
{
    private final IntcodeComputer computer;
    private final BiFunction<Integer, Integer, Integer> operation;
    private final boolean isFirstInputImmediate;
    private final boolean isSecondInputImmediate;

    public BinaryOperation(IntcodeComputer memory, BiFunction<Integer, Integer, Integer> operation, boolean isFirstInputImmediate, boolean isSecondInputImmediate)
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
        int position1 = IntcodeController.getTargetPosition(computer, instructionPointer + 1, isFirstInputImmediate);
        int position2 = IntcodeController.getTargetPosition(computer, instructionPointer + 2, isSecondInputImmediate);
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
