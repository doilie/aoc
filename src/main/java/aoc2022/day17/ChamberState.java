package aoc2022.day17;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class ChamberState {
    private final Chamber chamber;
    private final Hashtable<String, List<Integer>> states = new Hashtable<>();
    private final Hashtable<Integer, Integer> heightAtRock = new Hashtable<>();
    private ChamberCycle chamberCycle = null;

    public ChamberState(Chamber chamber) {
        this.chamber = chamber;
    }

    public void storeState() {
        if (chamberCycle == null) {
            int rockCounter = Rock.getRockCounter() - 1;
            heightAtRock.put(rockCounter, chamber.getMaxHeight());
            if (rockCounter > 0 && rockCounter % Rock.getRockTypeOrder().length == 0) {
                int minHeight = heightAtRock.get(rockCounter - Rock.getRockTypeOrder().length);
                String stateKey = getStateAsString(chamber.getMaxHeight(), minHeight);
                List<Integer> rockList = states.computeIfAbsent(stateKey, k -> new ArrayList<>());
                rockList.add(rockCounter);
            }
            detectCycle();
        }
    }

    private String getStateAsString(int topRow, int bottomRow) {
        StringBuilder sb = new StringBuilder();

        for (int y = topRow; y >= bottomRow; y--) {
            sb.append(chamber.getRowDisplayValue(y));
        }

        return sb.toString();
    }

    public int getHeightAtRock(int rockNumber) {
        return heightAtRock.get(rockNumber);
    }

    public ChamberCycle getChamberCycle() {
        return chamberCycle;
    }

    private void detectCycle() {
        for (List<Integer> rockList : states.values()) {
            if (rockList.size() > 2) {
                int interval = 0;
                boolean isCycle = true;
                for (int i = 1; i < rockList.size(); i++) {
                    if (interval != 0 && rockList.get(i) - rockList.get(i - 1) != interval) {
                        isCycle = false;
                        break;
                    }
                    interval = rockList.get(i) - rockList.get(i - 1);
                }
                if (isCycle) {
                    chamberCycle = new ChamberCycle(this, rockList.get(0), rockList.get(1));
                }
            }
        }
    }

}
