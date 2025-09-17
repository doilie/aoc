package aoc2022.day1.calories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ElfCalories {
    private final List<Integer> elfCalories = new ArrayList<>();

    public void addElfCalories(int elfCalories) {
        this.elfCalories.add(elfCalories);
    }

    public int getMaxCalories() {
        return getSumOfTopCalories(1);
    }

    public int getSumOfTopCalories(int level) {
        Collections.sort(elfCalories);

        int sumOfTopCalories = 0;
        for (int i = elfCalories.size() - 1; i >= (elfCalories.size() - level); i--) {
            sumOfTopCalories += elfCalories.get(i);
        }

        return sumOfTopCalories;
    }
}
