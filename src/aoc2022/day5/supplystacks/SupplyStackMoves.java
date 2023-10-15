package aoc2022.day5.supplystacks;

public class SupplyStackMoves {
    private final int numElements;
    private final int srcStack;
    private final int destStack;

    public SupplyStackMoves(int numElements, int srcStack, int destStack) {
        this.numElements = numElements;
        this.srcStack = srcStack;
        this.destStack = destStack;
    }

    public int getNumElements() {
        return numElements;
    }

    public int getSrcStack() {
        return srcStack;
    }

    public int getDestStack() {
        return destStack;
    }
}
