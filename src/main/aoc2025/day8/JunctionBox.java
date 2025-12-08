package aoc2025.day8;

public class JunctionBox {
    private final int x;
    private final int y;
    private final int z;

    JunctionBox(int x, int y, int z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    double calculateDistance(JunctionBox junctionBox)
    {
        return Math.sqrt(Math.pow(junctionBox.x - this.x, 2) + Math.pow(junctionBox.y - this.y, 2) + Math.pow(junctionBox.z - this.z, 2));
    }

    @Override
    public String toString() {
        return x + "," + y + "," + z;
    }
}
