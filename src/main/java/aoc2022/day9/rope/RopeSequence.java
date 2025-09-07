package aoc2022.day9.rope;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class RopeSequence {
    private final List<MoveInstruction> moveInstructions = new ArrayList<>();
    private final RopeHead head = new RopeHead();
    private final int startingPositionIdx = 100;
    private List<RopeTail> ropeTails;
    private Hashtable<String, Position> allPositions;

    public RopeSequence() {
        reset();
    }

    public void reset() {
        ropeTails = new ArrayList<>();
        allPositions = new Hashtable<>();
        Position startingPosition = new Position(startingPositionIdx, startingPositionIdx);
        allPositions.put(startingPosition.getName(), startingPosition);
        head.setCurrentPosition(startingPosition);
        startingPosition.setStartingPosition(true);
    }

    public RopeTail addRopeTail(String name) {
        RopeTail ropeTail = null;
        if (name != null) {
            RopeEnd previousRopeEnd = ropeTails.isEmpty() ? head : ropeTails.get(ropeTails.size() - 1);
            ropeTail = new RopeTail(name, previousRopeEnd);
            ropeTail.setCurrentPosition(previousRopeEnd.currentPosition);
            ropeTails.add(ropeTail);
        }
        return ropeTail;
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

                for (RopeTail tail : ropeTails) {
                    Position newTailPosition = tail.move();
                    newTailPosition = addPositionToMap(newTailPosition);
                    tail.setCurrentPosition(newTailPosition);
//                    System.out.println(", " + tail.getName() + " " + newTailPosition.getName());
//                    printCurrentRopeEnds();
                }
            }
        }
    }

    private Position addPositionToMap(Position position) {
        Position newPosition = position;
        if (allPositions.containsKey(newPosition.getName())) {
            newPosition = allPositions.get(newPosition.getName());
        }
        this.allPositions.putIfAbsent(newPosition.getName(), newPosition);
        return newPosition;
    }

    public void printPositionsVisitedByRopeEnd(RopeEnd ropeEnd) {
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
                else {
                    boolean tailFound = false;
                    for (RopeTail ropeTail : ropeTails) {
                        if (ropeTail.currentPosition == position) {
                            sb.append(ropeTail.getName());
                            tailFound = true;
                            break;
                        }
                    }
                    if (!tailFound) {
                        sb.append(".");
                    }
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public int countPositionsVisitedByRopeEnd(RopeEnd ropeEnd) {
        return (int) allPositions.values().stream().filter(position -> position.didRopeEndVisit(ropeEnd)).count();
    }

}
