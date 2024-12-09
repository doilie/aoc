package aoc2024.day7;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EquationTest {

    @Test
    void getRecordedResult()
    {
        Equation equation = new Equation("190: 10 19");
        assertEquals(190, equation.getRecordedResult());
    }

    @Test
    void getExpressions()
    {
        Equation equation = new Equation("190: 10 19");
        List<Expression> expressions = equation.getExpressions();
        assertEquals(2, expressions.size());
    }

    @Test
    void isValidExpression_true()
    {
        Equation equation = new Equation("190: 10 19");
        Expression expression = new Expression(List.of("10", "*", "19"));
        assertTrue(equation.isValidExpression(expression));
    }

    @Test
    void isValidExpression_false()
    {
        Equation equation = new Equation("190: 10 19");
        Expression expression = new Expression(List.of("10", "+", "19"));
        assertFalse(equation.isValidExpression(expression));
    }

    @Test
    void hasValidResult_1()
    {
        Equation equation = new Equation("190: 10 19");
        assertTrue(equation.hasValidResult());
    }

    @Test
    void hasValidResult_2()
    {
        Equation equation = new Equation("3267: 81 40 27");
        assertTrue(equation.hasValidResult());
    }

    @Test
    void hasValidResult_3()
    {
        Equation equation = new Equation("292: 11 6 16 20");
        assertTrue(equation.hasValidResult());
    }

    @Test
    void hasValidResult_4()
    {
        Equation equation = new Equation("196487536: 7 3 6 4 393 4 1 93 3 7 9 7");
        assertTrue(equation.hasValidResult());
    }

    @Test
    void hasValidResult_5()
    {
        Equation equation = new Equation("1819723819533: 8 3 62 7 85 3 8 7 64 4 6 7");
        assertFalse(equation.hasValidResult());
    }

    @Test void getValidExpressionSet()
    {
        List<Equation> equations = new ArrayList<>(Arrays.stream("""
                190: 10 19
                3267: 81 40 27
                83: 17 5
                156: 15 6
                7290: 6 8 6 15
                161011: 16 10 13
                192: 17 8 14
                21037: 9 7 18 13
                292: 11 6 16 20""".split("\\n")).map(Equation::new).toList());

        List<Equation> equationsWithValidResults = equations.stream().filter(Equation::hasValidResult).toList();
        assertEquals(3749, equationsWithValidResults.stream().map(Equation::getRecordedResult).mapToLong(Long::longValue).sum());
    }

}