package aoc2025.day3;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BatteryBankTest {
    private static Stream<Arguments> provideBanks()
    {
        return Stream.of(
                Arguments.of("987654321111111", 98),
                Arguments.of("811111111111119", 89),
                Arguments.of("234234234234278", 78),
                Arguments.of("818181911112111", 92)
        );
    }

    @ParameterizedTest
    @MethodSource("provideBanks")
    void testGetLargestPossibleJoltage(String input, int expected)
    {
        BatteryBank batteryBank = new BatteryBank(input);
        assertEquals(expected, batteryBank.getLargestPossibleJoltage());
    }
}
