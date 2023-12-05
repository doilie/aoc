package aoc2023.day3;

import java.util.ArrayList;
import java.util.List;

public class SchematicNumber {
    private final int number;
    private final int x;
    private final int y;

    public SchematicNumber(int number, int x, int y) {
        this.number = number;
        this.x = x;
        this.y = y;
    }

    public List<String> getSurroundingCoordinates() {
        List<String> surroundingCoordinates = new ArrayList<>();
        int numberLength = Integer.toString(number).length();
        int xStart = x > 0 ? x - 1 : x;
        int yStart = y > 0 ? y - 1 : y;

        for (int yIdx = yStart; yIdx <= y + 1; yIdx++) {
            if (yIdx == y) {
                if (x > 0) {
                    surroundingCoordinates.add(x - 1 + "," + y);
                }
                surroundingCoordinates.add(x + numberLength + "," + y);
            }
            else {
                for (int xIdx = xStart; xIdx <= x + numberLength; xIdx++) {
                    surroundingCoordinates.add(xIdx + "," + yIdx);
                }
            }
        }

        return surroundingCoordinates;
    }

    public int getNumber() {
        return number;
    }
}
