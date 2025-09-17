package aoc2022.day8;

import aoc2022.day8.treehouse.Tree;
import aoc2022.day8.treehouse.TreeGrid;
import lib.Challenge;

public class Day8_TreetopTreeHouse extends Challenge {
    public static void main(String[] args) {
        Day8_TreetopTreeHouse day8 = new Day8_TreetopTreeHouse();
        day8.doOneStarSolution();
        day8.doTwoStarSolution();
    }

    private final TreeGrid treeGrid = new TreeGrid();

    public Day8_TreetopTreeHouse() {
        super("2022/day8-input.txt");
        parseFile();
    }

    @Override
    protected void parseFile() {
        String[] lines = this.getFileContents().split("\n");
        for (int row = 0; row < lines.length; row++) {
            String line = lines[row];
            for (int column = 0; column < line.length(); column++) {
                treeGrid.addTree(Integer.parseInt(Character.toString(line.charAt(column))), column, row);
            }
        }
    }

    @Override
    public void doOneStarSolution() {
        int count = (int) treeGrid.getAllTrees().stream().filter(treeGrid::checkIfTreeIsVisible).count();
        System.out.println("Visible tree count: " + count);
    }

    @Override
    public void doTwoStarSolution() {
        int maxScenicScore = 0;
        for (Tree tree : treeGrid.getAllTrees()) {
            int treeScenicScore = treeGrid.getTreeScenicScore(tree);
            if (treeScenicScore > maxScenicScore) {
                maxScenicScore = treeScenicScore;
            }
        }
        System.out.println("Max scenic score: " + maxScenicScore);
    }
}
