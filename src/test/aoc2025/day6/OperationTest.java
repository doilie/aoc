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
                Arguments.of(List.of("123", " 45", "  6"), "*", 33210L),
                Arguments.of(List.of("328", "64 ", "98 "), "+", 490L),
                Arguments.of(List.of(" 51", "387", "215"), "*", 4243455L),
                Arguments.of(List.of("64 ", "23 ", "314"), "+", 401L)
        );
    }

    private static Stream<Arguments> provideGetCephalopodNumbersData()
    {
        return Stream.of(
                Arguments.of(List.of("123", " 45", "  6"), List.of("356", "24 ", "1  ")),
                Arguments.of(List.of("328", "64 ", "98 "), List.of("8  ", "248", "369")),
                Arguments.of(List.of(" 51", "387", "215"), List.of("175", "581", " 32")),
                Arguments.of(List.of("64 ", "23 ", "314"), List.of("  4", "431", "623"))
        );
    }

    private static Stream<Arguments> provideGetCephalopodResultData()
    {
        return Stream.of(
                Arguments.of(List.of("123", " 45", "  6"), "*", 8544L),
                Arguments.of(List.of("328", "64 ", "98 "), "+", 625L),
                Arguments.of(List.of(" 51", "387", "215"), "*", 3253600L),
                Arguments.of(List.of("64 ", "23 ", "314"), "+", 1058L)
        );
    }

    @ParameterizedTest
    @MethodSource("provideGetResultData")
    void testGetResult(List<String> numbers, String operationStr, long expected)
    {
        Operation operation = new Operation(3);
        numbers.forEach(operation::addNumber);
        operation.setOperation(operationStr);
        assertEquals(expected, operation.getResult());
    }

    @ParameterizedTest
    @MethodSource("provideGetCephalopodNumbersData")
    void testGetCephalopodNumbers(List<String> numbers, List<String> expected)
    {
        Operation operation = new Operation(3);
        numbers.forEach(operation::addNumber);
        assertEquals(expected, operation.getCephalopodNumbers());
    }

    @ParameterizedTest
    @MethodSource("provideGetCephalopodResultData")
    void testGetCephalopodResult(List<String> numbers, String operationStr, long expected)
    {
        Operation operation = new Operation(3);
        numbers.forEach(operation::addNumber);
        operation.setOperation(operationStr);
        assertEquals(expected, operation.getCephalopodResult());
    }
}
