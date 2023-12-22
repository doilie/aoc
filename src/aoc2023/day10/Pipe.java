package aoc2023.day10;

import java.util.Objects;

public final class Pipe {
    private final String value;
    private final int x;
    private final int y;

    public Pipe(String value, int x, int y) {
        this.value = value;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return buildRowColKey(x, y);
    }

    public static String buildRowColKey(int x, int y) {
        return x + "," + y;
    }

    public String value() {
        return value;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Pipe) obj;
        return Objects.equals(this.value, that.value) &&
                this.x == that.x &&
                this.y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, x, y);
    }

    @Override
    public String toString() {
        return "Pipe[" +
                "value=" + value + ", " +
                "x=" + x + ", " +
                "y=" + y + ']';
    }

}
