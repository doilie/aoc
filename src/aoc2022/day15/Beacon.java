package aoc2022.day15;

public class Beacon {
    private final Position position;

    public Beacon(String input) {
        position = Position.parseLine(input);
    }

    public Position getPosition() {
        return position;
    }
}
