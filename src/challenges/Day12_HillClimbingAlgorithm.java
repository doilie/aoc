package challenges;

import impl.hill.Graph;

public class Day12_HillClimbingAlgorithm extends Challenge {
    public static void main(String[] args) {
        Day12_HillClimbingAlgorithm day12 = new Day12_HillClimbingAlgorithm();
        day12.doOneStarSolution();
        day12.doTwoStarSolution();
    }

    public Day12_HillClimbingAlgorithm() {
        super("day12-input.txt");
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
        graph.calculateShortestPath();
        System.out.println("Shortest path: " + graph.getEndNode().getDistance());
    }

    @Override
    public void doTwoStarSolution() {
    }

}
