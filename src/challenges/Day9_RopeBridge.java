package challenges;

import impl.rope.MoveInstruction;
import impl.rope.RopeSequence;

public class Day9_RopeBridge extends Challenge {
    public static void main(String[] args) {
        Day9_RopeBridge day6 = new Day9_RopeBridge();
        day6.doOneStarSolution();
        day6.doTwoStarSolution();
    }

    private final RopeSequence ropeSequence = new RopeSequence();
    public Day9_RopeBridge() {
        super("day9-input.txt");
        parseFile();
    }

    @Override
    protected void parseFile() {
        String[] lines = this.getFileContents().split("\n");
        for (String line : lines) {
            String[] moveString = line.split(" ");
            if (moveString.length == 2) {
                MoveInstruction.Direction direction = MoveInstruction.Direction.getDirectionByAcronym(moveString[0]);
                int steps = Integer.parseInt(moveString[1]);
                MoveInstruction moveInstruction = new MoveInstruction(direction, steps);
                ropeSequence.addHeadMove(moveInstruction);
            }
        }
    }

    @Override
    public void doOneStarSolution() {
        ropeSequence.applyMoves();
        System.out.println("Positions visited by tail: " + ropeSequence.countPositionsVisitedByTail());
    }

    @Override
    public void doTwoStarSolution() {
    }
}
