package aoc2022.day17;

import lib.Challenge;

import java.util.ArrayList;
import java.util.List;

public class Day17_PyroclasticFlow extends Challenge {
    public static void main(String[] args) {
        Day17_PyroclasticFlow day17 = new Day17_PyroclasticFlow();
        day17.doOneStarSolution();
        day17.doTwoStarSolution();
    }

    public Day17_PyroclasticFlow() {
        super("2022/day17-input.txt");
        parseFile();
    }

    private Chamber chamber;
    @Override
    protected void parseFile() {
        List<WindDirection> windDirections = new ArrayList<>();
        for (int i = 0; i < getFileContents().length(); i++) {
            char direction = getFileContents().charAt(i);
            windDirections.add(WindDirection.getFromCharacter(direction));
        }
        chamber = new Chamber(7, 2, 4, windDirections);
    }

    @Override
    public void doOneStarSolution() {
        final int max = 2022;
        for (int i = 0; i < max; i++) {
            chamber.simulateRockFall();
        }
        chamber.printChamber();
        System.out.println("Tower of rocks height (2022): " + (chamber.getMaxHeight() + 1));
    }

    @Override
    public void doTwoStarSolution() {
        while (!chamber.cycleFound()) {
            chamber.simulateRockFall();
        }
        System.out.println("Tower of rocks height (1 trillion): " + (chamber.getHeightAtRock(1000000000000L)));
    }
}
