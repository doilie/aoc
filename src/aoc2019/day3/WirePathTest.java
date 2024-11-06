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
        String wire1 = "R8,U5,L5,D3";
        String wire2 = "U7,R6,D4,L4";
        Set<String> positionsWithIntersection = WirePath.getPositionsWithIntersection(List.of(WirePath.createFromInstructions(wire1), WirePath.createFromInstructions(wire2)));
        assertEquals(2, positionsWithIntersection.size());
        assertTrue(positionsWithIntersection.contains("3,-3"));
        assertTrue(positionsWithIntersection.contains("6,-5"));
    }

    @Test
    void getClosestDistanceFromCentralPort()
    {
        String wire1 = "R8,U5,L5,D3";
        String wire2 = "U7,R6,D4,L4";
        assertEquals(6, WirePath.getClosestDistanceFromCentralPort(List.of(WirePath.createFromInstructions(wire1), WirePath.createFromInstructions(wire2))));
    }

    @Test
    void getClosestDistanceFromCentralPortCase1()
    {
        String wire1 = "R75,D30,R83,U83,L12,D49,R71,U7,L72";
        String wire2 = "U62,R66,U55,R34,D71,R55,D58,R83";
        assertEquals(159, WirePath.getClosestDistanceFromCentralPort(List.of(WirePath.createFromInstructions(wire1), WirePath.createFromInstructions(wire2))));
    }

    @Test
    void getClosestDistanceFromCentralPortCase2()
    {
        String wire1 = "R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51";
        String wire2 = "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7";
        assertEquals(135, WirePath.getClosestDistanceFromCentralPort(List.of(WirePath.createFromInstructions(wire1), WirePath.createFromInstructions(wire2))));
    }

    @Test
    void getNumberOfStepsToPosition1()
    {
        String wire = "R8,U5,L5,D3";
        WirePath wp = WirePath.createFromInstructions(wire);
        assertEquals(20, wp.getNumberOfStepsToPosition("3,-3"));
    }


    @Test
    void getNumberOfStepsToPosition2()
    {
        String wire = "U7,R6,D4,L4";
        WirePath wp = WirePath.createFromInstructions(wire);
        assertEquals(20, wp.getNumberOfStepsToPosition("3,-3"));
    }

    @Test
    void getNumberOfStepsToPosition1a()
    {
        String wire = "R8,U5,L5,D3";
        WirePath wp = WirePath.createFromInstructions(wire);
        assertEquals(15, wp.getNumberOfStepsToPosition("6,-5"));
    }


    @Test
    void getNumberOfStepsToPosition2b()
    {
        String wire = "U7,R6,D4,L4";
        WirePath wp = WirePath.createFromInstructions(wire);
        assertEquals(15, wp.getNumberOfStepsToPosition("6,-5"));
    }

    @Test
    void getFewestStepsToIntersection1()
    {
        String wire1 = "R8,U5,L5,D3";
        String wire2 = "U7,R6,D4,L4";
        assertEquals(30, WirePath.getFewestStepsToIntersection(List.of(WirePath.createFromInstructions(wire1), WirePath.createFromInstructions(wire2))));
    }

    @Test
    void getFewestStepsToIntersection2()
    {
        String wire1 = "R75,D30,R83,U83,L12,D49,R71,U7,L72";
        String wire2 = "U62,R66,U55,R34,D71,R55,D58,R83";
        assertEquals(610, WirePath.getFewestStepsToIntersection(List.of(WirePath.createFromInstructions(wire1), WirePath.createFromInstructions(wire2))));
    }

    @Test
    void getFewestStepsToIntersection3()
    {
        String wire1 = "R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51";
        String wire2 = "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7";
        assertEquals(410, WirePath.getFewestStepsToIntersection(List.of(WirePath.createFromInstructions(wire1), WirePath.createFromInstructions(wire2))));
    }
}