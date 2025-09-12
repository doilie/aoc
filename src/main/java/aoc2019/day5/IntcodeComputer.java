package aoc2019.day5;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public class IntcodeComputer {
    private int[] memory;
    private int instructionPointer;
    private int lastOutput;
    private boolean halted;
    private Queue<Integer> inputQueue;

    public IntcodeComputer(int[] program) {
        this.memory = Arrays.copyOf(program, program.length);
        this.instructionPointer = 0;
        this.lastOutput = 0;
        this.halted = false;
        this.inputQueue = new LinkedList<>();
    }

    public void addInput(int input) {
        this.inputQueue.add(input);
    }

    public int getLastOutput() {
        return this.lastOutput;
    }

    public boolean isHalted() {
        return this.halted;
    }

    public void runProgram() {
        while (!halted) {
            int instruction = memory[instructionPointer];
            int opcode = instruction % 100;
            int mode1 = (instruction / 100) % 10;
            int mode2 = (instruction / 1000) % 10;
            // int mode3 = (instruction / 10000) % 10; // Not used in current opcodes

            switch (opcode) {
                case 1: // Addition
                    executeBinaryOperation((a, b) -> a + b, mode1, mode2);
                    break;
                case 2: // Multiplication
                    executeBinaryOperation((a, b) -> a * b, mode1, mode2);
                    break;
                case 3: // Input
                    if (inputQueue.isEmpty()) {
                        throw new IllegalStateException("Input expected but none available.");
                    }
                    int inputVal = inputQueue.poll();
                    int destIdx = memory[instructionPointer + 1];
                    memory[destIdx] = inputVal;
                    instructionPointer += 2;
                    break;
                case 4: // Output
                    int outputVal = getParameterValue(memory[instructionPointer + 1], mode1);
                    lastOutput = outputVal;
                    instructionPointer += 2;
                    break;
                // case 5: // Jump-if-true
                //     executeJumpOperation(val -> val != 0, mode1, mode2);
                //     break;
                // case 6: // Jump-if-false
                //     executeJumpOperation(val -> val == 0, mode1, mode2);
                //     break;
                // case 7: // Less than
                //     executeBinaryOperation((a, b) -> (a < b) ? 1 : 0, mode1, mode2);
                //     break;
                // case 8: // Equals
                //     executeBinaryOperation((a, b) -> (a == b) ? 1 : 0, mode1, mode2);
                //     break;
                case 99: // Halt
                    halted = true;
                    break;
                default:
                    throw new IllegalStateException("Unknown opcode: " + opcode);
            }
        }
    }
    
    private int getParameterValue(int parameter, int mode) {
        return mode == 0 ? memory[parameter] : parameter;
    }

    private void executeBinaryOperation(BiFunction<Integer, Integer, Integer> operation, int mode1, int mode2) {
        int param1 = getParameterValue(memory[instructionPointer + 1], mode1);
        int param2 = getParameterValue(memory[instructionPointer + 2], mode2);
        int destIdx = memory[instructionPointer + 3];
        memory[destIdx] = operation.apply(param1, param2);
        instructionPointer += 4;
    }

    // private void executeJumpOperation(Predicate<Integer> condition, int mode1, int mode2) {
    //     int param1 = getParameterValue(memory[instructionPointer + 1], mode1);
    //     int param2 = getParameterValue(memory[instructionPointer + 2], mode2);
    //     if (condition.test(param1)) {
    //         instructionPointer = param2;
    //     } else {
    //         instructionPointer += 3;
    //     }
    // }
}
