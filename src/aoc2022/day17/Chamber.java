package aoc2022.day17;

import java.util.*;
import java.util.stream.IntStream;

public class Chamber {
    private final int maxWidth;
    private final int startingX;
    private final int startingY;
    private final List<WindDirection> windDirections;
    private int windCounter = 0;
    private final Hashtable<String, String> chamberContents = new Hashtable<>();
    private final ChamberState chamberState;

    public Chamber(int maxWidth, int startingX, int startingY, List<WindDirection> windDirections) {
        this.maxWidth = maxWidth;
        this.startingX = startingX;
        this.startingY = startingY;
        this.windDirections = windDirections;
        this.chamberState = new ChamberState(this);
    }

    public void printChamber() {
        int maxHeight = getMaxHeight();
        for (int y = maxHeight; y >= 0; y--) {
            System.out.printf(" %6d", y);
            System.out.print("|");
            System.out.print(getRowDisplayValue(y));
            System.out.println("|");
        }
        printChamberBottom();
    }

    public String getDisplayValue(int x, int y) {
        String content = chamberContents.get(Coordinate.buildName(x, y));
        return Objects.requireNonNullElse(content, ".");
    }

    public String getRowDisplayValue(int y) {
        StringBuilder sb = new StringBuilder();

        for (int x = 0; x < maxWidth; x++) {
            sb.append(getDisplayValue(x, y));
        }

        return sb.toString();
    }

    private void printChamberBottom() {
        IntStream.range(0, 7).mapToObj(i -> " ").forEach(System.out::print);
        System.out.print("+");
        IntStream.range(0, maxWidth).mapToObj(i -> "-").forEach(System.out::print);
        System.out.println("+");
    }

    public int getMaxHeight() {
        int maxY = -1;
        for (String coordinateName : chamberContents.keySet()) {
            Coordinate c = Coordinate.parseName(coordinateName);
            if (c != null && c.getY() > maxY) {
                maxY = c.getY();
            }
        }
        return maxY;
    }

    public long getHeightAtRock(long rockNumber) {
        if (chamberState.getChamberCycle() == null) {
            return chamberState.getHeightAtRock((int) rockNumber);
        }
        else {
            return chamberState.getChamberCycle().getHeightAtRock(rockNumber);
        }
    }

    public void simulateRockFall() {
        int currMaxHeight = getMaxHeight();
        Rock rock = Rock.getNextRock(startingX, startingY + currMaxHeight);
        Set<String> topCoordinates = getTopCoordinates();
        boolean rockSettled = false;
        while (!rockSettled) {
            blowRock(rock);
            rockSettled = rock.moveDown(topCoordinates);
        }

        // set position in chamber
        rock.getCoordinates().forEach(c -> chamberContents.put(Coordinate.buildName(c.getX(), c.getY()), "#"));

        if (chamberState.getChamberCycle() == null) {
            chamberState.storeState();
        }
    }

    public boolean cycleFound() {
        return chamberState.getChamberCycle() != null;
    }

    private WindDirection getNextWindDirection() {
        return windDirections.get(windCounter++ % windDirections.size());
    }

    private Set<String> getTopCoordinates() {
        return chamberContents.keySet();
    }

    private void blowRock(Rock rock) {
        WindDirection windDirection = getNextWindDirection();
        Set<String> topCoordinates = getTopCoordinates();
        switch (windDirection) {
            case Left:
                rock.moveLeft(topCoordinates);
                break;
            case Right:
                rock.moveRight(maxWidth, topCoordinates);
                break;
        }
    }
}
