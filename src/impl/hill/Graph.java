package impl.hill;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Graph {
    private final static String possibleNodeValues = "abcdefghijklmnopqrstuvwxyz";
    private final Hashtable<String, Node> nodes = new Hashtable<>();
    private Node startNode;
    private Node endNode;
    public Graph(String[] lines) {
        for (int row = 0; row < lines.length; row++) {
            String line = lines[row];
            for (int col = 0; col < line.length(); col++) {
                String currNode = Character.toString(line.charAt(col));
                Node node;
                if (currNode.equals("S")) {
                    node = new Node(0, row, col);
                    startNode = node;
                    nodes.putIfAbsent(node.getName(), node);
                } else if (currNode.equals("E")) {
                    node = new Node(possibleNodeValues.length() - 1, row, col);
                    endNode = node;
                    nodes.putIfAbsent(node.getName(), node);
                } else {
                    node = new Node(possibleNodeValues.indexOf(currNode), row, col);
                    nodes.putIfAbsent(node.getName(), node);
                }
            }
        }
        buildPaths();
    }

    private void buildPaths() {
        List<Node> nodeList = new ArrayList<>(nodes.values());
        for (Node node : nodeList) {
            List<String> possibleNeighborKeys = node.getPossibleNeighborIndices();
            for (String possibleNeighborKey : possibleNeighborKeys) {
                Node possibleNeighbor = nodes.get(possibleNeighborKey);
                if (possibleNeighbor != null) {
                    int heightDiff = possibleNeighbor.getValue() - node.getValue();
                    if (heightDiff <= 1) {
                        node.addDestination(possibleNeighbor, 1);
                    }
                }
            }
        }
    }

    public Node getStartNode() {
        return startNode;
    }

    public Node getEndNode() {
        return endNode;
    }

    public void calculateShortestPath() {
        if (startNode != null && endNode != null) {
            startNode.setDistance(0);

            Set<Node> settledNodes = new HashSet<>();
            Set<Node> unsettledNodes = new HashSet<>();

            unsettledNodes.add(startNode);

            while (!unsettledNodes.isEmpty()) {
                Node currentNode = getLowestDistanceNode(unsettledNodes);
                unsettledNodes.remove(currentNode);
                for (Map.Entry<Node, Integer> adjacencyPair:
                        currentNode.getDestinations().entrySet()) {
                    Node adjacentNode = adjacencyPair.getKey();
                    Integer edgeWeight = adjacencyPair.getValue();
                    if (!settledNodes.contains(adjacentNode)) {
                        calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                        unsettledNodes.add(adjacentNode);
                    }
                }
                settledNodes.add(currentNode);
            }
        }
    }

    private Node getLowestDistanceNode(Set < Node > unsettledNodes) {
        Node lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Node node: unsettledNodes) {
            int nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }

    private void calculateMinimumDistance(Node evaluationNode, Integer edgeWeigh, Node sourceNode) {
        Integer sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }
}
