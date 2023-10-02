package impl.rope;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class RopeSequence {
    private final Hashtable<String, Position> allPositions = new Hashtable<>();
    private final List<MoveInstruction> moveInstructions;
    private final RopeHead head = new RopeHead();
    private final RopeTail tail = new RopeTail(head);
    private final int startingPositionIdx = 8;

    public RopeSequence() {
        moveInstructions = new ArrayList<>();
        Position startingPosition = new Position(startingPositionIdx, startingPositionIdx);
        allPositions.put(startingPosition.getName(), startingPosition);
        head.setCurrentPosition(startingPosition);
        tail.setCurrentPosition(startingPosition);
        startingPosition.setStartingPosition(true);
    }

    public void addHeadMove(MoveInstruction moveInstruction) {
        this.moveInstructions.add(moveInstruction);
    }

    public void applyMoves() {
        for (MoveInstruction moveInstruction : moveInstructions) {
            for (int i = 0; i < moveInstruction.getSteps(); i++) {
                Position newHeadPosition = head.move(moveInstruction.getDirection());
                newHeadPosition = addPositionToMap(newHeadPosition);
                head.setCurrentPosition(newHeadPosition);
//                System.out.print("H " + newHeadPosition.getName());

                Position newTailPosition = tail.move();
                newTailPosition = addPositionToMap(newTailPosition);
                tail.setCurrentPosition(newTailPosition);
//                System.out.println(", T " + newTailPosition.getName());
//                printCurrentRopeEnds();
            }
        }
    }

    public void printPositionsVisitedByHead() {
        this.printPositionsVisitedByRopeEnd(head);
    }

    public void printPositionsVisitedByTail() {
        this.printPositionsVisitedByRopeEnd(tail);
    }

    public int countPositionsVisitedByHead() {
        return this.countPositionsVisitedByRopeEnd(head);
    }

    public int countPositionsVisitedByTail() {
        return this.countPositionsVisitedByRopeEnd(tail);
    }

    private Position addPositionToMap(Position position) {
        Position newPosition = position;
        if (allPositions.containsKey(newPosition.getName())) {
            newPosition = allPositions.get(newPosition.getName());
        }
        this.allPositions.putIfAbsent(newPosition.getName(), newPosition);
        return newPosition;
    }

    private void printPositionsVisitedByRopeEnd(RopeEnd ropeEnd) {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < this.startingPositionIdx * 2; row++) {
            for (int column = 0; column < this.startingPositionIdx * 2; column++) {
                String key = row + "," + column;
                Position position = allPositions.get(key);
                if (position == null) {
                    sb.append(".");
                }
                else if (position.isStartingPosition()) {
                    sb.append("s");
                }
                else if (ropeEnd.currentPosition == position) {
                    sb.append(ropeEnd.getName());
                }
                else if (position.didRopeEndVisit(ropeEnd)) {
                    sb.append("#");
                }
                else {
                    sb.append(".");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private void printCurrentRopeEnds() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < this.startingPositionIdx * 2; row++) {
            for (int column = 0; column < this.startingPositionIdx * 2; column++) {
                String key = row + "," + column;
                Position position = allPositions.get(key);
                if (position == null) {
                    sb.append("-");
                }
                else if (position.isStartingPosition()) {
                    sb.append("s");
                }
                else if (head.currentPosition == position) {
                    sb.append(head.getName());
                }
                else if (tail.currentPosition == position) {
                    sb.append(tail.getName());
                }
                else {
                    sb.append(".");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private int countPositionsVisitedByRopeEnd(RopeEnd ropeEnd) {
        return (int) allPositions.values().stream().filter(position -> position.didRopeEndVisit(ropeEnd)).count();
    }

}
