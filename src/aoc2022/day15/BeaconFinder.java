package aoc2022.day15;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BeaconFinder {
    private final Hashtable<Integer, List<Range>> sensorRanges;

    public BeaconFinder(List<Sensor> sensorList) {
        sensorRanges = collectSensorRanges(sensorList);
        combineSensorRanges();
    }

    public List<Range> getSensorRangeInRow(int row) {
        return sensorRanges.get(row);
    }

    public Beacon findDistressBeaconWithinRange(int minRow, int maxRow) {
        List<Integer> possibleRows = sensorRanges.keySet().stream().filter(x -> x >= minRow && x <= maxRow).collect(Collectors.toList());
        Position foundPosition = null;
        for (int possibleRow : possibleRows) {
            List<Range> rangesToCheck = sensorRanges.get(possibleRow);
            if (rangesToCheck.size() > 1) {
                foundPosition = new Position(rangesToCheck.get(0).getEnd() + 1, possibleRow);
                break;
            }
        }
        if (foundPosition != null) {
            return new Beacon(foundPosition);
        }
        return null;
    }

    private Hashtable<Integer, List<Range>> collectSensorRanges(List<Sensor> sensorList) {
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

    private void combineSensorRanges() {
        Set<Integer> rows = sensorRanges.keySet();
        for (int row : rows) {
            List<Range> rangesToCheck = sensorRanges.get(row);
            Collections.sort(rangesToCheck);

            Range range = null;
            List<Range> newRangeList = new ArrayList<>();
            for (int i = 0; i < rangesToCheck.size() - 1; i++) {
                if (range == null) {
                    range = rangesToCheck.get(i);
                }
                Range newRange = range.combineRange(rangesToCheck.get(i + 1));
                if (newRange != null) {
                    range = newRange;
                }
                else {
                    // add old sequence and start new one -- there was a skipped position
                    newRangeList.add(range);
                }
            }
            if (newRangeList.isEmpty() && range != null) {
                newRangeList.add(range);
            }
            sensorRanges.put(row, newRangeList);
        }
    }
}
