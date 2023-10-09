package impl.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Graph {
    private final Hashtable<String, Node> nodes = new Hashtable<>();
    private final Set<Node> possibleStartNodes = new HashSet<>();
    private Node startNode;
    private Node endNode;

    public Graph(String[] lines) {
        for (int row = 0; row < lines.length; row++) {
            String line = lines[row];
            for (int col = 0; col < line.length(); col++) {
                String currNode = Character.toString(line.charAt(col));
                Node node;
                if (currNode.equals("S")) {
                    node = new Node("a", row, col);
                    startNode = node;
                    nodes.putIfAbsent(node.getName(), node);
                } else if (currNode.equals("E")) {
                    node = new Node("z", row, col);
                    endNode = node;
                    nodes.putIfAbsent(node.getName(), node);
                } else {
                    node = new Node(currNode, row, col);
                    nodes.putIfAbsent(node.getName(), node);
                    if (node.getValue() == 0) {
                        possibleStartNodes.add(node);
                    }
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
                        node.addDestination(possibleNeighbor);
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

    public Set<Node> getPossibleStartNodes() {
        return possibleStartNodes;
    }

    public void calculateShortestPath(Node node) {
        nodes.values().forEach(n -> n.setDistance(Integer.MAX_VALUE));
        if (endNode != null) {
            node.setDistance(0);

            Set<Node> settledNodes = new HashSet<>();
            Set<Node> unsettledNodes = new HashSet<>();

            unsettledNodes.add(node);

            while (!unsettledNodes.isEmpty()) {
                Node currentNode = getLowestDistanceNode(unsettledNodes);
                unsettledNodes.remove(currentNode);
                for (Node destination : currentNode.getDestinations()) {
                    if (!settledNodes.contains(destination)) {
                        calculateMinimumDistance(destination, currentNode);
                        unsettledNodes.add(destination);
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

    private void calculateMinimumDistance(Node evaluationNode, Node sourceNode) {
        Integer sourceDistance = sourceNode.getDistance();
        if (sourceDistance + 1 < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + 1);
            LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }
}
