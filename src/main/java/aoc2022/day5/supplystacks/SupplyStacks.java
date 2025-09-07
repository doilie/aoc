package aoc2022.day5.supplystacks;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SupplyStacks {
    private List<Stack<String>> supplyStacks = new ArrayList<>();
    private List<SupplyStackMoves> moves = new ArrayList<>();

    public void createNewStack() {
        this.supplyStacks.add(new Stack<>());
    }

    public void addToStack(int index, String crate) {
        if (this.supplyStacks.get(index) != null) {
            this.supplyStacks.get(index).push(crate);
        }
    }

    public void addMove(int numElements, int srcStack, int destStack) {
        moves.add(new SupplyStackMoves(numElements, srcStack, destStack));
    }

    public void moveCratesOneByOne() {
        for (SupplyStackMoves move : moves) {
            for (int i = 0; i < move.getNumElements(); i++) {
                String crate = this.supplyStacks.get(move.getSrcStack()).pop();
                this.supplyStacks.get(move.getDestStack()).push(crate);
            }
        }
    }

    public void moveCratesByBatch() {
        for (SupplyStackMoves move : moves) {
            Stack<String> batch = new Stack<>();
            for (int i = 0; i < move.getNumElements(); i++) {
                String crate = this.supplyStacks.get(move.getSrcStack()).pop();
                batch.push(crate);
            }
            while (!batch.isEmpty()) {
                this.supplyStacks.get(move.getDestStack()).push(batch.pop());
            }
        }
    }

    public String getTopCrates() {
        StringBuilder topCrates = new StringBuilder();
        for (Stack<String> supplyStack : supplyStacks) {
            topCrates.append(supplyStack.peek());
        }
        return topCrates.toString();
    }

    public void reset() {
        supplyStacks = new ArrayList<>();
        moves = new ArrayList<>();
    }
}
