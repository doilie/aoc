package aoc2022.day15;

public class Beacon {
    private final Position position;

    public Beacon(String input) {
        position = Position.parseLine(input);
    }

    public Beacon(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public long getTuningFrequency() {
        return (position.getX() * 4000000L) + position.getY();
    }
}
