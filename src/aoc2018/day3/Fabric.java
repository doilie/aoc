package aoc2018.day3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fabric {
    private static String getPosition(int x, int y)
    {
        return x + "," + y;
    }
    private final Map<String, List<Integer>> fabricSquareArea = new HashMap<>();

    public void addElfOwnership(ElfFabricOwnership efo)
    {
        int startX = efo.getStartX();
        int endX = startX + efo.getWidth();
        int startY = efo.getStartY();
        int endY = startY + efo.getHeight();
        for (int x = startX; x < endX; x++)
        {
            for (int y = startY; y < endY; y++)
            {
                addElfInPosition(x, y, efo.getElfId());
            }
        }
    }

    public List<Integer> getFabricPositionOwners(int x, int y)
    {
        return fabricSquareArea.get(getPosition(x, y));
    }

    public long countFabricPositionsWithOverlap()
    {
        return fabricSquareArea.values().stream().filter(x -> x.size() > 1).count();
    }

    private void addElfInPosition(int x, int y, int elfId)
    {
        String position = getPosition(x, y);
        List<Integer> elfOwners = fabricSquareArea.get(getPosition(x, y));
        if (fabricSquareArea.get(getPosition(x, y)) == null)
        {
            elfOwners = new ArrayList<>();
            fabricSquareArea.put(position, elfOwners);
        }
        elfOwners.add(elfId);
    }
}
