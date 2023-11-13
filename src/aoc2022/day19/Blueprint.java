package aoc2022.day19;

public class Blueprint {
    private final int id;
    private final Robot oreRobot;
    private final Robot clayRobot;
    private final Robot obsidianRobot;
    private final Robot geodeRobot;
    private final int maxOre;
    private final int maxClay;
    private final int maxObsidian;

    public Blueprint(int id, Robot oreRobot, Robot clayRobot, Robot obsidianRobot, Robot geodeRobot) {
        this.id = id;
        this.oreRobot = oreRobot;
        this.clayRobot = clayRobot;
        this.obsidianRobot = obsidianRobot;
        this.geodeRobot = geodeRobot;

        maxOre = Math.max(Math.max(Math.max(oreRobot.getOreCost(), clayRobot.getOreCost()), obsidianRobot.getOreCost()), geodeRobot.getOreCost());
        maxClay = obsidianRobot.getClayCost();
        maxObsidian = geodeRobot.getObsidianCost();
    }

    public static Blueprint parseLine(String line) {
        final int idIdx = 1;
        final int oreRobotCostIdx = 6;
        final int clayRobotCostIdx = 12;
        final int obsidianRobotCostOrePartIdx = 18;
        final int obsidianRobotCostClayPartIdx = 21;
        final int geodeRobotCostOrePartIdx = 27;
        final int geodeRobotCostObsidianPartIdx = 30;
        final int partsLength = 32;

        String[] parts = line.split("\\s");
        if (parts.length == partsLength) {
            int id = Integer.parseInt(parts[idIdx].substring(0, parts[idIdx].length() - 1));
            return new Blueprint(
                    id,
                    Robot.buildOreRobot(Integer.parseInt(parts[oreRobotCostIdx])),
                    Robot.buildClayRobot(Integer.parseInt(parts[clayRobotCostIdx])),
                    Robot.buildObsidianRobot(Integer.parseInt(parts[obsidianRobotCostOrePartIdx]), Integer.parseInt(parts[obsidianRobotCostClayPartIdx])),
                    Robot.buildGeodeRobot(Integer.parseInt(parts[geodeRobotCostOrePartIdx]), Integer.parseInt(parts[geodeRobotCostObsidianPartIdx]))
            );
        }

        return null;
    }

    public int getId() {
        return id;
    }

    public Robot getOreRobot() {
        return oreRobot;
    }

    public Robot getClayRobot() {
        return clayRobot;
    }

    public Robot getObsidianRobot() {
        return obsidianRobot;
    }

    public Robot getGeodeRobot() {
        return geodeRobot;
    }

    public int getMaxOre() {
        return maxOre;
    }

    public int getMaxClay() {
        return maxClay;
    }

    public int getMaxObsidian() {
        return maxObsidian;
    }
}
