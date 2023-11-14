package aoc2022.day19;

import java.util.PriorityQueue;
import java.util.Queue;

public class BlueprintFactory {
    private final Blueprint blueprint;
    private final int minutes;

    public BlueprintFactory(Blueprint blueprint, int minutes) {
        this.blueprint = blueprint;
        this.minutes = minutes;
    }

    public int findBest() {
        int currentBest = 0;
        Queue<BlueprintState> queue = new PriorityQueue<>();
        queue.add(new BlueprintState(this.minutes, 1, 0, 0, 0, 1, 0, 0, 0));

        while (!queue.isEmpty()) {
            BlueprintState state = queue.poll();
            if (state.hasPotentialToBeBetter(currentBest)) {
                queue.addAll(state.getNextSensibleStates(blueprint));
            }
            currentBest = Math.max(currentBest, state.getGeode() + state.getGeodeRobots() * (state.getMinutes() - 1));
        }

        return currentBest;
    }
}
