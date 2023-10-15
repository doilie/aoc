package aoc2022.day9.rope;

public abstract class RopeEnd {
    private final String name;
    protected Position currentPosition;

    public RopeEnd(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCurrentPosition(Position position) {
        this.currentPosition = position;
        this.currentPosition.visitBy(this);
    }
}
