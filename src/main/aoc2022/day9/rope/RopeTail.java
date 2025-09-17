package aoc2022.day9.rope;

public class RopeTail extends RopeEnd {
    private final RopeEnd head;

    public RopeTail(String name, RopeEnd head) {
        super(name);
        this.head = head;
    }

    public Position move() {
        Position headPos = head.currentPosition;
        Position tailPos = this.currentPosition;
        int newRow = tailPos.getRow();
        int newColumn = tailPos.getColumn();

        int rowDiff = headPos.getRow() - tailPos.getRow();
        int columnDiff = headPos.getColumn() - tailPos.getColumn();
        // same position, row +/-1, col +/- 1 - do nothing
        if (columnDiff == -2 || columnDiff == 2) {
            // move left
            if (columnDiff == -2) {
                newColumn--;
            }
            // move right
            else {
                newColumn++;
            }
            // also move vertically
            if (rowDiff < 0) {
                newRow--;
            }
            else if (rowDiff > 0) {
                newRow++;
            }
        }
        else if (rowDiff == -2 || rowDiff == 2) {
            // move up
            if (rowDiff == -2) {
                newRow--;
            }
            // move down
            else {
                newRow++;
            }
            // also move horizontally
            if (columnDiff < 0) {
                newColumn--;
            }
            else if (columnDiff > 0) {
                newColumn++;
            }
        }

        return new Position(newRow, newColumn);
    }
}
