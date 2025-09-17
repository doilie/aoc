package aoc2022.day16;

import java.util.*;
import java.util.stream.Collectors;

public class ValveGraph {
    private final Hashtable<String, Valve> valves = new Hashtable<>();
    private final Hashtable<List<String>, Integer> pressurePathList = new Hashtable<>();
    public void addValve(Valve valve) {
        valves.put(valve.getName(), valve);
    }

    public void initializePressurePaths() {
        calculateValveDistances();
    }

    public Map.Entry<List<String>, Integer> getMaxPressurePath(int minutes) {
        final String StartValve = "AA";
        pressurePathList.clear();
        computePressurePerPath(StartValve, minutes, new ArrayList<>());

        int max = 0;
        Map.Entry<List<String>, Integer> foundPathList = null;
        for (Map.Entry<List<String>, Integer> path : pressurePathList.entrySet()) {
            if (path.getValue() > max) {
                max = path.getValue();
                foundPathList = path;
            }
        }
        return foundPathList;
    }

    public Hashtable<List<String>, Integer> getMaxPressureToReleaseWithElephant(int minutes) {
        Hashtable<List<String>, Integer> combinedPaths = new Hashtable<>();

        // highest pressure path can be handled by human
        Map.Entry<List<String>, Integer> maxPressureByHuman = getMaxPressurePath(minutes);
        combinedPaths.put(maxPressureByHuman.getKey(), maxPressureByHuman.getValue());

        // find path that complements the human path with maximum pressure - sort the list by longest path to get higher values first
        List<String> humanPath = maxPressureByHuman.getKey();
        List<List<String>> pathsSorted = pressurePathList.keySet().stream().sorted((o1, o2) -> o2.size() - o1.size()).collect(Collectors.toList());
        int elephantPressure = 0;
        int maxPressure = 0;
        List<String> elephantPath = null;
        for (List<String> possibleElephantPath : pathsSorted) {
            if (humanPath == possibleElephantPath) {
                continue;
            }
            if (checkDisjoint(maxPressureByHuman.getKey(), possibleElephantPath)) {
                int totalPressure = pressurePathList.get(humanPath) + pressurePathList.get(possibleElephantPath);
                if (totalPressure > maxPressure) {
                    maxPressure = totalPressure;
                    elephantPath = possibleElephantPath;
                    elephantPressure = pressurePathList.get(possibleElephantPath);
                }
            }
        }

        if (elephantPath != null) {
            combinedPaths.put(elephantPath, elephantPressure);
        }

        return combinedPaths;
    }

    private static boolean checkDisjoint(List<String> path1, List<String> path2){
        Set<String> combined = new HashSet<>();
        combined.addAll(path1);
        combined.addAll(path2);

        return combined.size() == path1.size() + path2.size();
    }

    private void computePressurePerPath(String valveName, int minutes, List<String> openedValves) {
        Valve valve = valves.get(valveName);

        for (String destValveStr : valve.getValveDistances().keySet()) {
            Valve destValve = valves.get(destValveStr);
            int tempMinutes = minutes - valve.getValveDistances().get(destValveStr) - 1;
            if (tempMinutes > 0 && !openedValves.contains(destValveStr)) {
                int cumulativePressure = 0;
                if (!openedValves.isEmpty() && pressurePathList.get(openedValves) != null) {
                    cumulativePressure = pressurePathList.get(openedValves);
                }
                List<String> openedValvesCopy = new ArrayList<>(openedValves);
                openedValvesCopy.add(destValveStr);
                int tempPressure = tempMinutes * destValve.getRate();
                pressurePathList.put(openedValvesCopy, cumulativePressure + tempPressure);
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
