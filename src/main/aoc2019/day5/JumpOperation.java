package aoc2019.day5;

import java.util.function.Predicate;

public class JumpOperation implements IntcodeOperationAction
{
    private final IntcodeComputer computer;
    private final Predicate<Integer> condition;
    private final boolean isFirstInputImmediate;
    private final boolean isSecondInputImmediate;

    public JumpOperation(IntcodeComputer computer, Predicate<Integer> condition, boolean isFirstInputImmediate, boolean isSecondInputImmediate)
    {
        this.computer = computer;
        this.condition = condition;
        this.isFirstInputImmediate = isFirstInputImmediate;
        this.isSecondInputImmediate = isSecondInputImmediate;
    }


    @Override
    public void execute()
    {
        // nothing to do
    }

    @Override
    public void moveInstructionPointer() {
        int conditionPosition = IntcodeController.getTargetPosition(computer, computer.getInstructionPointerPosition() + 1, isFirstInputImmediate);
        int conditionValue = computer.getValueInPosition(conditionPosition);
        boolean shouldJump = condition.test(conditionValue);
        if (shouldJump)
        {
            int jumpPosition = IntcodeController.getTargetPosition(computer, computer.getInstructionPointerPosition() + 2, isSecondInputImmediate);
            int jumpPositionValue = computer.getValueInPosition(jumpPosition);
            computer.updateInstructionPointer(jumpPositionValue);
        }
        else {
            computer.updateInstructionPointer(computer.getInstructionPointerPosition() + 3);
        }
    }
}
