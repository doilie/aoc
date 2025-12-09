package aoc2025.day9;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigInteger;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RectangleFromDiagonalTest {
    private static Stream<Arguments> provideGetAreaData()
    {
        return Stream.of(
                Arguments.of("2,5", "9,7", BigInteger.valueOf(24)),
                Arguments.of("7,1", "11,7", BigInteger.valueOf(35)),
                Arguments.of("7,3", "2,3", BigInteger.valueOf(6)),
                Arguments.of("2,5", "11,1", BigInteger.valueOf(50))
        );
    }
    @ParameterizedTest
    @MethodSource("provideGetAreaData")
    void testGetArea(String point1, String point2, BigInteger expectedArea)
    {
        RectangleFromDiagonal rectangleFromDiagonal = new RectangleFromDiagonal(point1, point2);
        BigInteger actualArea = rectangleFromDiagonal.getArea();
        assertEquals(expectedArea, actualArea);
    }
}
