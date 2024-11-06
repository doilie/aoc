package aoc2019.day3;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class WirePathTest
{
    @Test
    void moveRight()
    {
        WirePath wg = new WirePath();
        wg.move("R8");
        assertEquals(8, wg.getCurrentX());
        assertEquals(0, wg.getCurrentY());
    }

    @Test
    void moveRightManySteps()
    {
        WirePath wg = new WirePath();
        wg.move("R888");
        assertEquals(888, wg.getCurrentX());
        assertEquals(0, wg.getCurrentY());
    }

    @Test
    void moveUp()
    {
        WirePath wg = new WirePath();
        wg.move("U5");
        assertEquals(0, wg.getCurrentX());
        assertEquals(-5, wg.getCurrentY());
    }

    @Test
    void moveLeft()
    {
        WirePath wg = new WirePath();
        wg.move("L5");
        assertEquals(-5, wg.getCurrentX());
        assertEquals(0, wg.getCurrentY());
    }

    @Test
    void moveDown()
    {
        WirePath wg = new WirePath();
        wg.move("D3");
        assertEquals(0, wg.getCurrentX());
        assertEquals(3, wg.getCurrentY());
    }

    @Test
    void moveMultiple()
    {
        WirePath wg = new WirePath();
        wg.move("R8");
        wg.move("U5");
        wg.move("L5");
        wg.move("D3");
        assertEquals(3, wg.getCurrentX());
        assertEquals(-2, wg.getCurrentY());
    }

    @Test
    void moveMultiple2()
    {
        WirePath wg = new WirePath();
        wg.move("U7");
        wg.move("R6");
        wg.move("D4");
        wg.move("L4");
        assertEquals(2, wg.getCurrentX());
        assertEquals(-3, wg.getCurrentY());
    }

    @Test
    void getPositionsWithIntersection()
    {
        WirePath wg1 = new WirePath();
        wg1.move("R8");
        wg1.move("U5");
        wg1.move("L5");
        wg1.move("D3");
        WirePath wg2 = new WirePath();
        wg2.move("U7");
        wg2.move("R6");
        wg2.move("D4");
        wg2.move("L4");
        Set<String> positionsWithIntersection = WirePath.getPositionsWithIntersection(List.of(wg1, wg2));
        assertEquals(2, positionsWithIntersection.size());
        assertTrue(positionsWithIntersection.contains("3,-3"));
        assertTrue(positionsWithIntersection.contains("6,-5"));
    }

    @Test
    void getClosestDistanceFromCentralPort()
    {
        WirePath wg1 = new WirePath();
        wg1.move("R8");
        wg1.move("U5");
        wg1.move("L5");
        wg1.move("D3");
        WirePath wg2 = new WirePath();
        wg2.move("U7");
        wg2.move("R6");
        wg2.move("D4");
        wg2.move("L4");
        assertEquals(6, WirePath.getClosestDistanceFromCentralPort(List.of(wg1, wg2)));
    }
}