package aoc2016.day1;

import java.util.*;

public class InstructionList {
    private enum MapDirection {
        North,
        South,
        East,
        West
    }
    private final List<Instruction> instructions = new ArrayList<>();

    public void addInstruction(String instructionString) {
        String directionPart = instructionString.substring(0, 1);
        String stepsPart = instructionString.substring(1);
        int steps = Integer.parseInt(stepsPart);
        switch(directionPart) {
            case "R": instructions.add(new Instruction(Instruction.TurnDirection.Right, steps)); break;
            case "L": instructions.add(new Instruction(Instruction.TurnDirection.Left, steps)); break;
        }
    }

    public int getNumberOfBlocksAwayToEnd() {
        MapDirection currentMapDirection = MapDirection.North;
        Location currentLocation = new Location(0, 0);
        for (Instruction instruction : instructions) {
            currentMapDirection = changeDirection(currentMapDirection, instruction);
            currentLocation = moveLocation(currentLocation, currentMapDirection, instruction.getSteps());
        }

        return Math.abs(currentLocation.getX()) + Math.abs(currentLocation.getY());
    }

    public int getNumberOfBlocksAwayToVisitedTwice() {
        MapDirection currentMapDirection = MapDirection.North;
        Location currentLocation = new Location(0, 0);

        List<String> visitedLocations = new ArrayList<>();
        visitedLocations.add(currentLocation.getName());
        for (Instruction instruction : instructions) {
            currentMapDirection = changeDirection(currentMapDirection, instruction);
            for (int i = 0; i < instruction.getSteps(); i++) {
                currentLocation = moveLocation(currentLocation, currentMapDirection, 1);
                if (visitedLocations.contains(currentLocation.getName())) {
                    return Math.abs(currentLocation.getX()) + Math.abs(currentLocation.getY());
                }
                else {
                    visitedLocations.add(currentLocation.getName());
                }
            }
        }

        return Math.abs(currentLocation.getX()) + Math.abs(currentLocation.getY());
    }

    private MapDirection changeDirection(MapDirection currentMapDirection, Instruction instruction) {
        if (instruction.getTurnDirection().equals(Instruction.TurnDirection.Right)) {
            switch (currentMapDirection) {
                case North: currentMapDirection = MapDirection.East; break;
                case East: currentMapDirection = MapDirection.South; break;
                case South: currentMapDirection = MapDirection.West; break;
                case West: currentMapDirection = MapDirection.North; break;
            }
        }
        else if (instruction.getTurnDirection().equals(Instruction.TurnDirection.Left)) {
            switch (currentMapDirection) {
                case North: currentMapDirection = MapDirection.West; break;
                case West: currentMapDirection = MapDirection.South; break;
                case South: currentMapDirection = MapDirection.East; break;
                case East: currentMapDirection = MapDirection.North; break;
            }
        }
        return currentMapDirection;
    }

    private Location moveLocation(Location location, MapDirection currentMapDirection, int numSteps) {
        Location newLocation = location;
        switch(currentMapDirection) {
            case North: newLocation = new Location(location.getX(), location.getY() - numSteps); break;
            case South: newLocation = new Location(location.getX(), location.getY() + numSteps); break;
            case East: newLocation = new Location(location.getX() + numSteps, location.getY()); break;
            case West: newLocation = new Location(location.getX() - numSteps, location.getY()); break;
        }
        return newLocation;
    }
}
