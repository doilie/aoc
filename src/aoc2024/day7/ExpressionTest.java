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
        List<List<String>> combinations = Expression.generateCombinations(1, List.of(Expression.MULTIPLY, Expression.ADD));
        assertEquals(2, combinations.size());
        assertEquals(List.of("*"), combinations.get(0));
        assertEquals(List.of("+"), combinations.get(1));
    }

    @Test
    void generateCombinations_2()
    {
        List<List<String>> combinations = Expression.generateCombinations(2, List.of(Expression.MULTIPLY, Expression.ADD));
        assertEquals(4, combinations.size());
        assertEquals(List.of(List.of("*", "*"), List.of("*", "+"), List.of("+", "*"), List.of("+", "+")), combinations);
    }

    @Test
    void generateCombinations_3()
    {
        List<List<String>> combinations = Expression.generateCombinations(3, List.of(Expression.MULTIPLY, Expression.ADD));
        assertEquals(8, combinations.size());
        assertEquals(List.of(
                List.of("*", "*", "*"),
                List.of("*", "*", "+"),
                List.of("*", "+", "*"),
                List.of("*", "+", "+"),
                List.of("+", "*", "*"),
                List.of("+", "*", "+"),
                List.of("+", "+", "*"),
                List.of("+", "+", "+")), combinations);
        combinations = Expression.generateCombinations(2, List.of(Expression.MULTIPLY, Expression.ADD));
        assertEquals(4, combinations.size());
        assertEquals(List.of(List.of("*", "*"), List.of("*", "+"), List.of("+", "*"), List.of("+", "+")), combinations);
    }

    @Test
    void generateCombinations_1_concat()
    {
        List<List<String>> combinations = Expression.generateCombinations(1, List.of(Expression.MULTIPLY, Expression.ADD, Expression.CONCATENATE));
        assertEquals(3, combinations.size());
        assertEquals(List.of(List.of("*"), List.of("+"), List.of("||")), combinations);
    }

    @Test
    void generateCombinations_2_concat()
    {
        List<List<String>> combinations = Expression.generateCombinations(2, List.of(Expression.MULTIPLY, Expression.ADD, Expression.CONCATENATE));
        assertEquals(9, combinations.size());
        assertEquals(List.of(
                List.of("*", "*"),
                List.of("*", "+"),
                List.of("*", "||"),
                List.of("+", "*"),
                List.of("+", "+"),
                List.of("+", "||"),
                List.of("||", "*"),
                List.of("||", "+"),
                List.of("||", "||")), combinations);
    }

    @Test
    void evaluate_concat_1()
    {
        Expression expression = new Expression(List.of("15", "||", "6"));
        assertEquals(156, expression.evaluate());
    }

    @Test
    void evaluate_concat_2()
    {
        Expression expression = new Expression(List.of("6", "*", "8", "||", "6", "*", "15"));
        assertEquals(7290, expression.evaluate());
    }

    @Test
    void evaluate_concat_3()
    {
        Expression expression = new Expression(List.of("17", "||", "8", "+", "14"));
        assertEquals(192, expression.evaluate());
    }
}