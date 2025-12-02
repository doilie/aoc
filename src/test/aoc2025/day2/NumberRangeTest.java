package aoc2025.day2;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumberRangeTest
{
    private static Stream<Arguments> provideSampleDataTwoRepeatingSequences()
    {
        return Stream.of(
                Arguments.of("11-22", Set.of(11L, 22L)),
                Arguments.of("95-115", Set.of(99L)),
                Arguments.of("998-1012", Set.of(1010L)),
                Arguments.of("1188511880-1188511890", Set.of(1188511885L)),
                Arguments.of("222220-222224", Set.of(222222L)),
                Arguments.of("1698522-1698528", Set.of()),
                Arguments.of("446443-446449", Set.of(446446L)),
                Arguments.of("38593856-38593862", Set.of(38593859L)),
                Arguments.of("565653-565659", Set.of()),
                Arguments.of("824824821-824824827", Set.of()),
                Arguments.of("2121212118-2121212124", Set.of())
        );
    }

    private static Stream<Arguments> provideSampleDataRepeatingSequences()
    {
        return Stream.of(
                Arguments.of("11-22", Set.of(11L, 22L)),
                Arguments.of("95-115", Set.of(99L, 111L)),
                Arguments.of("998-1012", Set.of(999L, 1010L)),
                Arguments.of("1188511880-1188511890", Set.of(1188511885L)),
                Arguments.of("222220-222224", Set.of(222222L)),
                Arguments.of("1698522-1698528", Set.of()),
                Arguments.of("446443-446449", Set.of(446446L)),
                Arguments.of("38593856-38593862", Set.of(38593859L)),
                Arguments.of("565653-565659", Set.of(565656L)),
                Arguments.of("824824821-824824827", Set.of(824824824L)),
                Arguments.of("2121212118-2121212124", Set.of(2121212121L))
        );
    }

    @ParameterizedTest
    @MethodSource("provideSampleDataTwoRepeatingSequences")
    void testGetTwoRepeatingSequences(String input, Set<Long> expected)
    {
        NumberRange range = new NumberRange(input);
        Set<Long> actual = range.getTwoRepeatingSequences();
        assertEquals(expected.size(), actual.size());
        for (Long l : expected)
        {
            assertTrue(actual.contains(l));
        }
    }

    @ParameterizedTest
    @MethodSource("provideSampleDataRepeatingSequences")
    void testGetRepeatingSequence(String input, Set<Long> expected)
    {
        NumberRange range = new NumberRange(input);
        Set<Long> actual = range.getRepeatingSequence();
        assertEquals(expected.size(), actual.size());
        for (Long l : expected)
        {
            assertTrue(actual.contains(l));
        }
    }
}
