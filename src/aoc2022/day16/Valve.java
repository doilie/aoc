package aoc2022.day16;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Valve {
    private enum InputPosition {
        Name(1),
        RateString(4),
        RateValue(1),
        TunnelsStart(9);
        private final int position;

        InputPosition(int inputPosition) {
            this.position = inputPosition;
        }

        public int getPosition() {
            return position;
        }
    }

    private String name;
    private int rate;
    private final List<String> tunnelDestinations = new ArrayList<>();
    private boolean visited = false;
    private final Hashtable<String, Integer> valveDistances = new Hashtable<>();
    private int distanceFromRoot = 0;

    public Valve(String input) {
        List<String> inputParts = new ArrayList<>(List.of(input.split(" ")));
        if (inputParts.size() >= 10) {
            name = inputParts.get(InputPosition.Name.getPosition());
            String[] rateStr = inputParts.get(InputPosition.RateString.getPosition()).split("=");
            if (rateStr.length == 2) {
                rate = Integer.parseInt(rateStr[InputPosition.RateValue.getPosition()].substring(0, rateStr[InputPosition.RateValue.getPosition()].length() - 1));
            }
            List<String> destinations = inputParts.subList(InputPosition.TunnelsStart.getPosition(), inputParts.size());
            destinations.stream().map(destination -> destination.replace(',', '\0').trim()).forEach(tunnelDestinations::add);
        }
    }

    public String getName() {
        return name;
    }

    public int getRate() {
        return rate;
    }

    public List<String> getTunnelDestinations() {
        return tunnelDestinations;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public int getDistanceFromRoot() {
        return distanceFromRoot;
    }

    public void setDistanceFromRoot(int distanceFromRoot) {
        this.distanceFromRoot = distanceFromRoot;
    }

    public Hashtable<String, Integer> getValveDistances() {
        return valveDistances;
    }

    public void addValveDistance(String valveName, int distance) {
        valveDistances.merge(valveName, distance, (a, b) -> Math.min(b, a));
    }
}
