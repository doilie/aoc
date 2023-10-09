package impl.hill;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

public class Node {
    private final int value;
    private final int row;
    private final int col;
    private final Hashtable<Node, Integer> destinations = new Hashtable<>();
    private List<Node> shortestPath = new LinkedList<>();
    private Integer distance = Integer.MAX_VALUE;

    public Node(int value, int row, int col) {
        this.value = value;
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

    public static String buildRowColKey(int row, int col) {
        return row + "," + col;
    }

    public void addDestination(Node destinationNode, int distanceFromSource) {
        Integer savedDistanceFromSource = this.destinations.get(destinationNode);
        if (savedDistanceFromSource != null) {
            distanceFromSource = Math.min(distanceFromSource, savedDistanceFromSource);
        }
        this.destinations.put(destinationNode, distanceFromSource);
    }

    public Hashtable<Node, Integer> getDestinations() {
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
}
