package aoc2022.day22;

import java.util.*;
import java.util.stream.Collectors;

import static aoc2022.day22.MonkeyMapTile.WallTile;

public class MonkeyMapCube {
    private static class CubeTile {
        MonkeyMapTile tile;
        FacingDirection direction;
        CubeTile(MonkeyMapTile tile, FacingDirection direction) {
            this.tile = tile;
            this.direction = direction;
        }
    }
    private final Hashtable<Integer, MonkeyMapCubeFace> cubeFaces = new Hashtable<>();
    private final MonkeyMap map;
    private final Position position = new Position(1, 1, FacingDirection.Right);
    private int currentCubeFace = 1;
    private final int cubeFaceSize;

    public MonkeyMapCube(MonkeyMap map, CubeMode mode) {
        this.map = map;
        cubeFaceSize = findGCF(map.getMaxCol(), map.getMaxRow());
        buildCubeFaces(mode.cubeDirections);
        buildCubeFaceTiles();
    }

    public void movePosition(MonkeyTileMove move) {
        MonkeyMapTile currentTile = cubeFaces.get(currentCubeFace).getTile(position.x, position.y);
        List<CubeFaceDirection> sidesTraversed = getSidesTraversed(new CubeFaceDirection(currentCubeFace, position.direction));
        System.out.println(sidesTraversed);

        int refX = MonkeyMapTile.getCubeFaceCoordinate(position.x, cubeFaceSize);;
        int refY = MonkeyMapTile.getCubeFaceCoordinate(position.y, cubeFaceSize);;
        FacingDirection refDirection = position.direction;
        List<CubeTile> tileRowAroundCube = new ArrayList<>();
        int currentTileIdx = -1;
        for (CubeFaceDirection cubeSide : sidesTraversed) {
            switch(refDirection) {
                case Left:
                    switch(cubeSide.facingDirection) {
                        case Up:
                            refDirection = FacingDirection.Up;
                            refX = cubeFaceSize - refY + 1;
                            break;
                        case Down:
                            refDirection = FacingDirection.Down;
                            refX = refY;
                            break;
                        case Right:
                            refDirection = FacingDirection.Right;
                            refY = cubeFaceSize - refY + 1;
                            refX = 1;
                            break;
                    }
                    break;
                case Right:
                    switch(cubeSide.facingDirection) {
                        case Up:
                            refDirection = FacingDirection.Up;
                            refX = refY;
                            break;
                        case Down:
                            refDirection = FacingDirection.Down;
                            refX = cubeFaceSize - refY + 1;
                            break;
                        case Left:
                            refDirection = FacingDirection.Left;
                            refY = cubeFaceSize - refY + 1;
                            refX = cubeFaceSize;
                            break;
                    }
                    break;
                case Up:
                    switch(cubeSide.facingDirection) {
                        case Left:
                            refDirection = FacingDirection.Left;
                            refY = cubeFaceSize - refX + 1;
                            break;
                        case Right:
                            refDirection = FacingDirection.Right;
                            refY = refX;
                            break;
                        case Down:
                            refDirection = FacingDirection.Down;
                            refX = cubeFaceSize - refX + 1;
                            refY = 1;
                            break;
                    }
                    break;
                case Down:
                    switch(cubeSide.facingDirection) {
                        case Left:
                            refDirection = FacingDirection.Left;
                            refY = refX;
                            break;
                        case Right:
                            refDirection = FacingDirection.Right;
                            refY = cubeFaceSize - refX + 1;
                            break;
                        case Up:
                            refDirection = FacingDirection.Up;
                            refX = cubeFaceSize - refX + 1;
                            refY = cubeFaceSize;
                            break;
                    }
                    break;
            }
            MonkeyMapCubeFace cubeFace = cubeFaces.get(cubeSide.index);
            switch(refDirection) {
                case Right:
                    for (int x = 1; x <= cubeFaceSize; x++) {
                        if (cubeFace.getTile(x, refY) == currentTile) {
                            currentTileIdx = tileRowAroundCube.size();
                        }
                        tileRowAroundCube.add(new CubeTile(cubeFace.getTile(x, refY), refDirection));
                    }
                    break;
                case Left:
                    for (int x = cubeFaceSize; x >= 1; x--) {
                        tileRowAroundCube.add(new CubeTile(cubeFace.getTile(x, refY), refDirection));
                    }
                    break;
                case Down:
                    for (int y = 1; y <= cubeFaceSize; y++) {
                        tileRowAroundCube.add(new CubeTile(cubeFace.getTile(refX, y), refDirection));
                    }
                    break;
                case Up:
                    for (int y = cubeFaceSize; y >= 1; y--) {
                        tileRowAroundCube.add(new CubeTile(cubeFace.getTile(refX, y), refDirection));
                    }
                    break;
            }
        }

        List<CubeTile> reorderedTileRowAroundCube = new ArrayList<>(tileRowAroundCube.subList(currentTileIdx, tileRowAroundCube.size()));
        reorderedTileRowAroundCube.addAll(tileRowAroundCube.subList(0, currentTileIdx));

        System.out.println(reorderedTileRowAroundCube);

        int ctr = 0;
        CubeTile lastTileVisited = reorderedTileRowAroundCube.get(ctr++);
        while (lastTileVisited != null && !lastTileVisited.tile.getTile().equals(WallTile) && ctr < move.getSteps()) {
            lastTileVisited = reorderedTileRowAroundCube.get(ctr++);
        }

        if (lastTileVisited != null) {
            position.x = lastTileVisited.tile.getX();
            position.y = lastTileVisited.tile.getY();
            position.direction = MonkeyMap.getNewDirection(lastTileVisited.direction, move.getTurn());
        }


        // TODO: get actual tiles passed, create indicator of index of when direction was changed
//        CircularMonkeyMapPath.PathDirection pathDirection = getPathDirection();
//        List<Integer> visitedTileIndices;
//        if (pathDirection != null) {
//            switch(position.direction) {
//                case Right:
//                case Left:
//                    visitedTileIndices = yCircularPaths.get(position.y).moveUntilWall(position.x, move.getSteps(), pathDirection);
//                    for (int x : visitedTileIndices) {
//                        monkeyMapTiles.put(x + "," + position.y, new MonkeyMapTile(x, position.y, position.direction.symbol));
//                    }
//                    position.x = visitedTileIndices.get(visitedTileIndices.size() - 1);
//                    break;
//                case Down:
//                case Up:
//                    visitedTileIndices = xCircularPaths.get(position.x).moveUntilWall(position.y, move.getSteps(), pathDirection);
//                    for (int y : visitedTileIndices) {
//                        monkeyMapTiles.put(position.x + "," + y, new MonkeyMapTile(position.x, y, position.direction.symbol));
//                    }
//                    position.y = visitedTileIndices.get(visitedTileIndices.size() - 1);
//                    break;
//            }
//            position.direction = getNewDirection(move.getTurn());
//        }
    }



    public List<CubeFaceDirection> getSidesTraversed(CubeFaceDirection source) {
        MonkeyMapCubeFace cubeFace = cubeFaces.get(currentCubeFace);
        CubeFaceDirection currDirection = source;
        List<CubeFaceDirection> sidesTraversed = new ArrayList<>();
        sidesTraversed.add(currDirection);

        do {
            currDirection = cubeFace.getAdjacentFace(currDirection.facingDirection);
            cubeFace = cubeFaces.get(currDirection.index);
            if (!currDirection.equals(source)) {
                sidesTraversed.add(currDirection);
            }

        } while(!currDirection.equals(source));

        return sidesTraversed;
    }

    private void buildCubeFaces(Hashtable<CubeFaceDirection, CubeFaceDirection> directions) {
        Set<CubeFaceDirection> directionsKeyset = directions.keySet();
        for (CubeFaceDirection source : directionsKeyset) {
            CubeFaceDirection target = directions.get(source);
            int sourceIndex = source.index;
            MonkeyMapCubeFace face = cubeFaces.get(sourceIndex);
            if (face == null) {
                cubeFaces.put(sourceIndex, new MonkeyMapCubeFace(sourceIndex));
                face = cubeFaces.get(sourceIndex);
            }
            face.addAdjacentFace(target.index, source.facingDirection, target.facingDirection);
        }
    }

    private void buildCubeFaceTiles() {
        int numYDiv = map.getMaxRow() / cubeFaceSize;
        int index = 1;
        for (int yDiv = 0; yDiv < numYDiv; yDiv++) {
            int minY = 1 + (yDiv * cubeFaceSize);
            int maxY = minY + cubeFaceSize - 1;
            List<Hashtable<String, MonkeyMapTile>> yFaces = new ArrayList<>();
            for (int y = minY; y <= maxY; y++) {
                final int currY = y;
                List<MonkeyMapTile> yTiles = map.getTiles().stream().filter(t -> t.getY() == currY).sorted().collect(Collectors.toList());
                int numXDiv = yTiles.size() / cubeFaceSize;
                if (yFaces.isEmpty()) {
                    for (int xDiv = 0; xDiv < numXDiv; xDiv++) {
                        yFaces.add(new Hashtable<>());
                    }
                }
                for (int xDiv = 0; xDiv < numXDiv; xDiv++) {
                    Hashtable<String, MonkeyMapTile> currFace = yFaces.get(xDiv);
                    int minX = xDiv * cubeFaceSize;
                    int maxX = minX + cubeFaceSize;
                    List<MonkeyMapTile> tilesInFace =  yTiles.subList(minX, maxX);
                    tilesInFace.forEach(t -> currFace.put(t.getCubeFaceX(cubeFaceSize) + "," + t.getCubeFaceY(cubeFaceSize), t));
                }
            }
            for (Hashtable<String, MonkeyMapTile> yFace : yFaces) {
                cubeFaces.get(index++).setTiles(yFace);
            }
        }
    }

    private int getTileCubeIndex(MonkeyMapTile tile) {
        for (Map.Entry<Integer, MonkeyMapCubeFace> cubeFace : cubeFaces.entrySet()) {
            int index = cubeFace.getKey();
            MonkeyMapCubeFace face = cubeFace.getValue();
            if (face.containsTile(tile)) {
                return index;
            }
        }
        return 0;
    }

    private static int findGCF(int num1, int num2) {
        int startingDivisor = Math.min(num1, num2);
        int gcf = startingDivisor;
        for (int divisor = startingDivisor; divisor >= 1; divisor--) {
            if (num1 % divisor == 0 && num2 % divisor == 0) {
                gcf = divisor;
                break;
            }
        }
        return gcf;
    }

    private static Hashtable<CubeFaceDirection, CubeFaceDirection> getSampleCubeDirections() {
        Hashtable<CubeFaceDirection, CubeFaceDirection> directions = new Hashtable<>();
        // 1
        directions.put(new CubeFaceDirection(1, FacingDirection.Left), new CubeFaceDirection(3, FacingDirection.Down));
        directions.put(new CubeFaceDirection(1, FacingDirection.Up), new CubeFaceDirection(2, FacingDirection.Down));
        directions.put(new CubeFaceDirection(1, FacingDirection.Down), new CubeFaceDirection(4, FacingDirection.Down));
        directions.put(new CubeFaceDirection(1, FacingDirection.Right), new CubeFaceDirection(6, FacingDirection.Left));

        // 2
        directions.put(new CubeFaceDirection(2, FacingDirection.Up), new CubeFaceDirection(1, FacingDirection.Down));
        directions.put(new CubeFaceDirection(2, FacingDirection.Right), new CubeFaceDirection(3, FacingDirection.Right));
        directions.put(new CubeFaceDirection(2, FacingDirection.Down), new CubeFaceDirection(5, FacingDirection.Up));
        directions.put(new CubeFaceDirection(2, FacingDirection.Left), new CubeFaceDirection(6, FacingDirection.Up));

        // 3
        directions.put(new CubeFaceDirection(3, FacingDirection.Up), new CubeFaceDirection(1, FacingDirection.Right));
        directions.put(new CubeFaceDirection(3, FacingDirection.Left), new CubeFaceDirection(2, FacingDirection.Left));
        directions.put(new CubeFaceDirection(3, FacingDirection.Right), new CubeFaceDirection(4, FacingDirection.Right));
        directions.put(new CubeFaceDirection(3, FacingDirection.Down), new CubeFaceDirection(5, FacingDirection.Right));

        // 4
        directions.put(new CubeFaceDirection(4, FacingDirection.Left), new CubeFaceDirection(3, FacingDirection.Left));
        directions.put(new CubeFaceDirection(4, FacingDirection.Up), new CubeFaceDirection(1, FacingDirection.Up));
        directions.put(new CubeFaceDirection(4, FacingDirection.Down), new CubeFaceDirection(5, FacingDirection.Down));
        directions.put(new CubeFaceDirection(4, FacingDirection.Right), new CubeFaceDirection(6, FacingDirection.Down));

        // 5
        directions.put(new CubeFaceDirection(5, FacingDirection.Up), new CubeFaceDirection(4, FacingDirection.Up));
        directions.put(new CubeFaceDirection(5, FacingDirection.Right), new CubeFaceDirection(6, FacingDirection.Right));
        directions.put(new CubeFaceDirection(5, FacingDirection.Left), new CubeFaceDirection(3, FacingDirection.Up));
        directions.put(new CubeFaceDirection(5, FacingDirection.Down), new CubeFaceDirection(2, FacingDirection.Up));

        // 6
        directions.put(new CubeFaceDirection(6, FacingDirection.Left), new CubeFaceDirection(5, FacingDirection.Left));
        directions.put(new CubeFaceDirection(6, FacingDirection.Up), new CubeFaceDirection(4, FacingDirection.Left));
        directions.put(new CubeFaceDirection(6, FacingDirection.Right), new CubeFaceDirection(1, FacingDirection.Left));
        directions.put(new CubeFaceDirection(6, FacingDirection.Down), new CubeFaceDirection(2, FacingDirection.Right));

        return directions;
    }

    private static Hashtable<CubeFaceDirection, CubeFaceDirection> getActualCubeDirections() {
        // TODO change with actual
        Hashtable<CubeFaceDirection, CubeFaceDirection> directions = new Hashtable<>();
        // 1
        directions.put(new CubeFaceDirection(1, FacingDirection.Left), new CubeFaceDirection(3, FacingDirection.Down));
        directions.put(new CubeFaceDirection(1, FacingDirection.Up), new CubeFaceDirection(2, FacingDirection.Down));
        directions.put(new CubeFaceDirection(1, FacingDirection.Down), new CubeFaceDirection(4, FacingDirection.Down));
        directions.put(new CubeFaceDirection(1, FacingDirection.Right), new CubeFaceDirection(6, FacingDirection.Left));

        // 2
        directions.put(new CubeFaceDirection(2, FacingDirection.Up), new CubeFaceDirection(1, FacingDirection.Down));
        directions.put(new CubeFaceDirection(2, FacingDirection.Right), new CubeFaceDirection(3, FacingDirection.Right));
        directions.put(new CubeFaceDirection(2, FacingDirection.Down), new CubeFaceDirection(5, FacingDirection.Up));
        directions.put(new CubeFaceDirection(2, FacingDirection.Right), new CubeFaceDirection(6, FacingDirection.Right));

        // 3
        directions.put(new CubeFaceDirection(3, FacingDirection.Up), new CubeFaceDirection(3, FacingDirection.Right));
        directions.put(new CubeFaceDirection(3, FacingDirection.Left), new CubeFaceDirection(2, FacingDirection.Left));
        directions.put(new CubeFaceDirection(3, FacingDirection.Right), new CubeFaceDirection(4, FacingDirection.Right));
        directions.put(new CubeFaceDirection(3, FacingDirection.Down), new CubeFaceDirection(5, FacingDirection.Right));

        // 4
        directions.put(new CubeFaceDirection(4, FacingDirection.Left), new CubeFaceDirection(3, FacingDirection.Left));
        directions.put(new CubeFaceDirection(4, FacingDirection.Up), new CubeFaceDirection(1, FacingDirection.Up));
        directions.put(new CubeFaceDirection(4, FacingDirection.Down), new CubeFaceDirection(5, FacingDirection.Down));
        directions.put(new CubeFaceDirection(4, FacingDirection.Right), new CubeFaceDirection(6, FacingDirection.Down));

        // 5
        directions.put(new CubeFaceDirection(5, FacingDirection.Up), new CubeFaceDirection(4, FacingDirection.Up));
        directions.put(new CubeFaceDirection(5, FacingDirection.Right), new CubeFaceDirection(6, FacingDirection.Right));
        directions.put(new CubeFaceDirection(5, FacingDirection.Left), new CubeFaceDirection(3, FacingDirection.Up));
        directions.put(new CubeFaceDirection(5, FacingDirection.Down), new CubeFaceDirection(2, FacingDirection.Up));

        // 6
        directions.put(new CubeFaceDirection(6, FacingDirection.Left), new CubeFaceDirection(5, FacingDirection.Left));
        directions.put(new CubeFaceDirection(6, FacingDirection.Up), new CubeFaceDirection(4, FacingDirection.Left));
        directions.put(new CubeFaceDirection(6, FacingDirection.Right), new CubeFaceDirection(1, FacingDirection.Left));
        directions.put(new CubeFaceDirection(6, FacingDirection.Right), new CubeFaceDirection(2, FacingDirection.Right));

        return directions;
    }

    public static class CubeFaceDirection {
        int index;
        FacingDirection facingDirection;

        CubeFaceDirection(int index, FacingDirection facingDirection) {
            this.index = index;
            this.facingDirection = facingDirection;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj.getClass().equals(CubeFaceDirection.class)) {
                CubeFaceDirection cubeFaceDirection = (CubeFaceDirection) obj;
                return this.index == cubeFaceDirection.index && this.facingDirection == cubeFaceDirection.facingDirection;
            }
            return super.equals(obj);
        }
    }

    public enum CubeMode {
        Sample(getSampleCubeDirections()),
        Actual(getActualCubeDirections());

        final Hashtable<CubeFaceDirection, CubeFaceDirection> cubeDirections;

        CubeMode(Hashtable<CubeFaceDirection, CubeFaceDirection> cubeDirections) {
            this.cubeDirections = cubeDirections;
        }
    }
}
