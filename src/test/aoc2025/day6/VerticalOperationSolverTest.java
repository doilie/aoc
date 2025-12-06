package aoc2025.day6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VerticalOperationSolverTest {
    @Test
    void testSolve()
    {
        String sampleInput = """
                123 328  51 64
                 45 64  387 23
                  6 98  215 314
                *   +   *   +""";
        VerticalOperationSolver verticalOperationSolver = new VerticalOperationSolver(sampleInput);
        assertEquals(4277556L, verticalOperationSolver.solve());
    }
}
