package aoc2022.day16;

import java.util.*;

public class ValveGraph {
    private final Hashtable<String, Valve> valves = new Hashtable<>();
    private final Hashtable<String, Integer> pressurePaths = new Hashtable<>();

    public void addValve(Valve valve) {
        valves.put(valve.getName(), valve);
    }

    public Map.Entry<String, Integer> getMaxPressureToRelease(String valveName, int minutes) {
        calculateValveDistances();
        computePressurePerPath(valveName, minutes, new ArrayList<>());

        int max = 0;
        Map.Entry<String, Integer> foundPath = null;
        for (Map.Entry<String, Integer> path : pressurePaths.entrySet()) {
            if (path.getValue() > max) {
                max = path.getValue();
                foundPath = path;
            }
        }
        return foundPath;
    }

    private void computePressurePerPath(String valveName, int minutes, List<String> openedValves) {
        Valve valve = valves.get(valveName);

        for (String destValveStr : valve.getValveDistances().keySet()) {
            Valve destValve = valves.get(destValveStr);
            int tempMinutes = minutes - valve.getValveDistances().get(destValveStr) - 1;
            if (tempMinutes > 0 && !openedValves.contains(destValveStr)) {
                String pathKey = String.join(",", openedValves);
                int cumulativePressure = 0;
                if (!openedValves.isEmpty() && pressurePaths.get(pathKey) != null) {
                    cumulativePressure = pressurePaths.get(pathKey);
                }
                List<String> openedValvesCopy = new ArrayList<>(openedValves);
                openedValvesCopy.add(destValveStr);
                pathKey = String.join(",", openedValvesCopy);
                int tempPressure = tempMinutes * destValve.getRate();
                pressurePaths.put(pathKey, cumulativePressure + tempPressure);
                computePressurePerPath(destValveStr, tempMinutes, openedValvesCopy);
            }
        }
    }

    private void calculateValveDistances() {
        for (Valve valve : valves.values()) {
            calculateValveDistance(valve);
        }
    }

    private void calculateValveDistance(Valve valve) {
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
        List<Valve> valveQueue = new ArrayList<>();
        valves.values().forEach(v -> v.setVisited(false));
        valves.values().forEach(v -> v.setDistanceFromRoot(0));
        valve.setVisited(true);
        valveQueue.add(valve);
        while (!valveQueue.isEmpty()) {
            Valve v = valveQueue.remove(0);
            int distanceFromRoot = v.getDistanceFromRoot() + 1;
            for (String valveName : v.getTunnelDestinations()) {
                Valve dest = valves.get(valveName);
                dest.setDistanceFromRoot(distanceFromRoot);
                if (!dest.isVisited()) {
                    if (dest.getRate() > 0) {
                        valve.addValveDistance(valveName, distanceFromRoot);
                    }
                    dest.setVisited(true);
                    valveQueue.add(dest);
                }
            }
        }
    }
}
