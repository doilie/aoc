package aoc2019.day5;

public class InputOperation implements IntcodeOperationAction
{
    private final IntcodeComputer computer;

    public InputOperation(IntcodeComputer computer)
    {
        this.computer = computer;
    }

    @Override
    public void execute()
    {
        int destPosition = computer.getValueInPosition(computer.getInstructionPointerPosition() + 1);
        computer.setValueInPosition(destPosition, computer.readInput());
    }

    @Override
    public void moveInstructionPointer() {
        computer.updateInstructionPointer(computer.getInstructionPointerPosition() + 2);
    }
}
