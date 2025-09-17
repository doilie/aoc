package lib;

public class Position
{
    public static String getPosition(int x, int y)
    {
        return x + "," + y;
    }
    public static int getX(String position)
    {
        String[] split = position.split(",");
        return Integer.parseInt(split[0]);
    }
    public static int getY(String position)
    {
        String[] split = position.split(",");
        return Integer.parseInt(split[1]);
    }
}
