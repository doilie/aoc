package aoc2022.day8.treehouse;

public class TreeLocation {
    private final int column;
    private final int row;

    public TreeLocation(int column, int row) {
        this.column = column;
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }
}
