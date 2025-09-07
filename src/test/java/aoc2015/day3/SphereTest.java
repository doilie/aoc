package aoc2015.day3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SphereTest
{
    @Test
    public void testMoveRight()
    {
        Sphere sphere = new Sphere();
        // > = 2
        sphere.move(">");
        assertEquals(2, sphere.getVisitedPositions().size());
    }

    @Test
    public void testMoveAllDirections()
    {
        Sphere sphere = new Sphere();
        // ^>v< = 4
        sphere.move("^");
        sphere.move(">");
        sphere.move("v");
        sphere.move("<");
        assertEquals(4, sphere.getVisitedPositions().size());
    }

    @Test
    public void testMoveUpDownButNowhere()
    {
        Sphere sphere = new Sphere();
        // ^v^v^v^v^v = 2
        sphere.move("^");
        sphere.move("v");
        sphere.move("^");
        sphere.move("v");
        sphere.move("^");
        sphere.move("v");
        sphere.move("^");
        sphere.move("v");
        sphere.move("^");
        sphere.move("v");
        assertEquals(2, sphere.getVisitedPositions().size());
    }

}