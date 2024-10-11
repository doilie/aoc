package aoc2022.day22;

import java.util.ArrayList;
import java.util.List;

public class CircularMonkeyMapPath {
    public enum PathDirection {
        Forward,
        Backward
    }
    private final List<MonkeyMapTile> elements;
    private final int startIndex;

    public CircularMonkeyMapPath(List<MonkeyMapTile> elements, int startIndex) {
        this.elements = elements;
        this.startIndex = startIndex;
    }

    public List<Integer> moveUntilWall(int currPosition, int givenSteps, PathDirection pathDirection) {
        int targetPosition = currPosition;
        switch(pathDirection) {
            case Forward:
                targetPosition = currPosition + getPossibleStepsForward(currPosition - startIndex, givenSteps);
                break;
            case Backward:
                targetPosition = currPosition - getPossibleStepsBackward(currPosition - startIndex, givenSteps);
                break;
        }
        return getPossibleIndices(currPosition, targetPosition);
    }

    private int getPossibleStepsForward(int currPosition, int steps) {
        if (isValidPositionToMove(currPosition)) {
            List<MonkeyMapTile> elementsTargetAtStart = rotateElements(currPosition);
            int wallIndex = -1;
            for (int i = 0; i < elementsTargetAtStart.size(); i++) {
                MonkeyMapTile monkeyMapTile = elementsTargetAtStart.get(i);
                if (monkeyMapTile.getTile().equals(MonkeyMapTile.WallTile)) {
                    wallIndex = i;
                    break;
                }
            }
            if (wallIndex > -1) {
                return Math.min(wallIndex - 1, steps);
            }
            else {
                return steps;
            }
        }
        return 0;
    }

    private int getPossibleStepsBackward(int currPosition, int steps) {
        if (isValidPositionToMove(currPosition)) {
            List<MonkeyMapTile> elementsTargetAtEnd = rotateElements(currPosition + 1);
            int wallIndex = -1;
            for (int i = elementsTargetAtEnd.size() - 1; i >= 1; i--) {
                MonkeyMapTile monkeyMapTile = elementsTargetAtEnd.get(i - 1);
                if (monkeyMapTile.getTile().equals(MonkeyMapTile.WallTile)) {
                    wallIndex = i;
                    break;
                }
            }
            if (wallIndex > -1) {
                return Math.min(elements.size() - 1 - wallIndex, steps);
            }
            else {
                return steps;
            }
        }
        return 0;
    }

    private boolean isValidPositionToMove(int currPosition) {
        // cannot move wall tile
        return currPosition <= elements.size() && !elements.get(currPosition).getTile().equals(MonkeyMapTile.WallTile);
    }

    private List<MonkeyMapTile> rotateElements(int positionToSplit) {
        List<MonkeyMapTile> repositionedList = new ArrayList<>(elements.subList(positionToSplit, elements.size()));
        repositionedList.addAll(elements.subList(0, positionToSplit));
        return repositionedList;
    }

    private List<Integer> getPossibleIndices(int sourcePosition, int targetPosition) {
        List<Integer> possibleIndices = new ArrayList<>();
        if (sourcePosition < targetPosition) {
            for (int i = sourcePosition; i <= targetPosition; i++) {
                possibleIndices.add(getEquivalentIndex(i));
            }
        }
        else {
            for (int i = sourcePosition; i >= targetPosition; i--) {
                possibleIndices.add(getEquivalentIndex(i));
            }
        }
        return possibleIndices;
    }

    private int getEquivalentIndex(int index) {
        int equivalentIndex = (index - startIndex) % elements.size();
        if (equivalentIndex < 0) {
            equivalentIndex = elements.size() + equivalentIndex;
        }
        return equivalentIndex + startIndex;
    }
}
