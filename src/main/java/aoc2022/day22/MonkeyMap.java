package aoc2022.day22;

import java.util.*;
import java.util.stream.Collectors;

public class MonkeyMap {
    private final Hashtable<String, MonkeyMapTile> monkeyMapTiles = new Hashtable<>();
    private final Hashtable<Integer, CircularMonkeyMapPath> xCircularPaths = new Hashtable<>();
    private final Hashtable<Integer, CircularMonkeyMapPath> yCircularPaths = new Hashtable<>();
    private Position position;

    public void addTile(String line, int y) {
        List<String> possibleTiles = List.of(MonkeyMapTile.OpenTile, MonkeyMapTile.WallTile);
        for (int x = 1; x <= line.length(); x++) {
            String currentChar = Character.toString(line.charAt(x - 1));
            if (possibleTiles.contains(currentChar)) {
                MonkeyMapTile newTile = new MonkeyMapTile(x, y, currentChar);
                monkeyMapTiles.put(newTile.getPositionName(), newTile);
            }
        }
    }

    public void movePosition(MonkeyTileMove move) {
        CircularMonkeyMapPath.PathDirection pathDirection = getPathDirection();
        List<Integer> visitedTileIndices;
        if (pathDirection != null) {
            switch(position.direction) {
                case Right:
                case Left:
                    visitedTileIndices = yCircularPaths.get(position.y).moveUntilWall(position.x, move.getSteps(), pathDirection);
                    for (int x : visitedTileIndices) {
                        monkeyMapTiles.put(x + "," + position.y, new MonkeyMapTile(x, position.y, position.direction.symbol));
                    }
                    position.x = visitedTileIndices.get(visitedTileIndices.size() - 1);
                    break;
                case Down:
                case Up:
                    visitedTileIndices = xCircularPaths.get(position.x).moveUntilWall(position.y, move.getSteps(), pathDirection);
                    for (int y : visitedTileIndices) {
                        monkeyMapTiles.put(position.x + "," + y, new MonkeyMapTile(position.x, y, position.direction.symbol));
                    }
                    position.y = visitedTileIndices.get(visitedTileIndices.size() - 1);
                    break;
            }
            position.direction = getNewDirection(position.direction, move.getTurn());
        }
    }

    public static FacingDirection getNewDirection(FacingDirection direction, MonkeyTileMove.Turn turn) {
        if (turn == MonkeyTileMove.Turn.Clockwise) {
            return switch (direction) {
                case Right -> FacingDirection.Down;
                case Down -> FacingDirection.Left;
                case Left -> FacingDirection.Up;
                case Up -> FacingDirection.Right;
            };
        }
        else if (turn == MonkeyTileMove.Turn.Counterclockwise) {
            return switch (direction) {
                case Right -> FacingDirection.Up;
                case Up -> FacingDirection.Left;
                case Left -> FacingDirection.Down;
                case Down -> FacingDirection.Right;
            };
        }
        return direction;
    }

    private CircularMonkeyMapPath.PathDirection getPathDirection() {
        return switch (position.direction) {
            case Right, Down -> CircularMonkeyMapPath.PathDirection.Forward;
            case Left, Up -> CircularMonkeyMapPath.PathDirection.Backward;
        };
    }

    public void setRanges() {
        int firstRowStartX = 0;
        int maxRow = monkeyMapTiles.values().stream().map(MonkeyMapTile::getY).sorted().toList().get(monkeyMapTiles.values().size() - 1);
        for (int y = 1; y <= maxRow; y++) {
            final int yComp = y;
            List<MonkeyMapTile> yTiles = monkeyMapTiles.values().stream().filter(t -> t.getY() == yComp).sorted().collect(Collectors.toList());
            int startIndex = yTiles.stream().map(MonkeyMapTile::getX).toList().get(0);
            yCircularPaths.put(y, new CircularMonkeyMapPath(yTiles, startIndex));
            if (y == 1) {
                firstRowStartX = startIndex;
            }
        }
        int maxCol = monkeyMapTiles.values().stream().map(MonkeyMapTile::getX).sorted().toList().get(monkeyMapTiles.values().size() - 1);
        for (int x = 1; x <= maxCol; x++) {
            final int xComp = x;
            List<MonkeyMapTile> xTiles = monkeyMapTiles.values().stream().filter(t -> t.getX() == xComp).sorted().collect(Collectors.toList());
            int startIndex = xTiles.stream().map(MonkeyMapTile::getY).toList().get(0);
            xCircularPaths.put(x, new CircularMonkeyMapPath(xTiles, startIndex));
        }
        position = new Position(firstRowStartX, 1, FacingDirection.Right);
    }

    public int getFinalPassword() {
        System.out.println(position);
        int password = (1000 * position.y) + (4 * position.x);
        switch(position.direction) {
            case Down: password++; break;
            case Left: password += 2; break;
            case Up: password += 3; break;
        }
        return password;
    }

    public int getMaxCol() {
        return monkeyMapTiles.values().stream().map(MonkeyMapTile::getX).sorted().toList().get(monkeyMapTiles.values().size() - 1);
    }

    public int getMaxRow() {
        return monkeyMapTiles.values().stream().map(MonkeyMapTile::getY).sorted().toList().get(monkeyMapTiles.values().size() - 1);
    }

    public Collection<MonkeyMapTile> getTiles() {
        return monkeyMapTiles.values();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 1; y <= getMaxRow(); y++) {
            for (int x = 1; x <= getMaxCol(); x++) {
                MonkeyMapTile tile = monkeyMapTiles.get(x + "," + y);
                if (tile != null) {
                    sb.append(tile.getTile());
                }
                else {
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
