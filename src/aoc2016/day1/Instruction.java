package aoc2016.day1;

public class Instruction {
    public enum TurnDirection {
        Right,
        Left
    }

    private final TurnDirection turnDirection;
    private final int steps;

    public Instruction(TurnDirection turnDirection, int steps) {
        this.turnDirection = turnDirection;
        this.steps = steps;
    }

    public TurnDirection getTurnDirection() {
        return turnDirection;
    }

    public int getSteps() {
        return steps;
    }
}
