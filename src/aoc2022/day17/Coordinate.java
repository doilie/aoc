package aoc2022.day17;

public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public static String buildName(int x, int y) {
        return x + "," + y;
    }

    public static Coordinate parseName(String name) {
        String[] xy = name.split(",");
        if (xy.length == 2) {
            return new Coordinate(Integer.parseInt(xy[0]), Integer.parseInt(xy[1]));
        }
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass().equals(Coordinate.class)) {
            return this.x == ((Coordinate) obj).x && this.y == ((Coordinate) obj).y;
        }
        return super.equals(obj);
    }
}
