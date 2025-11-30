package aoc2020.day7;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BagRulesTest
{
    private static Stream<Arguments> provideBagRules()
    {
        return Stream.of(
                Arguments.of(
                        "light red bags contain 1 bright white bag, 2 muted yellow bags.",
                        "light red",
                        Map.of("bright white", 1, "muted yellow", 2)),
                Arguments.of(
                        "dark orange bags contain 3 bright white bags, 4 muted yellow bags.",
                        "dark orange",
                        Map.of("bright white", 3, "muted yellow", 4)),
                Arguments.of(
                        "bright white bags contain 1 shiny gold bag.",
                        "bright white",
                        Map.of("shiny gold", 1)),
                Arguments.of(
                        "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.",
                        "muted yellow",
                        Map.of("shiny gold", 2, "faded blue", 9)),
                Arguments.of(
                        "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.",
                        "shiny gold",
                        Map.of("dark olive", 1, "vibrant plum", 2)),
                Arguments.of(
                        "dark olive bags contain 3 faded blue bags, 4 dotted black bags.",
                        "dark olive",
                        Map.of("faded blue", 3, "dotted black", 4)),
                Arguments.of(
                        "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.",
                        "vibrant plum",
                        Map.of("faded blue", 5, "dotted black", 6)),
                Arguments.of(
                        "faded blue bags contain no other bags.",
                        "faded blue",
                        Map.of()),
                Arguments.of(
                        "dotted black bags contain no other bags.",
                        "dotted black",
                        Map.of())
        );
    }

    @ParameterizedTest
    @MethodSource("provideBagRules")
    void testAddBagRule(String rule, String key, Map<String, Integer> expectedContents)
    {
        BagRules bagRules = new BagRules();
        bagRules.addBagRule(rule);
        Map<String, Integer> actualContents = bagRules.getBagContents(key);
        assertNotNull(actualContents);
        assertEquals(expectedContents.size(), actualContents.size());
        Set<String> expectedContentsKeys = expectedContents.keySet();
        for (String expectedContentKey : expectedContentsKeys)
        {
            assertEquals(expectedContents.get(expectedContentKey), actualContents.get(expectedContentKey));
        }
    }

    @Test
    void testShinyGold()
    {
        BagRules bagRules = new BagRules();
        bagRules.addBagRule("light red bags contain 1 bright white bag, 2 muted yellow bags.");
        bagRules.addBagRule("dark orange bags contain 3 bright white bags, 4 muted yellow bags.");
        bagRules.addBagRule("bright white bags contain 1 shiny gold bag.");
        bagRules.addBagRule("muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.");
        bagRules.addBagRule("shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.");
        bagRules.addBagRule("dark olive bags contain 3 faded blue bags, 4 dotted black bags.");
        bagRules.addBagRule("vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.");
        bagRules.addBagRule("faded blue bags contain no other bags.");
        bagRules.addBagRule("dotted black bags contain no other bags.");

        Set<String> containingBags = new HashSet<>();
        bagRules.getContainingBags("shiny gold", containingBags);
        assertEquals(4, containingBags.size());
    }
}
