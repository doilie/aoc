package impl.hill;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

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
        if (startNode != null) {
            startNode.setSource(startNode);
            startNode.setDistanceFromSource(0);
            buildPaths();
        }
    }

    private void buildPaths() {
        Set<Node> sourceNodes = new HashSet<>();
        sourceNodes.add(startNode);

        // process per source node to avoid marking shared path as visited
        Node nextSourceNode = getNodeWithLowestValue(sourceNodes);
        while (!sourceNodes.isEmpty()) {
            Set<Node> unvisitedNodes = new HashSet<>();
            // initialize source node as first unvisited node and remove from source node set
            unvisitedNodes.add(nextSourceNode);
            sourceNodes.remove(nextSourceNode);

            // track visited nodes here
            Set<Node> visitedNodes = new HashSet<>();

            // get next node from set
            Node node = getNodeWithLowestValue(unvisitedNodes);
            while (!unvisitedNodes.isEmpty() && node != endNode) {
                // get node possible neighbors
                List<String> possibleNeighbors = node.getPossibleNeighbors();
                // remove current node from unvisited nodes
                unvisitedNodes.remove(node);
                // for each possible neighbor
                for (String possibleNeighborLoc : possibleNeighbors) {
                    Node possibleNeighbor = this.nodes.get(possibleNeighborLoc);
                    // if neighbor not null and not yet in visited
                    if (possibleNeighbor != null && !visitedNodes.contains(possibleNeighbor)) {
                        // if value = to curr node value and is not a source node
                        if (possibleNeighbor.getValue() == node.getValue() && possibleNeighbor != possibleNeighbor.getSource()) {
                            // - add node to visited by neighbor
                            possibleNeighbor.setSource(node.getSource());
                            // - set distance from source = curr node distance + 1
                            possibleNeighbor.setDistanceFromSource(node.getDistanceFromSource() + 1);
                            // - add to unvisitedNodes
                            unvisitedNodes.add(possibleNeighbor);
                        }
                        // else if value = curr node value + 1
                        else if (possibleNeighbor.getValue() == node.getValue() + 1) {
                            // - add as destination from source with distance = current node distance from source + 1
                            node.getSource().addDestination(possibleNeighbor, node.getDistanceFromSource() + 1);
                            // - add to source nodes
                            sourceNodes.add(possibleNeighbor);
                        }
                        // else - do nothing
                    }
                }
                // set as visited node
                visitedNodes.add(node);
                // get next unvisited node
                node = getNodeWithLowestValue(unvisitedNodes);
            }
            nextSourceNode = getNodeWithLowestValue(sourceNodes);
        }
    }

    private Node getNodeWithLowestValue(Set<Node> unvisitedNodes) {
        Node lowestValueNode = null;
        int lowestValue = possibleNodeValues.length();
        for (Node node : unvisitedNodes) {
            if (node.getValue() < lowestValue) {
                lowestValue = node.getValue();
                lowestValueNode = node;
            }
        }
        return lowestValueNode;
    }

    public void printPaths(Node node) {
        Hashtable<Node, Integer> nodeDest = node.getDestinations();
        Set<Node> nodes = nodeDest.keySet();

        for (Node destNode : nodes) {
//            System.out.print(possibleNodeValues.charAt(node.getValue()) + " (" + node.getName() + ")");
            System.out.print(possibleNodeValues.charAt(node.getValue()));
            int distance = nodeDest.get(destNode);
            IntStream.range(0, distance).mapToObj(i -> "-").forEach(System.out::print);
            if (!destNode.getDestinations().isEmpty()) {
                printPaths(destNode);
            }
            else {
//                System.out.println(possibleNodeValues.charAt(destNode.getValue()) + " (" + destNode.getName() + ")");
                System.out.println(possibleNodeValues.charAt(destNode.getValue()));
            }
        }
    }

    public Node getStartNode() {
        return startNode;
    }
}
