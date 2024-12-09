package aoc2024.day7;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionTest {

    @Test
    void evaluate_multiply()
    {
        Expression expression = new Expression(List.of("10", "*", "19"));
        assertEquals(190, expression.evaluate());
    }


    @Test
    void evaluate_add()
    {
        Expression expression = new Expression(List.of("10", "+", "19"));
        assertEquals(29, expression.evaluate());
    }

    @Test
    void evaluate_combo_1()
    {
        Expression expression = new Expression(List.of("81", "+", "40", "*", "27"));
        assertEquals(3267, expression.evaluate());
    }

    @Test
    void evaluate_combo_2()
    {
        Expression expression = new Expression(List.of("81", "*", "40", "+", "27"));
        assertEquals(3267, expression.evaluate());
    }

    @Test
    void evaluate_combo_3()
    {
        Expression expression = new Expression(List.of("7","+","3","*","6","+","4","*","393","*","4","*","1","*","93","*","3","+","7","+","9","*","7"));
        assertEquals(196487536, expression.evaluate());
    }

    @Test
    void generateCombinations_1()
    {
        List<String> combinations = Expression.generateCombinations(1);
        assertEquals(2, combinations.size());
        assertEquals("*", combinations.get(0));
        assertEquals("+", combinations.get(1));
    }

    @Test
    void generateCombinations_2()
    {
        List<String> combinations = Expression.generateCombinations(2);
        assertEquals(4, combinations.size());
        assertEquals(List.of("**", "*+", "+*", "++"), combinations);
    }

    @Test
    void generateCombinations_3()
    {
        List<String> combinations = Expression.generateCombinations(3);
        assertEquals(8, combinations.size());
        assertEquals(List.of("***", "**+", "*+*", "*++", "+**", "+*+", "++*", "+++"), combinations);
        combinations = Expression.generateCombinations(2);
        assertEquals(4, combinations.size());
        assertEquals(List.of("**", "*+", "+*", "++"), combinations);
    }
}