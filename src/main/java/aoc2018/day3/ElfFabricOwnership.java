package aoc2018.day3;

public class ElfFabricOwnership {
    private int elfId;
    private int startX;
    private int startY;
    private int width;
    private int height;

    public ElfFabricOwnership(String input)
    {
        String[] splitAt = input.split("@");
        if (splitAt.length == 2)
        {
            elfId = Integer.parseInt(splitAt[0].trim().substring(1));
            String[] splitColon = splitAt[1].trim().split(":");
            if (splitColon.length == 2)
            {
                String[] splitComma = splitColon[0].trim().split(",");
                if (splitComma.length == 2)
                {
                    startX = Integer.parseInt(splitComma[0].trim());
                    startY = Integer.parseInt(splitComma[1].trim());
                }
                String[] splitX = splitColon[1].trim().split("x");
                if (splitComma.length == 2)
                {
                    width = Integer.parseInt(splitX[0].trim());
                    height = Integer.parseInt(splitX[1].trim());
                }
            }
        }
    }

    public int getElfId() {
        return elfId;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
