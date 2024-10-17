package aoc2017.day3;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SpiralStepsRebuild {
    private int x = 0;
    private int y = 0;
    private final Map<String, Long> positionValueMap = new HashMap<>();
    private final long lastValue;

    public SpiralStepsRebuild(long lastValue)
    {
        this.lastValue = lastValue;
    }
    public void buildSpiral()
    {
        long value = 1;
        int state = 1;
        positionValueMap.put(buildPosition(x, y), value);

        while (value <= lastValue) {
            switch (state) {
                case 1: // up
                    if (x == y)  // start new square
                    {
                        x++;
                        y++;
                    }
                    y--;
                    break;
                case 2: // left
                    x--;
                    break;
                case 3: // down
                    y++;
                    break;
                case 4: // right
                    x++;
                    break;
            }

            value = getSumOfAdjacentPositions();
            positionValueMap.put(buildPosition(x, y), value);

            if (Math.abs(x) == Math.abs(y)) {
                state = (state % 4) + 1;  // turn when at edge of square
            }
        }
    }

    public long getLastValue()
    {
        return positionValueMap.get(buildPosition(x, y));
    }

    private Set<String> getAdjacentPositions()
    {
        return Set.of(
                buildPosition(x + 1, y),
                buildPosition(x - 1, y),
                buildPosition(x, y + 1),
                buildPosition(x, y - 1),
                buildPosition(x - 1, y - 1),
                buildPosition(x + 1, y + 1),
                buildPosition(x + 1, y - 1),
                buildPosition(x - 1, y + 1));
    }

    private long getSumOfAdjacentPositions()
    {
        long sum = 0;
        Set<String> positions = getAdjacentPositions();
        for (String position : positions)
        {
            Long value = positionValueMap.get(position);
            if (value != null)
            {
                sum += value;
            }
        }
        return sum;
    }

    private static String buildPosition(int x, int y)
    {
        return x + "," + y;
    }
}
