package aoc2015.day2;

public class PresentSize {
    private int length;
    private int width;
    private int height;

    public PresentSize(String dimensions) {
        String[] dimensionList = dimensions.split("x");
        if (dimensionList.length == 3) {
            this.length = Integer.parseInt(dimensionList[0]);
            this.width = Integer.parseInt(dimensionList[1]);
            this.height = Integer.parseInt(dimensionList[2]);
        }
    }

    public int computeSurfaceArea() {
        return 2 * computeArea(length, width) + 2 * computeArea(width, height) + 2 * computeArea(height, length);
    }

    public int getAreaOfSmallestSide() {
        return Math.min(Math.min(computeArea(length, width), computeArea(width, height)), computeArea(height, length));
    }

    public int getPerimeterOfSmallestSide() {
        return Math.min(Math.min(computePerimeter(length, width), computePerimeter(width, height)), computePerimeter(height, length));
    }

    public int getVolume() {
        return length * width * height;
    }

    public static int computeArea(int side1, int side2) {
        return side1 * side2;
    }

    public static int computePerimeter(int side1, int side2) {
        return 2 * side1 + 2 * side2;
    }
}
