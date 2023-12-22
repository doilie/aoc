package aoc2023.day10;

import java.util.*;

public class Path {
    public enum Direction {
        Left,
        Right,
        Up,
        Down
    }
    private static final HashMap<String, Direction> directionsFromMovingLeft = new HashMap<>();
    private static final HashMap<String, Direction> directionsFromMovingRight = new HashMap<>();
    private static final HashMap<String, Direction> directionsFromMovingUp = new HashMap<>();
    private static final HashMap<String, Direction> directionsFromMovingDown = new HashMap<>();

    static {
        directionsFromMovingLeft.put("-", Direction.Left);
        directionsFromMovingLeft.put("L", Direction.Up);
        directionsFromMovingLeft.put("F", Direction.Down);

        directionsFromMovingRight.put("-", Direction.Right);
        directionsFromMovingRight.put("J", Direction.Up);
        directionsFromMovingRight.put("7", Direction.Down);

        directionsFromMovingUp.put("|", Direction.Up);
        directionsFromMovingUp.put("7", Direction.Left);
        directionsFromMovingUp.put("F", Direction.Right);

        directionsFromMovingDown.put("|", Direction.Down);
        directionsFromMovingDown.put("J", Direction.Left);
        directionsFromMovingDown.put("L", Direction.Right);
    }

    private final List<Pipe> pipes = new ArrayList<>();
    private Direction direction;

    private Path(Pipe startPipe, Direction direction) {
        this.direction = direction;
        pipes.add(startPipe);
    }

    public static Path startPath(Pipe startPipe, Pipe adjacentPipe, Direction direction) {
        if (!getReferenceDirections(direction).containsKey(adjacentPipe.value())) {
            return null;
        }

        return new Path(startPipe, direction);
    }

    public static HashMap<String, Direction> getReferenceDirections(Direction direction) {
        return switch(direction) {
            case Left -> directionsFromMovingLeft;
            case Right -> directionsFromMovingRight;
            case Up -> directionsFromMovingUp;
            default -> directionsFromMovingDown;
        };
    }

    public void addPipe(Pipe pipe) {
        pipes.add(pipe);
        direction = getReferenceDirections(direction).get(pipe.value());
    }

    public String getNextPipeLocation() {
        return calculateNextPipeLocation(getCurrentPipe().x(), getCurrentPipe().y(), direction);
    }

    public Pipe getCurrentPipe() {
        return pipes.get(pipes.size() - 1);
    }

    public List<Pipe> getPipes() {
        return pipes;
    }

    public Direction getDirection() {
        return direction;
    }

    public static String calculateNextPipeLocation(int x, int y, Direction direction) {
        switch(direction) {
            case Left: x--; break;
            case Right: x++; break;
            case Up: y--; break;
            case Down: y++; break;
        }
        return Pipe.buildRowColKey(x, y);
    }
}
