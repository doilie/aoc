package impl.rope;

public class MoveInstruction {
    public enum Direction {
        Up,
        Down,
        Left,
        Right;

        public static Direction getDirectionByAcronym(String acronym) {
            switch (acronym) {
                case "U": return Up;
                case "D": return Down;
                case "L": return Left;
                case "R": return Right;
            }
            return null;
        }
    }

    private final Direction direction;
    private final int steps;

    public MoveInstruction(Direction direction, int numSteps) {
        this.direction = direction;
        this.steps = numSteps;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getSteps() {
        return steps;
    }
}
