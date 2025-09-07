package aoc2017.day2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SpreadsheetRow {
    private final List<Integer> cells = new ArrayList<>();

    public SpreadsheetRow(String line) {
        cells.addAll(Arrays.stream(line.split("\\t")).map(Integer::parseInt).collect(Collectors.toList()));
    }

    public int getChecksum(ChecksumMethod checksumMethod) {
        return checksumMethod.getChecksum(cells);
    }
}
