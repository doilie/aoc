package aoc2025.day9;

import lib.Position;

import java.math.BigInteger;

public class RectangleFromDiagonal {
    private final String point1;
    private final String point2;

    RectangleFromDiagonal(String point1, String point2) {
        this.point1 = point1;
        this.point2 = point2;
    }

    BigInteger getArea()
    {
        int point1x = Position.getX(point1);
        int point1y = Position.getY(point1);
        int point2x = Position.getX(point2);
        int point2y = Position.getY(point2);

        BigInteger xDiff = BigInteger.valueOf(point1x - point2x).abs().add(BigInteger.ONE);
        BigInteger yDiff = BigInteger.valueOf(point1y - point2y).abs().add(BigInteger.ONE);
        return xDiff.multiply(yDiff);
    }
}
