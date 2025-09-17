package aoc2023.day8;

public class TreeNode {
    private String nodeName;
    private String leftNode;
    private String rightNode;


    public TreeNode(String line) {
        String[] lineParts = line.split("=");
        if (lineParts.length == 2) {
            this.nodeName = lineParts[0].trim();
            String[] nodeParts = lineParts[1].trim().substring(1, lineParts[1].trim().length() - 1).split(",");
            if (nodeParts.length == 2) {
                this.leftNode = nodeParts[0].trim();
                this.rightNode = nodeParts[1].trim();
            }
        }
    }

    public String getNodeName() {
        return nodeName;
    }

    public String getLeftNode() {
        return leftNode;
    }

    public String getRightNode() {
        return rightNode;
    }
}
