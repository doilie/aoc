package aoc2025.day5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IngredientsCheckerTest {
    private static String getSampleInput()
    {
        return """
                3-5
                10-14
                16-20
                12-18""";
    }

    private static String getSampleInput2()
    {
        return """
                3-5
                10-14
                16-20
                12-18
                35-40
                36-41
                43-44
                42-45""";
    }

    private static Stream<Arguments> provideIsIngredientFreshData() {
        return Stream.of(
                Arguments.of(getSampleInput(), 1L, false),
                Arguments.of(getSampleInput(), 5L, true),
                Arguments.of(getSampleInput(), 8L, false),
                Arguments.of(getSampleInput(), 11L, true),
                Arguments.of(getSampleInput(), 17L, true),
                Arguments.of(getSampleInput(), 32L, false),

                Arguments.of(getSampleInput2(), 32L, false),
                Arguments.of(getSampleInput2(), 40L, true),
                Arguments.of(getSampleInput2(), 99L, false)
        );
    }

    private static Stream<Arguments> provideCountFreshIngredientsInListData() {
        return Stream.of(
                Arguments.of(getSampleInput(), """
                        1
                        5
                        8
                        11
                        17
                        32""", 3),

                Arguments.of(getSampleInput2(), """
                        32
                        40
                        99""", 1)
        );
    }

    private static Stream<Arguments> provideCountFreshIngredientsInRangesData() {
        return Stream.of(
                Arguments.of(getSampleInput(), 14L),
                Arguments.of(getSampleInput2(), 25L)
        );
    }

    @Test
    public void testIngredientsChecker()
    {
        IngredientsChecker ingredientsChecker = new IngredientsChecker(getSampleInput2());
        Map<Long, Long> freshIngredientsRanges = ingredientsChecker.getFreshIngredientsRanges();
        assertEquals(4, freshIngredientsRanges.size());
        assertTrue(freshIngredientsRanges.containsKey(3L));
        assertEquals(5L, freshIngredientsRanges.get(3L));
        assertTrue(freshIngredientsRanges.containsKey(10L));
        assertEquals(20L, freshIngredientsRanges.get(10L));
        assertTrue(freshIngredientsRanges.containsKey(35L));
        assertEquals(41L, freshIngredientsRanges.get(35L));
        assertTrue(freshIngredientsRanges.containsKey(42L));
        assertEquals(45L, freshIngredientsRanges.get(42L));
    }

    @ParameterizedTest
    @MethodSource("provideIsIngredientFreshData")
    public void testIsIngredientFresh(String input, long ingredientId, boolean expected)
    {
        IngredientsChecker ingredientsChecker = new IngredientsChecker(input);
        assertEquals(expected, ingredientsChecker.isFreshIngredient(ingredientId));
    }

    @ParameterizedTest
    @MethodSource("provideCountFreshIngredientsInListData")
    public void testCountFreshIngredientsInList(String input, String ingredientsList, int expected)
    {
        IngredientsChecker ingredientsChecker = new IngredientsChecker(input);
        assertEquals(expected, ingredientsChecker.countFreshIngredientsInList(ingredientsList));
    }

    @ParameterizedTest
    @MethodSource("provideCountFreshIngredientsInRangesData")
    public void testCountFreshIngredientsInRanges(String input, long expected)
    {
        IngredientsChecker ingredientsChecker = new IngredientsChecker(input);
        assertEquals(expected, ingredientsChecker.countFreshIngredientsInRanges());
    }

}
