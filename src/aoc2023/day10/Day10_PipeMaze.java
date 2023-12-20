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

    private PipeLine graph;

    @Override
    public void parseFile() {
        String[] lines = this.getFileContents().split("\n");
        graph = new PipeLine(lines);
    }

    @Override
    public void doOneStarSolution() {
        List<Path> paths = graph.getPaths();
        if (paths != null && paths.size() == 2) {
            System.out.println("Farthest distance from starting position: " + (paths.get(0).getPipes().size() - 1));
        }
    }

    @Override
    public void doTwoStarSolution() {

    }
}
