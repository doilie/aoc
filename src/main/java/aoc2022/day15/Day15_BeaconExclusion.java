package aoc2022.day15;

import lib.Challenge;

import java.util.ArrayList;
import java.util.List;

public class Day15_BeaconExclusion extends Challenge {
    public static void main(String[] args) {
        Day15_BeaconExclusion day15 = new Day15_BeaconExclusion();
        day15.doOneStarSolution();
        day15.doTwoStarSolution();
    }

    private BeaconFinder beaconFinder;

    public Day15_BeaconExclusion() {
        super("2022/day15-input.txt");
        parseFile();
    }

    @Override
    protected void parseFile() {
        List<Sensor> sensorList = new ArrayList<>();
        String[] lines = this.getFileContents().split("\n");
        for (String line : lines) {
            sensorList.add(new Sensor(line));
        }
        beaconFinder = new BeaconFinder(sensorList);
    }

    @Override
    public void doOneStarSolution() {
        List<Range> sensorRangeInRow = beaconFinder.getSensorRangeInRow(2000000);
        System.out.println("Positions that cannot contain a beacon in y = 2000000: " +
                sensorRangeInRow.stream().mapToInt(range -> range.getEnd() - range.getStart()).sum());
    }

    @Override
    public void doTwoStarSolution() {
        Beacon distressBeacon = beaconFinder.findDistressBeaconWithinRange(0, 4000000);
        if (distressBeacon != null) {
            System.out.println("Tuning frequency of distress beacon " +
                    "(x = " + distressBeacon.getPosition().getX() + ", y = " + distressBeacon.getPosition().getY() + "): " +
                    distressBeacon.getTuningFrequency());
        }
    }
}
