package aoc2022.day19;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BlueprintState implements Comparable<BlueprintState> {
    private final int minutes;
    private final int ore;
    private final int clay;
    private final int obsidian;
    private final int geode;
    private final int oreRobots;
    private final int clayRobots;
    private final int obsidianRobots;
    private final int geodeRobots;

    public BlueprintState(int minutes, int ore, int clay, int obsidian, int geode, int oreRobots, int clayRobots, int obsidianRobots, int geodeRobots) {
        this.minutes = minutes;
        this.ore = ore;
        this.clay = clay;
        this.obsidian = obsidian;
        this.geode = geode;
        this.oreRobots = oreRobots;
        this.clayRobots = clayRobots;
        this.obsidianRobots = obsidianRobots;
        this.geodeRobots = geodeRobots;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getGeode() {
        return geode;
    }

    public int getGeodeRobots() {
        return geodeRobots;
    }

    @Override
    public int compareTo(BlueprintState o) {
        Integer otherGeode = o.geode;
        Integer thisGeode = this.geode;
        return otherGeode.compareTo(thisGeode);
    }

    public List<BlueprintState> getNextSensibleStates(Blueprint blueprint) {
        List<BlueprintState> nextSensibleStates = new ArrayList<>();
        if (blueprint.getMaxOre() > oreRobots) {
            nextSensibleStates.add(nextWith(blueprint.getOreRobot()));
        }
        if (blueprint.getMaxClay() > clayRobots) {
            nextSensibleStates.add(nextWith(blueprint.getClayRobot()));
        }
        if (clayRobots > 0 && blueprint.getMaxObsidian() > obsidianRobots) {
            nextSensibleStates.add(nextWith(blueprint.getObsidianRobot()));
        }
        if (obsidianRobots > 0) {
            nextSensibleStates.add(nextWith(blueprint.getGeodeRobot()));
        }
        return nextSensibleStates.stream().filter(s -> s.minutes > 0).collect(Collectors.toList());
    }

    public BlueprintState nextWith(Robot robot) {
        int minutes = timeToBuild(robot);
        return new BlueprintState(
                this.minutes - minutes,
                ore + oreRobots * minutes - robot.getOreCost(),
                clay + clayRobots * minutes - robot.getClayCost(),
                obsidian + obsidianRobots * minutes - robot.getObsidianCost(),
                geode + geodeRobots * minutes,
                oreRobots + robot.getOreRobotsBuilt(),
                clayRobots + robot.getClayRobotsBuilt(),
                obsidianRobots + robot.getObsidianRobotsBuilt(),
                geodeRobots + robot.getGeodeRobotsBuilt());
    }

    public boolean hasPotentialToBeBetter(int best) {
        int potentialProduction = 0;
        for (int i = 0; i < minutes; i++) {
            potentialProduction += i + geodeRobots;
        }
        return geode + potentialProduction > best;
    }

    private int timeToBuild(Robot robot) {
        int remainingOre = Math.max(robot.getOreCost() - ore, 0);
        int remainingClay = Math.max(robot.getClayCost() - clay, 0);
        int remainingObsidian = Math.max(robot.getObsidianCost() - obsidian, 0);

        return Math.max(
                Math.max(
                    (int) Math.ceil(remainingOre / (float) oreRobots),
                    (int) Math.ceil(remainingClay / (float) clayRobots)),
                (int) Math.ceil(remainingObsidian / (float) obsidianRobots)
        ) + 1;
    }
}
