package aoc2022.day12.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Node {
    private final static String possibleNodeValues = "abcdefghijklmnopqrstuvwxyz";
    private final int value;
    private final int row;
    private final int col;
    private final Set<Node> destinations = new HashSet<>();
    private List<Node> shortestPath = new LinkedList<>();
    private Integer distance = Integer.MAX_VALUE;

    public Node(String value, int row, int col) {
        this.value = possibleNodeValues.indexOf(value);
        this.row = row;
        this.col = col;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return row + "," + col;
    }

    public List<String> getPossibleNeighborIndices() {
        int nodeToTopIdx = this.row - 1;
        int nodeToBottomIdx = this.row + 1;
        int nodeToLeftIdx = this.col - 1;
        int nodeToRightIdx = this.col + 1;

        return new ArrayList<>(Arrays.asList(buildRowColKey(nodeToTopIdx, col),
                buildRowColKey(nodeToBottomIdx, col),
                buildRowColKey(row, nodeToLeftIdx),
                buildRowColKey(row, nodeToRightIdx)));
    }

    public void addDestination(Node destinationNode) {
        this.destinations.add(destinationNode);
    }

    public Set<Node> getDestinations() {
        return destinations;
    }

    public List<Node> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(List<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public static String buildRowColKey(int row, int col) {
        return row + "," + col;
    }
}
