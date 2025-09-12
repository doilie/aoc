package aoc2017.day6;

import java.util.HashSet;
import java.util.Set;

import lib.Challenge;

public class Day6_MemoryReallocation extends Challenge {
    public static void main(String[] args) {
        Day6_MemoryReallocation day6 = new Day6_MemoryReallocation();
        day6.doOneStarSolution();
        day6.doTwoStarSolution();
    }

    public Day6_MemoryReallocation() {
        super("2017/day6-input.txt");
        this.parseFile();
    }

    private String infiniteLoopState;

    @Override
    public void parseFile() {

    }
    
    @Override
    public void doOneStarSolution() { 
        MemoryBank bank = new MemoryBank(getFileContents().trim());
        System.out.println("Number of cycles before a repeat state: " + getCyclesUntilRepeat(bank));
        infiniteLoopState = bank.getState().replaceAll(",", "\t");
    }

    @Override
    public void doTwoStarSolution() { 
        MemoryBank bank = new MemoryBank(infiniteLoopState.trim());
        System.out.println("Number of cycles before a repeat state again: " + getCyclesUntilRepeat(bank));
    }

    private int getCyclesUntilRepeat(MemoryBank bank) {
        Set<String> seenStates = new HashSet<>();
        while (!seenStates.contains(bank.getState())) {
            seenStates.add(bank.getState());
            int maxIndex = bank.findMaxIndex();
            bank.redistribute(maxIndex);
        }
        return seenStates.size();
    }
}
