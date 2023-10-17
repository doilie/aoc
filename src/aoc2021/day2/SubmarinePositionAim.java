package aoc2021.day2;

public class SubmarinePositionAim extends SubmarinePosition {
    private int aim = 0;

    @Override
    public void moveDown(int distance) {
        aim += distance;
    }

    @Override
    public void moveUp(int distance) {
        aim -= distance;
    }

    @Override
    public void moveForward(int distance) {
        horizontalPosition += distance;
        depth += (aim * distance);
    }
}
