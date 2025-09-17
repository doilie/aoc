package aoc2023.day10;

import lib.Challenge;

import java.util.List;

public class Day10_PipeMaze extends Challenge {
    public static void main(String[] args)  {
        Day10_PipeMaze day10 = new Day10_PipeMaze();
        day10.doOneStarSolution();
        day10.doTwoStarSolution();
    }

    public Day10_PipeMaze() {
        super("2023/day10-input.txt");
        this.parseFile();
    }

    private PipeLine pipeLine;

    @Override
    public void parseFile() {
        String[] lines = this.getFileContents().split("\n");
        pipeLine = new PipeLine(lines);
    }

    @Override
    public void doOneStarSolution() {
        List<Pipe> pipePath = pipeLine.getPipePath();
        System.out.println("Farthest distance from starting position: " + (pipePath.size() / 2));
    }

    @Override
    public void doTwoStarSolution() {
        List<Pipe> areaInsidePipePath = pipeLine.getAreaInsidePipePath();
        System.out.println("Area inside pipe path: " + areaInsidePipePath.size());

    }
}
