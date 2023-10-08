package impl.hill;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

public class Node {
    private final int value;
    private final int row;
    private final int col;
    private final Hashtable<Node, Integer> destinations = new Hashtable<>();
    private Node source;
    private int distanceFromSource;

    public Node(int value, int row, int col) {
        this.value = value;
        this.row = row;
        this.col = col;
    }

    public int getValue() {
        return value;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public String getName() {
        return row + "," + col;
    }

    public List<String> getPossibleNeighbors() {
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
        destinationNode.setSource(destinationNode);
    }

    public Hashtable<Node, Integer> getDestinations() {
        return destinations;
    }

    public Node getSource() {
        return source;
    }

    public void setSource(Node source) {
        this.source = source;
    }

    public int getDistanceFromSource() {
        return distanceFromSource;
    }

    public void setDistanceFromSource(int distanceFromSource) {
        this.distanceFromSource = distanceFromSource;
    }
}
