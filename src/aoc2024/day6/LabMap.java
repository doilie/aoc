package aoc2024.day6;

import lib.Position;

import java.util.*;
import java.util.stream.Collectors;

public class LabMap
{
    private static final Character OBSTRUCTION = '#';
    private static final Character FREE = '.';
    private static final Character START = '^';
    private static final Character UP_DOWN = '|';
    private static final Character LEFT_RIGHT = '-';
    private static final Character INTERSECT = '+';
    private static final Character NEW_OBSTRUCTION = 'O';

    private enum Direction
    {
        Up,
        Down,
        Left,
        Right
    }
    private final Map<String, Character> positions = new HashMap<>();
    private int maxX;
    private int maxY;
    private String currentPosition;
    private Direction currentDirection;
    private final String originalInput;

    public LabMap(String input)
    {
        this.originalInput = input;
        initializeMap();
    }

    private void initializeMap()
    {
        currentDirection = Direction.Up;
        String[] lines = originalInput.split("\\n");
        if (lines.length > 0)
        {
            maxX = lines[0].length();
            maxY = lines.length;
            for (int y = 0; y < lines.length; y++)
            {
                String line = lines[y];
                for (int x = 0; x < line.length(); x++)
                {
                    positions.put(Position.getPosition(x, y), line.charAt(x));
                    if (line.charAt(x) == START)
                    {
                        currentPosition = Position.getPosition(x, y);
                    }
                }
            }
        }
    }

    public String getCurrentPosition()
    {
        return currentPosition;
    }

    private boolean hasExited(String newPosition)
    {
        return newPosition != null && !positions.containsKey(newPosition);
    }

    private boolean shouldTurn(String newPosition)
    {
        return positions.get(newPosition) == OBSTRUCTION || positions.get(newPosition) == NEW_OBSTRUCTION;
    }

    private String getNewPosition()
    {
        int x = Position.getX(currentPosition);
        int y = Position.getY(currentPosition);
        switch(currentDirection)
        {
            case Up: y--; break;
            case Down: y++; break;
            case Left: x--; break;
            case Right: x++; break;
        }
        return Position.getPosition(x, y);
    }

    private void turn()
    {
        currentDirection = switch (currentDirection) {
            case Up -> Direction.Right;
            case Right -> Direction.Down;
            case Down -> Direction.Left;
            case Left -> Direction.Up;
        };
    }

    private void setMarkerAtPosition(String newPosition)
    {
        char newPositionChar = positions.get(newPosition);
        if (currentDirection == Direction.Up || currentDirection == Direction.Down)
        {
            if (newPositionChar == LEFT_RIGHT)
            {
                positions.put(newPosition, INTERSECT);
            }
            else if (newPositionChar != START)
            {
                positions.put(newPosition, UP_DOWN);
            }
        }
        else if (currentDirection == Direction.Left || currentDirection == Direction.Right)
        {
            if (newPositionChar == UP_DOWN)
            {
                positions.put(newPosition, INTERSECT);
            }
            else if (newPositionChar != START)
            {
                positions.put(newPosition, LEFT_RIGHT);
            }
        }
    }

    private void setMarkerForTurn(String newPosition)
    {
        positions.put(newPosition, INTERSECT);
    }

    private void visit(String newPosition)
    {
        setMarkerAtPosition(newPosition);
        currentPosition = newPosition;

    }

    private static class Node
    {
        String position;
        Node next;

        Node(String position)
        {
            this.position = position;
            this.next = null;
        }
    }

    boolean detectLoop(Node head)
    {
        Set<Node> visited = new HashSet<>();
        while (head != null)
        {
            if (visited.contains(head))
                return true;

            visited.add(head);

            head = head.next;
        }

        return false;
    }

    public boolean hasMovedUntilExit()
    {
        Map<String, Node> nodeMap = new HashMap<>();
        String headPosition = null;
        String oldPosition = null;
        String newPosition = getNewPosition();
        Node head = null;
        Node tail = null;

        while(!hasExited(newPosition))
        {
            if (shouldTurn(newPosition))
            {
                Node node = new Node(oldPosition);
                if (nodeMap.containsKey(oldPosition))
                {
                    node = nodeMap.get(oldPosition);
                }
                else
                {
                    nodeMap.put(oldPosition, node);
                }
                if (head == null)
                {
                    head = node;
                    tail = head;
                    headPosition = oldPosition;
                }
                else
                {
                    tail.next = node;
                    tail = node;
                }
                if (detectLoop(nodeMap.get(headPosition)))
                {
                    return false;
                }

                turn();
                if (oldPosition != null)
                {
                    setMarkerForTurn(oldPosition);
                }
            }
            else
            {
                visit(newPosition);
            }
            oldPosition = newPosition;
            newPosition = getNewPosition();
        }
        return true;
    }

    private Set<String> getPossibleObstructionPositions()
    {
        return positions.entrySet().stream().filter(kv -> kv.getValue() != OBSTRUCTION && kv.getValue() != START && kv.getValue() != FREE).map(Map.Entry::getKey).collect(Collectors.toSet());
    }

    public int countPositionsThatCauseLoop()
    {
        int count = 0;
        Set<String> possiblePositions = getPossibleObstructionPositions();
        int size = possiblePositions.size();
        int currentPositionIndex = 0;
        for (String position : possiblePositions)
        {
            initializeMap();
            int x = Position.getX(position);
            int y = Position.getY(position);
            positions.put(Position.getPosition(x, y), NEW_OBSTRUCTION);
            currentPositionIndex++;
            if (currentPositionIndex % 1000 == 0)
            {
                System.out.println("Processing: " + currentPositionIndex + "/" + size + ", Current count: " + count);
            }
            if (!hasMovedUntilExit())
            {
                count++;
            }

        }
        return count;
    }

    public int getVisitedPositionsCount()
    {
        return positions.values().stream().filter(c -> c != FREE && c != OBSTRUCTION).toList().size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < maxY; y++)
        {
            for (int x = 0; x < maxX; x++)
            {
                sb.append(positions.get(Position.getPosition(x, y)));
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
