package aoc2017.day2;

import java.util.List;
import java.util.stream.Collectors;

public class ModuloChecksumMethod extends ChecksumMethod {
    @Override
    public int getChecksum(List<Integer> cells) {
        List<Integer> sortedCells = cells.stream().sorted().collect(Collectors.toList());
        for (int i = sortedCells.size() - 1; i >= 1; i--) {
            int currentNum = sortedCells.get(i);
            for (int j = i - 1; j >= 0; j--) {
                int divisibleByNum = sortedCells.get(j);
                if (currentNum % divisibleByNum == 0) {
                    return currentNum / divisibleByNum;
                }
            }
        }
        return 1;
    }
}
