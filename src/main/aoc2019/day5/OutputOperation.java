package aoc2019.day5;

public class OutputOperation implements IntcodeOperationAction
{
    private final IntcodeComputer computer;
    private final boolean isImmediate;

    public OutputOperation(IntcodeComputer computer, boolean isImmediate)
    {
        this.computer = computer;
        this.isImmediate = isImmediate;
    }

    @Override
    public void execute()
    {
        int position = IntcodeController.getTargetPosition(computer, computer.getInstructionPointerPosition() + 1, isImmediate);
        int value = computer.getValueInPosition(position);
        computer.updateOutput(value);
    }

    @Override
    public void moveInstructionPointer() {
        computer.updateInstructionPointer(computer.getInstructionPointerPosition() + 2);
    }
}
