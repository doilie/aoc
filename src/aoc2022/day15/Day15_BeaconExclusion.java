package aoc2022.day15;

import lib.Challenge;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        List<Range> rangesToCombine = beaconFinder.getSensorRangeInRow(2000000);
        Range lastRange = rangesToCombine.get(rangesToCombine.size() - 1);
        int positionsWithNoBeacon = lastRange.getEnd() - lastRange.getStart();
        System.out.println("Positions that cannot contain a beacon in y=2000000: " + positionsWithNoBeacon);
    }

    @Override
    public void doTwoStarSolution() {
        List<Integer> possibleRows = beaconFinder.getSensorRanges().keySet().stream().filter(x -> x >= 0 && x <= 4000000).collect(Collectors.toList());
        Position foundPosition = null;
        for (int possibleRow : possibleRows) {
            List<Range> rangesToCheck = beaconFinder.getSensorRanges().get(possibleRow);
            if (rangesToCheck.size() > 1) {
                foundPosition = new Position(rangesToCheck.get(0).getEnd() + 1, possibleRow);
                System.out.println("Distress beacon: x = " + foundPosition.getX() + ", y = " + foundPosition.getY());
                break;
            }
        }
        if (foundPosition != null) {
            long tuningFrequency = (foundPosition.getX() * 4000000L) + foundPosition.getY();
            System.out.println("Tuning frequency of distress beacon: " + tuningFrequency);
        }
    }
}
