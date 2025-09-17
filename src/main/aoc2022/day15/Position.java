package aoc2022.day15;

public class Position {
    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Position parseLine(String input) {
        String[] parts = input.split(" ");
        int x = 0;
        int y = 0;
        for (String part : parts) {
            if (part.startsWith("x")) {
                String[] xParts = part.split("=");
                if (xParts.length == 2) {
                    x = Integer.parseInt(xParts[1].substring(0, xParts[1].length() - 1));
                }
            }
            else if (part.startsWith("y")) {
                String[] yParts = part.split("=");
                if (yParts.length == 2) {
                    y = Integer.parseInt(yParts[1]);
                }
            }
        }
        return new Position(x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
