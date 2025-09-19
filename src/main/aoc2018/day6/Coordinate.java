package aoc2018.day6;

public class Coordinate {
    private final int x;
    private final int y;

    public Coordinate(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDistance(Coordinate c)
    {
        return Math.abs(this.x - c.x) + Math.abs(this.y - c.y);
    }

    @Override
    public String toString() {
        return x + "," + y;
    }

    @Override
    public boolean equals(Object arg0) {
        if (arg0.getClass().equals(Coordinate.class))
        {
            return (this.x == ((Coordinate) arg0).x && this.y == ((Coordinate) arg0).y);
        }
        return super.equals(arg0);
    }

    public static Coordinate createCoordinate(String coordString)
    {
        String[] coStrings = coordString.split(",");
        if (coStrings.length == 2)
        {
            return new Coordinate(Integer.parseInt(coStrings[0].trim()), Integer.parseInt(coStrings[1].trim()));
        }
        else
        {
            throw new IllegalArgumentException("Invalid coordinate");
        }
    }
}
