package aoc2023.day6;

import lib.Challenge;

import java.util.ArrayList;
import java.util.List;

public class Day6_WaitForIt extends Challenge {
    public static void main(String[] args)  {
        Day6_WaitForIt day6 = new Day6_WaitForIt();
        day6.doOneStarSolution();
        day6.doTwoStarSolution();
    }

    public Day6_WaitForIt() {
        super("2023/day6-input.txt");
    }

    @Override
    public void parseFile() {
    }

    @Override
    public void doOneStarSolution() {
        int productOfWaysToBeatRecord = getSeparateBoatRaces().stream().mapToInt(boatRace -> boatRace.getMillisecondsToBeatRecord().size()).reduce(1, (a, b) -> a * b);
        System.out.println("Product of ways to beat record: " + productOfWaysToBeatRecord);
    }

    @Override
    public void doTwoStarSolution() {
        System.out.println("Long race number of ways to beat record: " + getOneBigBoatRace().getMillisecondsToBeatRecord().size());
    }

    private List<BoatRace> getSeparateBoatRaces() {
        List<BoatRace> boatRaces = new ArrayList<>();
        String[] lines = this.getFileContents().split("\\n");
        List<Integer> times = new ArrayList<>();
        List<Integer> distances = new ArrayList<>();
        for (String line : lines) {
            String[] lineParts = line.split(":");
            if (lineParts.length == 2) {
                String[] recordedPart = lineParts[1].split("\\s+");
                if (line.startsWith("Time:")) {
                    for (String s : recordedPart) {
                        if (!s.isEmpty()) {
                            times.add(Integer.parseInt(s));
                        }
                    }
                }
                else if (line.startsWith("Distance:")) {
                    for (String s : recordedPart) {
                        if (!s.isEmpty()) {
                            distances.add(Integer.parseInt(s));
                        }
                    }
                }
            }
        }
        if (times.size() == distances.size()) {
            for (int i = 0; i < times.size(); i++) {
                boatRaces.add(new BoatRace(times.get(i), distances.get(i)));
            }
        }
        return boatRaces;
    }

    private BoatRace getOneBigBoatRace() {
        String[] lines = this.getFileContents().split("\\n");
        long time = 0;
        long distance = 0;
        for (String line : lines) {
            String[] lineParts = line.split(":");
            if (lineParts.length == 2) {
                String recordedPart = lineParts[1].replaceAll("\\s+", "");
                if (line.startsWith("Time:")) {
                    time = Long.parseLong(recordedPart);
                }
                else if (line.startsWith("Distance:")) {
                    distance = Long.parseLong(recordedPart);
                }
            }
        }
        return new BoatRace(time, distance);
    }
}
