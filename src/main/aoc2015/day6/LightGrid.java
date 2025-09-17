package aoc2015.day6;

public interface LightGrid {
    public void applyInstruction(LightInstruction instruction);
    public int countLightsOn();
}
