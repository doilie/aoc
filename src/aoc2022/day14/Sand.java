package aoc2022.day14;

import java.util.Hashtable;

public class Sand {
    public static Coordinate fall(Hashtable<String, String> currentPositions) {
        Coordinate position = new Coordinate(500, 0);
        int oldX = -1;
        int oldY = -1;
        while (oldX != position.getX() || oldY != position.getY()) {
            oldX = position.getX();
            oldY = position.getY();
            position.moveDown(currentPositions);
            if (oldX == position.getX() && oldY == position.getY()) {
                position.moveDownLeft(currentPositions);
                if (oldX == position.getX() && oldY == position.getY()) {
                    position.moveDownRight(currentPositions);
                }
            }
        }

        return position;
    }

}
