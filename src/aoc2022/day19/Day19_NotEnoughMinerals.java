package aoc2022.day19;

import lib.Challenge;

import java.util.ArrayList;
import java.util.List;

public class Day19_NotEnoughMinerals extends Challenge {
    public static void main(String[] args) {
        // credit to ClouddJR
        Day19_NotEnoughMinerals day19 = new Day19_NotEnoughMinerals();
        day19.doOneStarSolution();
        day19.doTwoStarSolution();
    }

    public Day19_NotEnoughMinerals() {
        super("2022/day19-input.txt");
        parseFile();
    }

    private final List<Blueprint> blueprints = new ArrayList<>();

    @Override
    protected void parseFile() {
        String[] lines = getFileContents().split("\\n");
        for (String line : lines) {
            blueprints.add(Blueprint.parseLine(line));
        }
    }

    @Override
    public void doOneStarSolution() {
        final int minutes = 24;

        int totalQuality = 0;
        for (Blueprint blueprint : blueprints) {
            BlueprintFactory factory = new BlueprintFactory(blueprint, minutes);
            int bestGeodes = factory.findBest();
            totalQuality += blueprint.getId() * bestGeodes;
        }

        System.out.println("Total Quality: " + totalQuality);
    }

    @Override
    public void doTwoStarSolution() {
        final int minutes = 32;

        int firstThreeMaxGeodesProduct = 1;
        for (int i = 0; i < 3; i++) {
            Blueprint blueprint = blueprints.get(i);
            BlueprintFactory factory = new BlueprintFactory(blueprint, minutes);
            int bestGeodes = factory.findBest();
            firstThreeMaxGeodesProduct *= bestGeodes;
        }

        System.out.println("First three blueprints max geodes product: " + firstThreeMaxGeodesProduct);
    }
}
