package aoc2022.day15;

import java.util.Hashtable;

public class Sensor {
    private Position position;
    private Beacon nearestBeacon;
    private final Hashtable<Integer, Range> sensorRange = new Hashtable<>();

    public Sensor(String input) {
        String[] lineParts = input.split(":");
        if (lineParts.length == 2) {
            position = Position.parseLine(lineParts[0]);
            nearestBeacon = new Beacon(lineParts[1]);
        }
        computeRange();
    }

    public Position getPosition() {
        return position;
    }

    private void computeRange() {
        int xDiff = Math.abs(nearestBeacon.getPosition().getX() - this.getPosition().getX());
        int yDiff = Math.abs(nearestBeacon.getPosition().getY() - this.getPosition().getY());
        int maxSensorRangeStrength = xDiff + yDiff;
        for (int i = 0; i < maxSensorRangeStrength; i++) {
            int rowUp = this.getPosition().getY() - i;
            int rowDown = this.getPosition().getY() + i;
            int startRowIdx = this.getPosition().getX() - maxSensorRangeStrength + i;
            int endRowIdx = this.getPosition().getX() + maxSensorRangeStrength - i;
            sensorRange.put(rowUp, new Range(startRowIdx, endRowIdx));
            sensorRange.put(rowDown, new Range(startRowIdx, endRowIdx));
        }
    }

    public Hashtable<Integer, Range> getSensorRange() {
        return sensorRange;
    }
}
