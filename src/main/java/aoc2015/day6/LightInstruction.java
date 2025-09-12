package aoc2015.day6;

public class LightInstruction 
{
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private LightAction action;

    public LightInstruction(int startX, int startY, int endX, int endY, LightAction action) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.action = action;
    }

    public LightInstruction(String instruction) {
        String[] parts = instruction.split(" ");
        if (parts[0].equals("turn")) {
            if (parts[1].equals("on")) {
                action = LightAction.TURN_ON;
            } else if (parts[1].equals("off")) {
                action = LightAction.TURN_OFF;
            }
            String[] startCoords = parts[2].split(",");
            String[] endCoords = parts[4].split(",");
            startX = Integer.parseInt(startCoords[0]);
            startY = Integer.parseInt(startCoords[1]);
            endX = Integer.parseInt(endCoords[0]);
            endY = Integer.parseInt(endCoords[1]);
        } else if (parts[0].equals("toggle")) {
            action = LightAction.TOGGLE;
            String[] startCoords = parts[1].split(",");
            String[] endCoords = parts[3].split(",");
            startX = Integer.parseInt(startCoords[0]);
            startY = Integer.parseInt(startCoords[1]);
            endX = Integer.parseInt(endCoords[0]);
            endY = Integer.parseInt(endCoords[1]);
        } else {
            throw new IllegalArgumentException("Invalid instruction: " + instruction);
        }
    }

    public int getStartX() {
        return startX;
    }
    public int getStartY() {
        return startY;
    }
    public int getEndX() {
        return endX;
    }
    public int getEndY() {
        return endY;
    }
    public LightAction getAction() {
        return action;
    }
}
