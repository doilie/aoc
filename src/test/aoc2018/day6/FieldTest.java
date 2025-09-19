package aoc2018.day6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldTest 
{
    @Test
    void testGetClosestPoint()
    {
        Field f = new Field();
        f.addPoint("1,1");
        f.addPoint("1,6");
        f.addPoint("8,3");
        f.addPoint("3,4");
        f.addPoint("5,5");
        f.addPoint("8,9");

        assertEquals("1,6", f.getClosestPoint("1,1"));
        assertEquals("3,4", f.getClosestPoint("5,5"));
        assertEquals("8,3", f.getClosestPoint("8,9"));

        for (String s : f.getPoints())
        {
            Coordinate c = Coordinate.createCoordinate(s);
            for (String s1 : f.getPoints())
            {
                if (!s.equals(s1))
                {
                    Coordinate c1 = Coordinate.createCoordinate(s1);
                    System.out.println(s + "-" + s1 + ": " + c1.getDistance(c));
                }
            }
        }
    }




}
