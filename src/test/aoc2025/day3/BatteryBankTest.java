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
                Arguments.of("987654321111111", 98L, 987654321111L),
                Arguments.of("811111111111119", 89L, 811111111119L),
                Arguments.of("234234234234278", 78L, 434234234278L),
                Arguments.of("818181911112111", 92L, 888911112111L)
        );
    }

    @ParameterizedTest
    @MethodSource("provideBanks")
    void testGetLargestPossibleJoltage2Batteries(String input, long expected2Batteries, long expected12Batteries)
    {
        BatteryBank batteryBank = new BatteryBank(input);
        assertEquals(expected2Batteries, batteryBank.getLargestPossibleJoltage());
        batteryBank.setNumberOfBatteriesOn(12);
        assertEquals(expected12Batteries, batteryBank.getLargestPossibleJoltage());
    }
}
