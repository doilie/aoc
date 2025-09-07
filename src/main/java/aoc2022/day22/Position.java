package aoc2022.day22;

public class Position {
    int x;
    int y;
    FacingDirection direction;

    Position(int x, int y, FacingDirection direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    @Override
    public String toString() {
        return x + "," + y + "," + direction;
    }
}
