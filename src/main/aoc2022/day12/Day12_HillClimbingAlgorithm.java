package aoc2022.day12;

import aoc2022.day12.graph.Graph;
import aoc2022.day12.graph.Node;
import lib.Challenge;

public class Day12_HillClimbingAlgorithm extends Challenge {
    public static void main(String[] args) {
        Day12_HillClimbingAlgorithm day12 = new Day12_HillClimbingAlgorithm();
        day12.doOneStarSolution();
        day12.doTwoStarSolution();
    }

    public Day12_HillClimbingAlgorithm() {
        super("2022/day12-input.txt");
        parseFile();
    }

    private Graph graph;

    @Override
    protected void parseFile() {
        String[] lines = this.getFileContents().split("\n");
        graph = new Graph(lines);
    }

    @Override
    public void doOneStarSolution() {
        graph.calculateShortestPath(graph.getStartNode());
        System.out.println("Shortest path: " + graph.getEndNode().getDistance());
    }

    @Override
    public void doTwoStarSolution() {
        int shortestPath = Integer.MAX_VALUE;
        for (Node node : graph.getPossibleStartNodes()) {
            graph.calculateShortestPath(node);
            int distance = graph.getEndNode().getDistance();
            if (distance < shortestPath) {
                shortestPath = distance;
            }
        }
        System.out.println("Shortest path from any a: " + shortestPath);
    }

}
