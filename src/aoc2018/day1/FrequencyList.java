package aoc2018.day1;

import java.util.*;

public class FrequencyList {
    private final List<Integer> frequencies = new ArrayList<>();

    public void addFrequency(int frequency) {
        this.frequencies.add(frequency);
    }

    public int getDeltaFrequency(int startFrequency) {
        int delta = startFrequency;
        for (int frequency : frequencies) {
            delta = delta + frequency;
        }
        return delta;
    }

    public int getFrequencyReachedTwice(int startFrequency) {
        List<Integer> reachedFrequencies = new ArrayList<>();
        Integer reachedTwiceFrequency = null;

        int currentFrequency = startFrequency;
        while (reachedTwiceFrequency == null) {
            for (int frequency : frequencies) {
                currentFrequency = currentFrequency + frequency;
                if (reachedFrequencies.contains(currentFrequency)) {
                    reachedTwiceFrequency = currentFrequency;
                    break;
                }
                reachedFrequencies.add(currentFrequency);
            }
        }

        return reachedTwiceFrequency;
    }
}
