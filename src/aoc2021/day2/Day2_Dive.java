package aoc2021.day2;

import lib.Challenge;

public class Day2_Dive extends Challenge {
    public static void main(String[] args)  {
        Day2_Dive day2 = new Day2_Dive();
        day2.doOneStarSolution();
        day2.doTwoStarSolution();
    }


    public Day2_Dive() {
        super("2021/day2-input.txt");
        this.parseFile();
    }

    private final SubmarineCommandList submarineCommandList = new SubmarineCommandList();

    @Override
    public void parseFile() {
        String[] lines = this.getFileContents().split("\n");
        for (String line : lines) {
            submarineCommandList.addCommand(line);
        }
    }

    @Override
    public void doOneStarSolution() {
        SubmarinePosition position = new SubmarinePosition();
        submarineCommandList.performCommands(position);
        System.out.println("Final horizontal distance * final depth: " + (position.getHorizontalPosition() * position.getDepth()));
    }

    @Override
    public void doTwoStarSolution() {
        SubmarinePositionAim position = new SubmarinePositionAim();
        submarineCommandList.performCommands(position);
        System.out.println("Final horizontal distance * final depth with aim: " + (position.getHorizontalPosition() * position.getDepth()));
    }
}
