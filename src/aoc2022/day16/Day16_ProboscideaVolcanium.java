package aoc2022.day16;

import lib.Challenge;

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
    }

    @Override
    public void doOneStarSolution() {
        Map.Entry<String, Integer> maxPressure = valveGraph.getMaxPressureToRelease("AA", 30);
        System.out.println(maxPressure.getKey() + ": " + maxPressure.getValue());
    }

    @Override
    public void doTwoStarSolution() {

    }
}
