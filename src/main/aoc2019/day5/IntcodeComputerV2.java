package aoc2019.day5;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class IntcodeComputerV2 {
    private final List<Integer> memory = new ArrayList<>();
    private final Queue<Integer> inputQueue = new LinkedList<>();
    private int output = 0;
    private int instructionPointer = 0;

    public IntcodeComputerV2(List<Integer> memory) {
        this.memory.addAll(memory);
    }

    public void updateInstructionPointer(int newInstructionPointer) {
        this.instructionPointer = newInstructionPointer;
    }

    public int getValueInPosition(int position) {
        return memory.get(position);
    }

    public void setValueInPosition(final int position, int value) {
        memory.set(position, value);
    }

    public int getInstructionPointerPosition() {
        return instructionPointer;
    }

    public int getInstructionPointerValue() {
        return getValueInPosition(instructionPointer);
    }

    public void updateOutput(int output)
    {
        this.output = output;
    }

    public void addInput(int input)
    {
        inputQueue.add(input);
    }

    public int readInput()
    {
        if (inputQueue.isEmpty())
        {
            throw new IllegalStateException("Input expected but none available.");
        }
        return inputQueue.poll();
    }

    public int readOutput()
    {
        return output;
    }
}
