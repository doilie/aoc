package aoc2019.day5;

public class InputOperation implements IntcodeOperationAction
{
    private final IntcodeComputerV2 computer;

    public InputOperation(IntcodeComputerV2 computer)
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
