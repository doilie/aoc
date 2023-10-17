package aoc2017.day2;

import java.util.List;

public class DifferenceChecksumMethod extends ChecksumMethod {
    @Override
    public int getChecksum(List<Integer> cells) {
        int min = Integer.MAX_VALUE;
        int max = 0;
        for (int num : cells) {
            if (num < min) {
                min = num;
            }
            if (num > max) {
                max = num;
            }
        }
        return max - min;
    }
}
