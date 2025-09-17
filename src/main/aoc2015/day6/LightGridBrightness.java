package aoc2015.day6;

public class LightGridBrightness implements LightGrid {
    private final int[][] grid;

    public LightGridBrightness(int width, int height) {
        this.grid = new int[width][height];
    }

    public void applyInstruction(LightInstruction instruction) {
        for (int x = instruction.getStartX(); x <= instruction.getEndX(); x++) {
            for (int y = instruction.getStartY(); y <= instruction.getEndY(); y++) {
                switch (instruction.getAction()) {
                    case TURN_ON -> grid[x][y] += 1;
                    case TURN_OFF -> grid[x][y] = Math.max(0, grid[x][y] - 1);
                    case TOGGLE -> grid[x][y] += 2;
                }
            }
        }
    }

    public int countLightsOn() {
        int totalBrightness = 0;
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                totalBrightness += grid[x][y];
            }
        }
        return totalBrightness;
    }

}
