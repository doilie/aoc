package aoc2022.day8.treehouse;

public class Tree {
    private final int size;
    private final TreeLocation location;
    private final TreeVisibility visibility;

    public Tree(int size, int column, int row) {
        this.size = size;
        this.location = new TreeLocation(column, row);
        this.visibility = new TreeVisibility();
    }

    public int getSize() {
        return size;
    }

    public TreeLocation getLocation() {
        return location;
    }

    public TreeVisibility getVisibility() {
        return visibility;
    }
}
