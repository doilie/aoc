package challenges;

import impl.rope.MoveInstruction;
import impl.rope.RopeSequence;
import impl.rope.RopeTail;

import java.util.ArrayList;
import java.util.List;

public class Day9_RopeBridge extends Challenge {
    public static void main(String[] args) {
        Day9_RopeBridge day9 = new Day9_RopeBridge();
        day9.doOneStarSolution();
        day9.doTwoStarSolution();
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
        RopeTail ropeTail = ropeSequence.addRopeTail("T");
        ropeSequence.applyMoves();
        System.out.println("Positions visited by tail: " + ropeSequence.countPositionsVisitedByRopeEnd(ropeTail));
    }

    @Override
    public void doTwoStarSolution() {
        ropeSequence.reset();
        final int tailCount = 9;
        List<RopeTail> ropeTailList = new ArrayList<>();
        for (int i = 1; i <= tailCount; i++) {
            ropeTailList.add(ropeSequence.addRopeTail(Integer.toString(i)));
        }
        RopeTail finalTail = ropeTailList.get(ropeTailList.size() - 1);
        ropeSequence.applyMoves();
        System.out.println("Positions visited by last tail: " + ropeSequence.countPositionsVisitedByRopeEnd(finalTail));
    }
}
