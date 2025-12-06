package aoc2025.day6;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OperationTest {
    private static Stream<Arguments> provideGetResultData()
    {
        return Stream.of(
                Arguments.of(List.of(123, 45, 6), "*", 33210L),
                Arguments.of(List.of(328, 64, 98), "+", 490L),
                Arguments.of(List.of(51, 387, 215), "*", 4243455L),
                Arguments.of(List.of(64, 23, 314), "+", 401L)
        );
    }

    @ParameterizedTest
    @MethodSource("provideGetResultData")
    void testGetResult(List<Integer> numbers, String operationStr, long expected)
    {
        Operation operation = new Operation();
        numbers.forEach(operation::addNumber);
        operation.setOperation(operationStr);
        assertEquals(expected, operation.getResult());
    }
}
