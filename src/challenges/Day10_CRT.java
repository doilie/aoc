package challenges;

import impl.cpu.CRTProcessor;

public class Day10_CRT extends Challenge {
    public static void main(String[] args) {
        Day10_CRT day10 = new Day10_CRT();
        day10.doOneStarSolution();
        day10.doTwoStarSolution();
    }

    private final CRTProcessor CRTProcessor = new CRTProcessor();
    public Day10_CRT() {
        super("day10-input.txt");
        parseFile();
    }

    @Override
    protected void parseFile() {
        String[] lines = this.getFileContents().split("\n");
        CRTProcessor.addCommandLines(lines);
    }

    @Override
    public void doOneStarSolution() {
        CRTProcessor.runCycles();
        System.out.println("Signal Strength: " + CRTProcessor.getSignalStrength());
    }

    @Override
    public void doTwoStarSolution() {
        System.out.println("CRT Output:");
        CRTProcessor.printCRT();
    }
}
