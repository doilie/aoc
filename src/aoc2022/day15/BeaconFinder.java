package aoc2022.day15;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

public class BeaconFinder {
    private final Hashtable<Integer, List<Range>> sensorRanges;

    public BeaconFinder(List<Sensor> sensorList) {
        sensorRanges = collectSensorRanges(sensorList);
        combineSensorRanges();
    }

    public List<Range> getSensorRangeInRow(int row) {
        return sensorRanges.get(row);
    }

    public Hashtable<Integer, List<Range>> getSensorRanges() {
        return sensorRanges;
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
