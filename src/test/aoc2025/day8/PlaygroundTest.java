package aoc2025.day8;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlaygroundTest {
    private static String getSampleData()
    {
        return """
                162,817,812
                57,618,57
                906,360,560
                592,479,940
                352,342,300
                466,668,158
                542,29,236
                431,825,988
                739,650,466
                52,470,668
                216,146,977
                819,987,18
                117,168,530
                805,96,715
                346,949,466
                970,615,88
                941,993,340
                862,61,35
                984,92,344
                425,690,689""";
    }

    @Test
    void testGetCircuits()
    {
        Playground playground = new Playground(getSampleData());
        List<Set<String>> circuits = playground.getCircuits(10);
        assertEquals(11, circuits.size());
        assertEquals(5, circuits.getFirst().size());
        assertEquals(4, circuits.get(1).size());
        assertEquals(2, circuits.get(2).size());
    }
}
