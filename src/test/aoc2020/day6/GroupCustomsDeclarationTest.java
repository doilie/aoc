package aoc2020.day6;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GroupCustomsDeclarationTest
{
    private static Stream<Arguments> provideInput()
    {
        return Stream.of(
                Arguments.of("""
                        abcx
                        abcy
                        abcz
                        """, 6, 3),
                Arguments.of("abc", 3, 3),
                Arguments.of("""
                        a
                        b
                        c""", 3, 0),
                Arguments.of("""
                        ab
                        ac""", 3, 1),
                Arguments.of("""
                        a
                        a
                        a
                        a""", 1, 1),
                Arguments.of("b", 1, 1)
        );
    }

    @ParameterizedTest
    @MethodSource("provideInput")
    void testGetYesAnswersCount(String input, int expectedYesAnswers, int expectedCommonYesAnswers)
    {
        GroupCustomsDeclaration groupCustomsDeclaration = new GroupCustomsDeclaration(input);
        assertEquals(expectedYesAnswers, groupCustomsDeclaration.getYesAnswers());
        assertEquals(expectedCommonYesAnswers, groupCustomsDeclaration.getCommonYesAnswers());
    }
}
