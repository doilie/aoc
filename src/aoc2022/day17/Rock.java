package aoc2022.day17;

import java.util.*;

public class Rock {
    public enum RockType {
        Dash,
        Cross,
        InvertedL,
        Stick,
        Square
    }

    private static int rockCounter = 0;
    private static final RockType[] rockTypeOrder = {RockType.Dash, RockType.Cross, RockType.InvertedL, RockType.Stick, RockType.Square};

    private static Rock buildRock(RockType rockType) {
        Set<Coordinate> coordinates = new HashSet<>();
        switch(rockType) {
            case Dash:
                coordinates.addAll(List.of(new Coordinate[]{
                    new Coordinate(0, 0),
                    new Coordinate(1, 0),
                    new Coordinate(2, 0),
                    new Coordinate(3, 0)
                }));
                break;
            case Cross:
                coordinates.addAll(List.of(new Coordinate[]{
                        new Coordinate(1, 2),
                        new Coordinate(0, 1),
                        new Coordinate(1, 1),
                        new Coordinate(2, 1),
                        new Coordinate(1, 0)
                }));
                break;
            case InvertedL:
                coordinates.addAll(List.of(new Coordinate[]{
                        new Coordinate(2, 2),
                        new Coordinate(2, 1),
                        new Coordinate(0, 0),
                        new Coordinate(1, 0),
                        new Coordinate(2, 0)
                }));
                break;
            case Stick:
                coordinates.addAll(List.of(new Coordinate[]{
                        new Coordinate(0, 3),
                        new Coordinate(0, 2),
                        new Coordinate(0, 1),
                        new Coordinate(0, 0)
                }));
                break;
            case Square:
                coordinates.addAll(List.of(new Coordinate[]{
                        new Coordinate(0, 1),
                        new Coordinate(1, 1),
                        new Coordinate(0, 0),
                        new Coordinate(1, 0)
                }));
                break;
        }
        return new Rock(coordinates);
    }

    public static Rock getNextRock(int startingX, int startingY) {
        Rock nextRock = buildRock(rockTypeOrder[rockCounter++ % rockTypeOrder.length]);
        for (Coordinate c : nextRock.coordinates) {
            c.setX(c.getX() + startingX);
            c.setY(c.getY() + startingY);
        }
        return nextRock;
    }

    public static int getRockCounter() {
        return rockCounter;
    }

    public static RockType[] getRockTypeOrder() {
        return rockTypeOrder;
    }

    private final Set<Coordinate> coordinates;

    public Rock(Set<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }

    public Set<Coordinate> getCoordinates() {
        return coordinates;
    }

    public void moveLeft(Set<String> topCoordinates) {
        List<String> leftmostPoints = getLeftmostPoints();
        boolean canGoLeft = true;
        for (String leftmostPointName : leftmostPoints) {
            Coordinate leftmostPoint = Coordinate.parseName(leftmostPointName);
            if (leftmostPoint != null) {
                int newX = leftmostPoint.getX() - 1;
                String newLeftmostPointName = Coordinate.buildName(newX, leftmostPoint.getY());
                if (newX < 0 || topCoordinates.contains(newLeftmostPointName)) {
                    canGoLeft = false;
                    break;
                }
            }
        }
        if (canGoLeft) {
            coordinates.forEach(c -> c.setX(c.getX() - 1));
        }
    }

    public void moveRight(int maxWidth, Set<String> topCoordinates) {
        List<String> rightmostPoints = getRightmostPoints();
        boolean canGoRight = true;
        for (String rightmostPointName : rightmostPoints) {
            Coordinate rightmostPoint = Coordinate.parseName(rightmostPointName);
            if (rightmostPoint != null) {
                int newX = rightmostPoint.getX() + 1;
                String newRightmostPointName = Coordinate.buildName(newX, rightmostPoint.getY());
                if (newX == maxWidth || topCoordinates.contains(newRightmostPointName)) {
                    canGoRight = false;
                    break;
                }
            }
        }
        if (canGoRight) {
            coordinates.forEach(c -> c.setX(c.getX() + 1));
        }
    }

    public boolean moveDown(Set<String> topCoordinates) {
        boolean settled = false;
        List<String> lowestPoints = getLowestPoints();
        boolean canGoDown = true;
        for (String lowestPointName : lowestPoints) {
            Coordinate lowestPoint = Coordinate.parseName(lowestPointName);
            if (lowestPoint != null) {
                int newY = lowestPoint.getY() - 1;
                String newLowestPointName = Coordinate.buildName(lowestPoint.getX(), newY);
                if (newY < 0 || topCoordinates.contains(newLowestPointName)) {
                    canGoDown = false;
                    break;
                }
            }
        }
        if (canGoDown) {
            coordinates.forEach(c -> c.setY(c.getY() - 1));
        }
        else {
            settled = true;
        }
        return settled;
    }

    private List<String> getLowestPoints() {
        Hashtable<Integer, Integer> lowestPointPerX = new Hashtable<>();
        List<String> lowestPoints = new ArrayList<>();
        for (Coordinate c : coordinates) {
            lowestPointPerX.merge(c.getX(), c.getY(), (a, b) -> Math.min(b, a));
        }
        for (int x : lowestPointPerX.keySet()) {
            int y = lowestPointPerX.get(x);
            lowestPoints.add(Coordinate.buildName(x, y));
        }
        return lowestPoints;
    }

    private List<String> getLeftmostPoints() {
        Hashtable<Integer, Integer> leftmostPointPerY = new Hashtable<>();
        List<String> leftmostPoints = new ArrayList<>();
        for (Coordinate c : coordinates) {
            leftmostPointPerY.merge(c.getY(), c.getX(), (a, b) -> Math.min(b, a));
        }
        for (int y : leftmostPointPerY.keySet()) {
            int x = leftmostPointPerY.get(y);
            leftmostPoints.add(Coordinate.buildName(x, y));
        }
        return leftmostPoints;
    }

    private List<String> getRightmostPoints() {
        Hashtable<Integer, Integer> rightmostPointPerY = new Hashtable<>();
        List<String> rightmostPoints = new ArrayList<>();
        for (Coordinate c : coordinates) {
            rightmostPointPerY.merge(c.getY(), c.getX(), (a, b) -> Math.max(b, a));
        }
        for (int y : rightmostPointPerY.keySet()) {
            int x = rightmostPointPerY.get(y);
            rightmostPoints.add(Coordinate.buildName(x, y));
        }
        return rightmostPoints;
    }
}
