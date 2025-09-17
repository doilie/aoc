package aoc2022.day19;

public class Robot {
    private int oreCost = 0;
    private int clayCost = 0;
    private int obsidianCost = 0;
    private int oreRobotsBuilt = 0;
    private int clayRobotsBuilt = 0;
    private int obsidianRobotsBuilt = 0;
    private int geodeRobotsBuilt = 0;

    private Robot() {}

    public int getOreCost() {
        return oreCost;
    }

    public int getClayCost() {
        return clayCost;
    }

    public int getObsidianCost() {
        return obsidianCost;
    }

    public int getOreRobotsBuilt() {
        return oreRobotsBuilt;
    }

    public int getClayRobotsBuilt() {
        return clayRobotsBuilt;
    }

    public int getObsidianRobotsBuilt() {
        return obsidianRobotsBuilt;
    }

    public int getGeodeRobotsBuilt() {
        return geodeRobotsBuilt;
    }

    public static Robot buildOreRobot(int oreCost) {
        Robot oreRobot = new Robot();
        oreRobot.oreCost = oreCost;
        oreRobot.oreRobotsBuilt = 1;
        return oreRobot;
    }

    public static Robot buildClayRobot(int oreCost) {
        Robot clayRobot = new Robot();
        clayRobot.oreCost = oreCost;
        clayRobot.clayRobotsBuilt = 1;
        return clayRobot;
    }

    public static Robot buildObsidianRobot(int oreCost, int clayCost) {
        Robot obsidianRobot = new Robot();
        obsidianRobot.oreCost = oreCost;
        obsidianRobot.clayCost = clayCost;
        obsidianRobot.obsidianRobotsBuilt = 1;
        return obsidianRobot;
    }

    public static Robot buildGeodeRobot(int oreCost, int obsidianCost) {
        Robot geodeRobot = new Robot();
        geodeRobot.oreCost = oreCost;
        geodeRobot.obsidianCost = obsidianCost;
        geodeRobot.geodeRobotsBuilt = 1;
        return geodeRobot;
    }
}
