package aoc2022.day17;

public final class ChamberCycle {
    private final long minRock;
    private final long maxRock;
    private final ChamberState state;

    public ChamberCycle(ChamberState state, int minRock, int maxRock) {
        this.minRock = minRock - Rock.getRockTypeOrder().length;
        this.maxRock = maxRock - Rock.getRockTypeOrder().length;
        this.state = state;
    }

    public long getHeightAtRock(long rockNumber) {
        long range = maxRock - minRock;
        long height = state.getHeightAtRock((int) minRock + (int) range) - state.getHeightAtRock((int) minRock);
        long heightBeforeCycle = state.getHeightAtRock((int) minRock - 1);
        long numRocksInCycle = (rockNumber - minRock) / range;
        long extraRocksAfterCycles = (rockNumber - minRock) % range;
        long extraRocksHeight = 0;
        if (extraRocksAfterCycles > 0) {
            extraRocksHeight = state.getHeightAtRock((int) minRock + (int) extraRocksAfterCycles) - state.getHeightAtRock((int) minRock);
        }
        return numRocksInCycle * height + heightBeforeCycle + extraRocksHeight + 1;
    }
}
