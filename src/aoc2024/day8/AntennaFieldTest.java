package aoc2024.day8;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AntennaFieldTest
{

    @Test
    void getAntennas_1()
    {
        String input = """
                ..........
                ..........
                ..........
                ....a.....
                ..........
                .....a....
                ..........
                ..........
                ..........
                ..........""";
        AntennaField antennaField = new AntennaField(input);
        AntennaGroup antennaGroup = antennaField.getAntennaGroup("a");
        assertNotNull(antennaGroup);
        assertTrue(antennaGroup.getAntennaPairs().contains(List.of("4,3", "5,5")));
    }

    @Test
    void getAntennas_2()
    {
        String input = """
                ..........
                ..........
                ..........
                ....a.....
                ........a.
                .....a....
                ..........
                ......A...
                ..........
                ..........""";
        AntennaField antennaField = new AntennaField(input);
        AntennaGroup antennaGroup = antennaField.getAntennaGroup("a");
        assertNotNull(antennaGroup);
        antennaGroup = antennaField.getAntennaGroup("A");
        assertNotNull(antennaGroup);
        assertTrue(antennaGroup.getAntennaPairs().isEmpty());
    }

    @Test
    void getAntinodePositions_1()
    {
        String input = """
                ..........
                ..........
                ..........
                ....a.....
                ..........
                .....a....
                ..........
                ..........
                ..........
                ..........""";
        String[] lines = input.split("\n");
        AntennaGroup.setSize(lines.length);
        AntennaField antennaField = new AntennaField(input);
        assertEquals(Set.of("3,1", "6,7"), antennaField.getAntinodePositions());
    }

    @Test
    void getAntinodePositions_2()
    {
        String input = """
                ..........
                ..........
                ..........
                ....a.....
                ........a.
                .....a....
                ..........
                ......A...
                ..........
                ..........""";
        String[] lines = input.split("\n");
        AntennaGroup.setSize(lines.length);
        AntennaField antennaField = new AntennaField(input);
        assertEquals(Set.of("3,1", "0,2", "2,6", "6,7"), antennaField.getAntinodePositions());
    }

    @Test
    void getAntinodePositions_3()
    {
        String input = """
                ............
                ........0...
                .....0......
                .......0....
                ....0.......
                ......A.....
                ............
                ............
                ........A...
                .........A..
                ............
                ............""";
        String[] lines = input.split("\n");
        AntennaGroup.setSize(lines.length);
        AntennaField antennaField = new AntennaField(input);
        assertEquals(14, antennaField.getAntinodePositions().size());
    }


    @Test
    void getAntinodePositionsUntilEnd_1()
    {
        String input = """
                ............
                ........0...
                .....0......
                .......0....
                ....0.......
                ......A.....
                ............
                ............
                ........A...
                .........A..
                ............
                ............""";
        String[] lines = input.split("\n");
        AntennaGroup.setSize(lines.length);
        AntennaField antennaField = new AntennaField(input);
        assertEquals(34, antennaField.getAntinodePositionsUntilEnd().size());
    }
}