package aoc2022.day9.rope;

public class RopeHead extends RopeEnd {
    public RopeHead() {
        super("H");
    }

    public Position move(MoveInstruction.Direction direction) {
        int newRow = currentPosition.getRow();
        int newColumn = currentPosition.getColumn();
        switch(direction) {
            case Up: newRow--; break;
            case Down: newRow++; break;
            case Left: newColumn--; break;
            case Right: newColumn++; break;
        }
        return new Position(newRow, newColumn);
    }
}
