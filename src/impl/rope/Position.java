package impl.rope;

import java.util.ArrayList;
import java.util.List;

public class Position {
    private final int row;
    private final int column;
    private final List<RopeEnd> visitors;
    private boolean startingPosition;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
        visitors = new ArrayList<>();
    }

    public boolean didRopeEndVisit(RopeEnd ropeEnd) {
        return visitors.contains(ropeEnd);
    }

    public void visitBy(RopeEnd ropeEnd) {
        if (!didRopeEndVisit(ropeEnd)) {
            visitors.add(ropeEnd);
        }
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public String getName() {
        return row + "," + column;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass().equals(Position.class)) {
            Position position = (Position) obj;
            return position.getRow() == this.getRow() && position.getColumn() == this.getColumn();
        }
        return false;
    }

    @Override
    public String toString() {
        return getName();
    }

    public boolean isStartingPosition() {
        return startingPosition;
    }

    public void setStartingPosition(boolean startingPosition) {
        this.startingPosition = startingPosition;
    }
}
