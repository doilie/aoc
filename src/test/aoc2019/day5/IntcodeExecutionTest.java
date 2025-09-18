package aoc2019.day5;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntcodeExecutionTest
{
    @Test
    void testEqual_Position()
    {
        List<Integer> input = List.of(3,9,8,9,10,9,4,9,99,-1,8);
        IntcodeComputer computer = new IntcodeComputer(input);
        computer.addInput(8);
        IntcodeController intcodeController = new IntcodeController(computer);
        intcodeController.execute();
        assertEquals(1, computer.readOutput());

        computer = new IntcodeComputer(input);
        computer.addInput(9);
        intcodeController = new IntcodeController(computer);
        intcodeController.execute();
        assertEquals(0, computer.readOutput());
    }

    @Test
    void testLessThan_Position()
    {
        List<Integer> input = List.of(3,9,7,9,10,9,4,9,99,-1,8);
        IntcodeComputer computer = new IntcodeComputer(input);
        computer.addInput(9);
        IntcodeController intcodeController = new IntcodeController(computer);
        intcodeController.execute();
        assertEquals(0, computer.readOutput());

        computer = new IntcodeComputer(input);
        computer.addInput(7);
        intcodeController = new IntcodeController(computer);
        intcodeController.execute();
        assertEquals(1, computer.readOutput());
    }

    @Test
    void testEqual_Immediate()
    {
        List<Integer> input = List.of(3,3,1108,-1,8,3,4,3,99);
        IntcodeComputer computer = new IntcodeComputer(input);
        computer.addInput(8);
        IntcodeController intcodeController = new IntcodeController(computer);
        intcodeController.execute();
        assertEquals(1, computer.readOutput());

        computer = new IntcodeComputer(input);
        computer.addInput(9);
        intcodeController = new IntcodeController(computer);
        intcodeController.execute();
        assertEquals(0, computer.readOutput());
    }

    @Test
    void testLessThan_Immediate()
    {
        List<Integer> input = List.of(3,3,1107,-1,8,3,4,3,99);
        IntcodeComputer computer = new IntcodeComputer(input);
        computer.addInput(9);
        IntcodeController intcodeController = new IntcodeController(computer);
        intcodeController.execute();
        assertEquals(0, computer.readOutput());

        computer = new IntcodeComputer(input);
        computer.addInput(7);
        intcodeController = new IntcodeController(computer);
        intcodeController.execute();
        assertEquals(1, computer.readOutput());
    }

    @Test
    void testJump_Position()
    {
        List<Integer> input = List.of(3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9);
        IntcodeComputer computer = new IntcodeComputer(input);
        computer.addInput(0);
        IntcodeController intcodeController = new IntcodeController(computer);
        intcodeController.execute();
        assertEquals(0, computer.readOutput());

        computer = new IntcodeComputer(input);
        computer.addInput(7);
        intcodeController = new IntcodeController(computer);
        intcodeController.execute();
        assertEquals(1, computer.readOutput());
    }

    @Test
    void testJump_Immediate()
    {
        List<Integer> input = List.of(3,3,1105,-1,9,1101,0,0,12,4,12,99,1);
        IntcodeComputer computer = new IntcodeComputer(input);
        computer.addInput(0);
        IntcodeController intcodeController = new IntcodeController(computer);
        intcodeController.execute();
        assertEquals(0, computer.readOutput());

        computer = new IntcodeComputer(input);
        computer.addInput(7);
        intcodeController = new IntcodeController(computer);
        intcodeController.execute();
        assertEquals(1, computer.readOutput());
    }

    @Test
    void testLargerInput()
    {
        List<Integer> input = List.of(3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,
                1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,
                999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99);
        IntcodeComputer computer = new IntcodeComputer(input);
        computer.addInput(7);
        IntcodeController intcodeController = new IntcodeController(computer);
        intcodeController.execute();
        assertEquals(999, computer.readOutput());

        computer = new IntcodeComputer(input);
        computer.addInput(9);
        intcodeController = new IntcodeController(computer);
        intcodeController.execute();
        assertEquals(1001, computer.readOutput());
    }
}
