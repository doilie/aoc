package aoc2022.day15;

import lib.Challenge;

import java.util.*;
import java.util.stream.Collectors;

public class Day15_BeaconExclusion extends Challenge {
    public static void main(String[] args) {
        Day15_BeaconExclusion day15 = new Day15_BeaconExclusion();
        day15.doOneStarSolution();
        day15.doTwoStarSolution();
    }

    public Day15_BeaconExclusion() {
        super("2022/day15-input.txt");
        parseFile();
    }

    private final List<Sensor> sensorList = new ArrayList<>();
    private final Hashtable<Integer, List<Range>> sensorRanges = new Hashtable<>();

    @Override
    protected void parseFile() {
        String[] lines = this.getFileContents().split("\n");
        for (String line : lines) {
            sensorList.add(new Sensor(line));
        }
        sensorRanges.putAll(getSensorRanges());
    }

    @Override
    public void doOneStarSolution() {
        List<Range> rangesToCombine = sensorRanges.get(2000000);
        Collections.sort(rangesToCombine);
        for (int i = 0; i < rangesToCombine.size() - 1; i++) {
            Range range = rangesToCombine.get(i).combineRange(rangesToCombine.get(i + 1));
            if (range != null) {
                rangesToCombine.set(i + 1, range);
            }
        }
        Range lastRange = rangesToCombine.get(rangesToCombine.size() - 1);
        int positionsWithNoBeacon = lastRange.getEnd() - lastRange.getStart();
        System.out.println("Positions that cannot contain a beacon in y=2000000: " + positionsWithNoBeacon);
    }

    @Override
    public void doTwoStarSolution() {
        List<Integer> possibleRows = sensorRanges.keySet().stream().filter(x -> x >= 0 && x <= 4000000).collect(Collectors.toList());
        Position foundPosition = null;
        for (int possibleRow : possibleRows) {
            List<Range> rangesToCheck = sensorRanges.get(possibleRow);
            Collections.sort(rangesToCheck);
            for (int i = 0; i < rangesToCheck.size() - 1; i++) {
                Range range = rangesToCheck.get(i).combineRange(rangesToCheck.get(i + 1));
                if (range != null) {
                    rangesToCheck.set(i + 1, range);
                }
                else {
                    foundPosition = new Position(rangesToCheck.get(i).getEnd() + 1, possibleRow);
                    System.out.println("Distress beacon: x = " + foundPosition.getX() + ", y = " + foundPosition.getY());
                    break;
                }
            }
            if (foundPosition != null) {
                break;
            }
        }
        if (foundPosition != null) {
            long tuningFrequency = (foundPosition.getX() * 4000000L) + foundPosition.getY();
            System.out.println("Tuning frequency of distress beacon: " + tuningFrequency);
        }
    }

    private Hashtable<Integer, List<Range>> getSensorRanges() {
        Hashtable<Integer, List<Range>> sensorRanges = new Hashtable<>();
        for (Sensor sensor : sensorList) {
            Hashtable<Integer, Range> sensorRange = sensor.getSensorRange();
            Set<Integer> rows = sensorRange.keySet();
            for (int row : rows) {
                List<Range> rowRangeList = sensorRanges.get(row);
                if (rowRangeList == null) {
                    rowRangeList = new ArrayList<>();
                }
                rowRangeList.add(sensorRange.get(row));
                sensorRanges.put(row, rowRangeList);
            }
        }
        return sensorRanges;
    }
}
