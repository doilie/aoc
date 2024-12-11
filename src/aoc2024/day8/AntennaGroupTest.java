package aoc2024.day8;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AntennaGroupTest {

    @Test
    void createAntennaPairs_1()
    {
        AntennaGroup antennaGroup = new AntennaGroup(List.of("4,3", "5,5"));
        Set<List<String>> pairs = antennaGroup.getAntennaPairs();
        assertEquals(1, pairs.size());
        assertTrue(pairs.contains(List.of("4,3", "5,5")));
    }

    @Test
    void createAntennaPairs_2()
    {
        AntennaGroup antennaGroup = new AntennaGroup(List.of("4,3", "5,5", "8,4"));
        Set<List<String>> pairs = antennaGroup.getAntennaPairs();
        assertEquals(3, pairs.size());
        assertTrue(pairs.contains(List.of("4,3", "5,5")));
        assertTrue(pairs.contains(List.of("4,3", "8,4")));
        assertTrue(pairs.contains(List.of("5,5", "8,4")));
    }

    @Test
    void getAntinodePositions_1()
    {
        AntennaGroup.setSize(10);
        Set<String> antinodes = AntennaGroup.getAntinodePositions("4,3", "5,5");
        assertEquals(Set.of("3,1", "6,7"), antinodes);
    }

    @Test
    void getAntinodePositions_2()
    {
        AntennaGroup.setSize(10);
        Set<String> antinodes = AntennaGroup.getAntinodePositions("4,3", "8,4");
        assertEquals(Set.of("0,2"), antinodes);
    }

    @Test
    void getAntinodePositions_3()
    {
        AntennaGroup.setSize(30);
        AntennaGroup antennaGroup = new AntennaGroup(List.of("3,3", "10,6"));
        Set<List<String>> antennaPairs = antennaGroup.getAntennaPairs();
        assertEquals(Set.of(List.of("10,6", "3,3")), antennaPairs);
        List<List<String>> antennaPairList = antennaPairs.stream().toList();
        Set<String> antinodes = AntennaGroup.getAntinodePositions(antennaPairList.get(0).get(0), antennaPairList.get(0).get(1));
        assertEquals(Set.of("17,9"), antinodes);
    }

    @Test
    void getAntinodes_1()
    {
        AntennaGroup.setSize(10);
        AntennaGroup antennaGroup = new AntennaGroup(List.of("4,3", "5,5"));
        assertEquals(Set.of("3,1", "6,7"), antennaGroup.getAntinodes());
    }

    @Test
    void getAntinodes_2()
    {
        AntennaGroup.setSize(10);
        AntennaGroup antennaGroup = new AntennaGroup(List.of("4,3", "5,5", "8,4"));
        assertEquals(Set.of("3,1", "0,2", "2,6", "6,7"), antennaGroup.getAntinodes());
    }
}