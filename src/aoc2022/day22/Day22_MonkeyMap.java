package aoc2022.day22;

import lib.Challenge;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Day22_MonkeyMap extends Challenge {
    public static void main(String[] args) {
        Day22_MonkeyMap day22 = new Day22_MonkeyMap();
        day22.doOneStarSolution();
        day22.doTwoStarSolution();
    }

    public Day22_MonkeyMap() {
        super("2022/day22-input.txt");
        parseFile();
    }

    private MonkeyMap monkeyMap;
    private List<MonkeyTileMove> monkeyTileMoves;

    @Override
    protected void parseFile() {
        monkeyMap = new MonkeyMap();
        monkeyTileMoves = new ArrayList<>();
        String[] lines = getFileContents().split("\\n");
        for (int y = 1; y <= lines.length; y++) {
            String line = lines[y - 1];
            if (line.isEmpty()) {
                break;
            }
            monkeyMap.addTile(line, y);
        }
        monkeyMap.setRanges();
        monkeyTileMoves.addAll(MonkeyTileMove.parseMoves(lines[lines.length - 1]));
        System.out.println(monkeyMap);
    }

    @Override
    public void doOneStarSolution() {
        parseFile();
        for (MonkeyTileMove move : monkeyTileMoves) {
            System.out.println("--------");
            monkeyMap.movePosition(move);
            System.out.println(monkeyMap);
        }
        System.out.println("Final password: " + monkeyMap.getFinalPassword());
    }

    @Override
    public void doTwoStarSolution() {
        parseFile();
        MonkeyMapCube cube = new MonkeyMapCube(monkeyMap, MonkeyMapCube.CubeMode.Sample);
        for (MonkeyTileMove move : monkeyTileMoves) {
            System.out.println("--------");
            cube.movePosition(move);
            System.out.println(monkeyMap);
        }
    }

}
