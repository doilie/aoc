package aoc2019.day3;

import lib.Challenge;

import java.util.ArrayList;
import java.util.List;

public class Day3_CrossedWires extends Challenge {
    public static void main(String[] args) {
        Day3_CrossedWires day3 = new Day3_CrossedWires();
        day3.doOneStarSolution();
        day3.doTwoStarSolution();
    }

    public Day3_CrossedWires() {
        super("2019/day3-input.txt");
        this.parseFile();
    }

    private final List<WirePath> wirePaths = new ArrayList<>();

    @Override
    protected void parseFile() {
        String[] lines = this.getFileContents().split("\\n");
        for (String line : lines) {
            wirePaths.add(WirePath.createFromInstructions(line));
        }
    }

    @Override
    public void doOneStarSolution() {
        System.out.println("Manhattan distance from central port to closest intersection: " + WirePath.getClosestDistanceFromCentralPort(wirePaths));
    }

    @Override
    public void doTwoStarSolution() {
        System.out.println("Fewest combined steps from central port to intersection: " + WirePath.getFewestStepsToIntersection(wirePaths));
    }
}
