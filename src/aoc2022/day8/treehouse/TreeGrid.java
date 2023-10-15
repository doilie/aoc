package aoc2022.day8.treehouse;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TreeGrid {
    private int treeRowCount = 0;
    private int treeColumnCount = 0;
    private final List<Tree> allTrees = new ArrayList<>();
    private final Hashtable<Integer, List<Tree>> treeColumns = new Hashtable<>();
    private final Hashtable<Integer, List<Tree>> treeRows = new Hashtable<>();

    public void addTree(int size, int column, int row) {
        if (treeColumns.get(column) == null) {
            treeColumnCount++;
            treeColumns.put(column, new ArrayList<>());
        }
        if (treeRows.get(row) == null) {
            treeRowCount++;
            treeRows.put(row, new ArrayList<>());
        }
        Tree newTree = new Tree(size, column, row);
        treeColumns.get(column).add(newTree);
        treeRows.get(row).add(newTree);
        allTrees.add(newTree);
    }

    public List<Tree> getAllTrees() {
        return allTrees;
    }

    public boolean checkIfTreeIsVisible(Tree tree) {
        if (isEdge(tree)) {
            tree.getVisibility().setVisible();
        }
        else {
            List<Tree> sameRow = this.treeRows.get(tree.getLocation().getRow());
            tree.getVisibility().setFromLeft(isVisibleFromTreeSection(tree, getTreesBefore(tree, sameRow)));
            tree.getVisibility().setFromRight(isVisibleFromTreeSection(tree, getTreesAfter(tree, sameRow)));

            List<Tree> sameColumn = this.treeColumns.get(tree.getLocation().getColumn());
            tree.getVisibility().setFromTop(isVisibleFromTreeSection(tree, getTreesBefore(tree, sameColumn)));
            tree.getVisibility().setFromBottom(isVisibleFromTreeSection(tree, getTreesAfter(tree, sameColumn)));
        }

        return tree.getVisibility().isVisible();
    }

    public int getTreeScenicScore(Tree tree) {
        int scenicScore = 0;

        if (!isEdge(tree)) { // if a tree is on the edge, at least one of its viewing distances is 0
            List<Tree> sameRow = this.treeRows.get(tree.getLocation().getRow());
            List<Tree> treesFromLeft = getReversedList(getTreesBefore(tree, sameRow));
            int leftTrees = countTreesInView(tree, treesFromLeft);
            List<Tree> treesFromRight = getTreesAfter(tree, sameRow);
            int rightTrees = countTreesInView(tree, treesFromRight);
            List<Tree> sameColumn = this.treeColumns.get(tree.getLocation().getColumn());
            List<Tree> treesFromTop = getReversedList(getTreesBefore(tree, sameColumn));
            int topTrees = countTreesInView(tree, treesFromTop);
            List<Tree> treesFromBottom = getTreesAfter(tree, sameColumn);
            int bottomTrees = countTreesInView(tree, treesFromBottom);
            scenicScore = leftTrees * rightTrees * topTrees * bottomTrees;
        }

        return scenicScore;
    }

    private boolean isEdge(Tree tree) {
        return tree.getLocation().getRow() == 0 || tree.getLocation().getRow() == treeRowCount - 1 ||
                tree.getLocation().getColumn() == 0 || tree.getLocation().getColumn() == treeColumnCount - 1;
    }

    private boolean isVisibleFromTreeSection(Tree tree, List<Tree> treesToCompare) {
        return treesToCompare.stream().noneMatch(treeToCompare -> tree.getSize() <= treeToCompare.getSize());
    }

    private List<Tree> getTreesBefore(Tree tree, List<Tree> refList) {
        int treeIndexInRow = refList.indexOf(tree);
        return refList.subList(0, treeIndexInRow);
    }

    private List<Tree> getTreesAfter(Tree tree, List<Tree> refList) {
        int treeIndexInRow = refList.indexOf(tree);
        return refList.subList(treeIndexInRow + 1, refList.size());
    }

    private int countTreesInView(Tree tree, List<Tree> treesToCompare) {
        int treesInView = 0;
        for (Tree treeToCompare : treesToCompare) {
            treesInView++;
            if (treeToCompare.getSize() >= tree.getSize()) {
                break;
            }
        }
        return treesInView;
    }

    private List<Tree> getReversedList(List<Tree> origList) {
        return IntStream.iterate(origList.size() - 1, i -> i >= 0, i -> i - 1).mapToObj(origList::get).collect(Collectors.toList());
    }
}
