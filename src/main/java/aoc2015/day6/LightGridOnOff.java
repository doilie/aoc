package aoc2015.day6;

public class LightGridOnOff implements LightGrid {
    private final boolean[][] grid;

    public LightGridOnOff(int width, int height) {
        this.grid = new boolean[width][height];
    }

    public void applyInstruction(LightInstruction instruction) {
        for (int x = instruction.getStartX(); x <= instruction.getEndX(); x++) {
            for (int y = instruction.getStartY(); y <= instruction.getEndY(); y++) {
                switch (instruction.getAction()) {
                    case TURN_ON -> grid[x][y] = true;
                    case TURN_OFF -> grid[x][y] = false;
                    case TOGGLE -> grid[x][y] = !grid[x][y];
                }
            }
        }
    }

    public int countLightsOn() {
        int count = 0;
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                if (grid[x][y]) {
                    count++;
                }
            }
        }
        return count;
    }
}  
