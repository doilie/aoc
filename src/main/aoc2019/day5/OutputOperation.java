package aoc2019.day5;

public class OutputOperation implements IntcodeOperationAction
{
    private final IntcodeComputerV2 computer;
    private final boolean isImmediate;

    public OutputOperation(IntcodeComputerV2 computer, boolean isImmediate)
    {
        this.computer = computer;
        this.isImmediate = isImmediate;
    }

    @Override
    public void execute()
    {
        int position = isImmediate ? computer.getInstructionPointerPosition() + 1 : computer.getValueInPosition(computer.getInstructionPointerPosition() + 1);
        int value = computer.getValueInPosition(position);
        computer.updateOutput(value);
    }

    @Override
    public void moveInstructionPointer() {
        computer.updateInstructionPointer(computer.getInstructionPointerPosition() + 2);
    }
}
