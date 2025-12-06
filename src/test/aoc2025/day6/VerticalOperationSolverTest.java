package aoc2025.day6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VerticalOperationSolverTest {
    @Test
    void testSolve()
    {
        String sampleInput = "123 328  51 64 \n 45 64  387 23 \n  6 98  215 314\n*   +   *   +   \n";
        VerticalOperationSolver verticalOperationSolver = new VerticalOperationSolver(sampleInput);
        assertEquals(4277556L, verticalOperationSolver.solve());
    }

    @Test
    void testSolveCephalopodally()
    {
        String sampleInput = "123 328  51 64 \n 45 64  387 23 \n  6 98  215 314\n*   +   *   +   \n";
        VerticalOperationSolver verticalOperationSolver = new VerticalOperationSolver(sampleInput);
        assertEquals(3263827L, verticalOperationSolver.solveCephalopodally());
    }
}
