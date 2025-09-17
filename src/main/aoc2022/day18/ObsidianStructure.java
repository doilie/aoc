package aoc2022.day18;

import java.util.*;

public class ObsidianStructure {
    private final Hashtable<String, List<String>> structure = new Hashtable<>();
    private final Hashtable<String, List<String>> structureNoGaps = new Hashtable<>();
    private int minX = Integer.MAX_VALUE;
    private int minY = Integer.MAX_VALUE;
    private int minZ = Integer.MAX_VALUE;
    private int maxX = 0;
    private int maxY = 0;
    private int maxZ = 0;

    public void addDroplet(String line) {
        structure.put(line, new ArrayList<>());
        assignMinMax(line);
    }

    public int computeSurfaceAreaAll() {
        computeAdjacent(structure);
        return computeSurfaceArea(structure);
    }

    public int computeSurfaceAreaOutside() {
        fillInAir();
        computeAdjacent(structureNoGaps);
        return computeSurfaceArea(structureNoGaps);
    }

    private void assignMinMax(String cStr) {
        Cube c = Cube.parseName(cStr);
        if (c != null) {
            minX = Math.min(minX, c.getX());
            minY = Math.min(minY, c.getY());
            minZ = Math.min(minZ, c.getZ());
            maxX = Math.max(maxX, c.getX());
            maxY = Math.max(maxY, c.getY());
            maxZ = Math.max(maxZ, c.getZ());
        }
    }

    private void fillInAir() {
        /*
        procedure BFS(G, root) is
        let Q be a queue
        label root as explored
        Q.enqueue(root)
        while Q is not empty do
            v := Q.dequeue()
            if v is the goal then
                return v
            for all edges from v to w in G.adjacentEdges(v) do
                if w is not labeled as explored then
                    label w as explored
                    w.parent := v
                    Q.enqueue(w)
         */
        // bounding cube = cube from min x,y,z to max x,y,z
        // get all water points in bounding cube - this is essentially the outline of the structure
        Set<String> waterArea = new HashSet<>();
        List<String> cubeQueue = new ArrayList<>();
        // prep min and max to be outside of structure
        minX--;
        minY--;
        minZ--;
        maxX++;
        maxY++;
        maxZ++;
        // start at a point that is outside of the structure (all of the min points - 1)
        String startingPoint = Cube.buildName(minX, minY, minZ);
        cubeQueue.add(startingPoint);

        // find all water
        while (!cubeQueue.isEmpty()) {
            String waterCube = cubeQueue.remove(0);
            if (waterArea.contains(waterCube)) {
                continue;
            }
            waterArea.add(waterCube);
            Cube c = Cube.parseName(waterCube);
            if (c != null) {
                for (String neighbor : c.getPossibleNeighbors()) {
                    Cube cN = Cube.parseName(neighbor);
                    if (cN != null &&
                        ((cN.getX() >= minX && cN.getX() <= maxX) &&
                        (cN.getY() >= minY && cN.getY() <= maxY) &&
                        (cN.getZ() >= minZ && cN.getZ() <= maxZ)) &&
                        !structure.containsKey(neighbor)) {
                        cubeQueue.add(neighbor);
                    }
                }
            }
        }

        // get all points that are bound by the water area
        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    String xyz = Cube.buildName(x, y, z);
                    if (!waterArea.contains(xyz)) {
                        structureNoGaps.put(xyz, new ArrayList<>());
                    }
                }
            }
        }
    }

    private static void computeAdjacent(Hashtable<String, List<String>> structure) {
        List<String> droplets = new ArrayList<>(structure.keySet());
        if (droplets.size() >= 3) {
            for (int x = 0; x < droplets.size(); x++) {
                Cube c1 = Cube.parseName(droplets.get(x));
                if (x == droplets.size() - 1) {
                    Cube c2 = Cube.parseName(droplets.get(0));
                    Cube c3 = Cube.parseName(droplets.get(1));
                    if (c1 != null && c2 != null && c3 != null) {
                        setAdjacent(structure, c1, c2);
                        setAdjacent(structure, c1, c3);
                    }
                }
                else if (x == droplets.size() - 2) {
                    Cube c2 = Cube.parseName(droplets.get(droplets.size() - 1));
                    Cube c3 = Cube.parseName(droplets.get(0));
                    if (c1 != null && c2 != null && c3 != null) {
                        setAdjacent(structure, c1, c2);
                        setAdjacent(structure, c1, c3);
                    }
                }
                else {
                    for (int y = x + 1; y < droplets.size(); y++) {
                        Cube c2 = Cube.parseName(droplets.get(y));
                        Cube c3;
                        if (y + 1 == droplets.size() - 1) {
                            c3 = Cube.parseName(droplets.get(0));
                        }
                        else {
                            c3 = Cube.parseName(droplets.get(y));
                        }
                        if (c1 != null && c2 != null && c3 != null) {
                            setAdjacent(structure, c1, c2);
                            setAdjacent(structure, c1, c3);
                        }
                    }
                }
            }
        }
        else if (droplets.size() == 2) {
            Cube c1 = Cube.parseName(droplets.get(0));
            Cube c2 = Cube.parseName(droplets.get(1));
            if (c1 != null && c2 != null) {
                setAdjacent(structure, c1, c2);
            }
        }

    }

    private static void setAdjacent(Hashtable<String, List<String>> structure, Cube c1, Cube c2) {
        if (c1.isAdjacent(c2)) {
            if (!structure.get(c1.getName()).contains(c2.getName())) {
                structure.get(c1.getName()).add(c2.getName());
            }
            if (!structure.get(c2.getName()).contains(c1.getName())) {
                structure.get(c2.getName()).add(c1.getName());
            }
        }
    }

    private static int computeSurfaceArea(Hashtable<String, List<String>> structure) {
        final int totalSides = 6;
        int surfaceArea = 0;
        for (String droplet : structure.keySet()) {
            surfaceArea += totalSides - structure.get(droplet).size();
        }
        return surfaceArea;
    }
}
