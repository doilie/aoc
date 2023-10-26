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

    public Chamber(int maxWidth, int startingX, int startingY, List<WindDirection> windDirections) {
        this.maxWidth = maxWidth;
        this.startingX = startingX;
        this.startingY = startingY;
        this.windDirections = windDirections;
    }

    public void printChamber() {
        int maxHeight = getMaxHeight();
        for (int y = maxHeight; y >= 0; y--) {
            System.out.print("|");
            for (int x = 0; x < maxWidth; x++) {
                String content = chamberContents.get(Coordinate.buildName(x, y));
                System.out.print(Objects.requireNonNullElse(content, "."));
            }
            System.out.println("|");
        }
        printChamberBottom();
    }

    private void printChamberBottom() {
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
