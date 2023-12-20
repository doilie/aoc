package aoc2023.day10;

public record Pipe(String value, int x, int y) {

    public String getName() {
        return buildRowColKey(x, y);
    }

    public static String buildRowColKey(int x, int y) {
        return x + "," + y;
    }
}
