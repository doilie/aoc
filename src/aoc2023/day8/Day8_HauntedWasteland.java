package aoc2023.day8;

import lib.Challenge;

import java.util.*;

public class Day8_HauntedWasteland extends Challenge {
    public static void main(String[] args)  {
        Day8_HauntedWasteland day8 = new Day8_HauntedWasteland();
        day8.doOneStarSolution();
        day8.doTwoStarSolution();
    }

    public Day8_HauntedWasteland() {
        super("2023/day8-input.txt");
        this.parseFile();
    }

    private final HashMap<String, TreeNode> treeNodes = new HashMap<>();
    private final List<String> directions = new ArrayList<>();

    @Override
    public void parseFile() {
        String[] lines = this.getFileContents().split("\\n");
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (i == 0) {
                directions.addAll(Arrays.stream(line.split("")).toList());
            }
            if (!line.contains("=")) {
                continue;
            }
            TreeNode treeNode = new TreeNode(line);
            treeNodes.put(treeNode.getNodeName(), treeNode);
        }
    }

    @Override
    public void doOneStarSolution() {
        System.out.println("Steps to reach ZZZ: " + getStepsBetweenNodes(treeNodes.get("AAA"), treeNodes.get("ZZZ")));
    }

    @Override
    public void doTwoStarSolution() {
        List<TreeNode> nodesEndingWithA = treeNodes.values().stream().filter(v -> v.getNodeName().endsWith("A")).toList();
        List<TreeNode> nodesEndingWithZ = treeNodes.values().stream().filter(v -> v.getNodeName().endsWith("Z")).toList();
        List<Integer> stepsForEachPath = new ArrayList<>();

        for (TreeNode a : nodesEndingWithA) {
            for (TreeNode z : nodesEndingWithZ) {
                int steps = getStepsBetweenNodes(a, z);
                if (steps != -1) {
//                    System.out.println("Steps to reach " + a.getNodeName() + " to " + z.getNodeName() + ": " + steps);
                    stepsForEachPath.add(steps);
                }
            }
        }
        System.out.println("Steps to reach all ending with Z from ending with A: " + getLCM(stepsForEachPath));
    }

    private int getStepsBetweenNodes(TreeNode source, TreeNode destination) {
        int steps = 0;
        int directionCtr = 0;
        int tooMuchSteps = 25000;
        TreeNode currentNode = source;
        while (!currentNode.getNodeName().equals(destination.getNodeName())) {
            steps++;
            String direction = directions.get(directionCtr);
            if (direction.equals("L")) {
                currentNode = treeNodes.get(currentNode.getLeftNode());
            }
            else {
                currentNode = treeNodes.get(currentNode.getRightNode());
            }
            directionCtr = (directionCtr + 1) % directions.size();
            if (steps == tooMuchSteps) {
                steps = -1;
                break;
            }
        }
        return steps;
    }

    private static long getLCM(List<Integer> numbers) {
        Collections.sort(numbers);
        int maxNumber = numbers.get(numbers.size() - 1);
        long currentLCM = numbers.get(numbers.size() - 1);
        int allDivisibleCtr = 0;
        while (allDivisibleCtr < numbers.size()) {
            allDivisibleCtr = 0;
            for (int number : numbers) {
                if (currentLCM % number == 0) {
                    allDivisibleCtr++;
                }
                else {
                    break;
                }
            }
            currentLCM += maxNumber;
        }
        return currentLCM;
    }
}
