package aoc2021.day2;

public class SubmarinePosition {
    protected int horizontalPosition = 0;
    protected int depth = 0;

    public void moveForward(int distance) {
        horizontalPosition += distance;
    }

    public void moveUp(int distance) {
        depth -= distance;
    }

    public void moveDown(int distance) {
        depth += distance;
    }

    public int getHorizontalPosition() {
        return horizontalPosition;
    }

    public int getDepth() {
        return depth;
    }
}
