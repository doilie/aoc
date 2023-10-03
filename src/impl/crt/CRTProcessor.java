package impl.crt;

import java.util.*;

public class CRTProcessor {
    private final List<Integer> cycleStates = new ArrayList<>();
    private final List<String> commandLines = new ArrayList<>();
    private final Stack<Integer> accumulator = new Stack<>();
    private int x = 1;

    public void addCommandLines(String[] lines) {
        commandLines.addAll(Arrays.asList(lines));
    }

    public void runCycles() {
        while (!commandLines.isEmpty() || !accumulator.isEmpty()) {
            cycleStates.add(x);
            if (!accumulator.isEmpty()) {
                int accValue = accumulator.pop();
                x += accValue;
            }
            else if (!commandLines.isEmpty()) {
                CRTCommand command = CRTCommand.createCommand(commandLines.remove(0));
                if (command != null) {
                    command.run(this);
                }
            }
        }
    }

    public void addToAccumulator(int value) {
        this.accumulator.push(value);
    }

    public int getSignalStrength() {
        final int startSignalStrengthIdx = 19;
        final int signalStrengthInterval = 40;
        int signalStrength = 0;
        for (int i = startSignalStrengthIdx; i < cycleStates.size(); i += signalStrengthInterval) {
            signalStrength += cycleStates.get(i) * (i + 1);
        }
        return signalStrength;
    }

    public void printCRT() {
        final int crtRowLength = 40;
        StringBuilder sb = new StringBuilder();

        for (int pixelPos = 0; pixelPos < cycleStates.size(); pixelPos++) {
            int currColumn = pixelPos % crtRowLength;
            int spritePos = cycleStates.get(pixelPos);
            if (Math.abs(currColumn - spritePos) <= 1) {
                sb.append("#");
            }
            else {
                sb.append(".");
            }
            if (pixelPos % crtRowLength == crtRowLength - 1) {
                sb.append("\n");
            }
        }

        System.out.println(sb);
    }
}
