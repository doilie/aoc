package aoc2023.day2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CubeGame {
    public static class CubeSet {
        String color;
        int count;

        CubeSet(String color, int count) {
            this.color = color;
            this.count = count;
        }
    }
    private int id;
    private final List<List<CubeSet>> cubeSets = new ArrayList<>();

    public CubeGame(String line) {
        String[] lineParts = line.split(":");
        if (lineParts.length == 2) {
            String[] idPart = lineParts[0].split("\\s");
            if (idPart.length == 2) {
                this.id = Integer.parseInt(idPart[1]);
            }

            String[] setParts = lineParts[1].split(";");
            for (String setPart : setParts) {
                String[] drawParts = setPart.split(",");
                List<CubeSet> cubeSet = new ArrayList<>();
                for (String drawPart : drawParts) {
                    String[] typeParts = drawPart.trim().split("\\s");
                    if (typeParts.length == 2) {
                        cubeSet.add(new CubeSet(typeParts[1], Integer.parseInt(typeParts[0])));
                    }
                }
                cubeSets.add(cubeSet);
            }
        }
    }

    public int getId() {
        return id;
    }

    public boolean checkCubeSets(HashMap<String, Integer> maxCubes) {
        for (List<CubeSet> cubeSetList : cubeSets) {
            for (CubeSet cubeSet : cubeSetList) {
                if (cubeSet.count > maxCubes.get(cubeSet.color)) {
                    return false;
                }
            }
        }
        return true;
    }

    public int getPowerOfCubes() {
        int redMax = 0;
        int blueMax = 0;
        int greenMax = 0;
        for (List<CubeSet> cubeSetList : cubeSets) {
            for (CubeSet cubeSet : cubeSetList) {
                switch(cubeSet.color) {
                    case "red":
                        if (cubeSet.count > redMax) {
                            redMax = cubeSet.count;
                        }
                        break;
                    case "blue":
                        if (cubeSet.count > blueMax) {
                            blueMax = cubeSet.count;
                        }
                        break;
                    case "green":
                        if (cubeSet.count > greenMax) {
                            greenMax = cubeSet.count;
                        }
                        break;
                }
            }
        }
        return redMax * blueMax * greenMax;
    }
}
