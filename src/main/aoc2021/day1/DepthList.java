package aoc2021.day1;

import java.util.ArrayList;
import java.util.List;

public class DepthList {
    private final List<Integer> depths = new ArrayList<>();

    public void addDepth(int depth) {
        depths.add(depth);
    }

    public int getIncreaseCount() {
        return getIncreaseCountWithWindowSize(1);
    }

    public int getIncreaseCountWithWindowSize(int windowSize) {
        int increaseCount = 0;

        if (depths.size() > windowSize) {
            int previousDepth = 0;
            for (int i = 0; i < windowSize; i++) {
                previousDepth += depths.get(i);
            }

            for (int i = 1; i <= depths.size() - windowSize; i++) {
                int depth = 0;
                for (int j = i; j < i + windowSize; j++) {
                    depth += depths.get(j);
                }
                if (depth > previousDepth) {
                    increaseCount++;
                }
                previousDepth = depth;
            }
        }

        return increaseCount;
    }
}
