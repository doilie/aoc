package aoc2022.day16;

import lib.Challenge;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class Day16_ProboscideaVolcanium extends Challenge {
    public static void main(String[] args) {
        Day16_ProboscideaVolcanium day16 = new Day16_ProboscideaVolcanium();
        day16.doOneStarSolution();
        day16.doTwoStarSolution();
    }

    private final ValveGraph valveGraph = new ValveGraph();

    public Day16_ProboscideaVolcanium() {
        super("2022/day16-input.txt");
        parseFile();
    }

    @Override
    protected void parseFile() {
        String[] lines = this.getFileContents().split("\n");
        for (String line : lines) {
            valveGraph.addValve(new Valve(line));
        }
        valveGraph.initializePressurePaths();
    }

    @Override
    public void doOneStarSolution() {
        Map.Entry<List<String>, Integer> maxPressureHumanPath = valveGraph.getMaxPressurePath(30);
        System.out.println("Max pressure path and value (human only): " + maxPressureHumanPath.getKey() + " - " + maxPressureHumanPath.getValue());
    }

    @Override
    public void doTwoStarSolution() {
        Hashtable<List<String>, Integer> maxPressureCollaborativePath = valveGraph.getMaxPressureToReleaseWithElephant(26);
        int totalPressure = 0;
        int count = 1;
        for (Map.Entry<List<String>, Integer> pressurePath : maxPressureCollaborativePath.entrySet()) {
            System.out.println("Max pressure path and value (" + count++ + "): " + pressurePath.getKey() + " - " + pressurePath.getValue());
            totalPressure += pressurePath.getValue();
        }
        System.out.println("Total pressure with elephant: " + totalPressure);

    }
}
