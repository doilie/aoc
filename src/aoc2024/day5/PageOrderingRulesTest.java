package aoc2024.day5;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PageOrderingRulesTest
{
    private final PageOrderingRules pageOrderingRules = new PageOrderingRules("""
                47|53
                97|13
                97|61
                97|47
                75|29
                61|13
                75|53
                29|13
                97|29
                53|29
                61|53
                97|53
                61|29
                47|13
                75|47
                97|75
                47|61
                75|61
                47|29
                75|13
                53|13""");

    @Test
    void sortNumbers_1()
    {
        List<Integer> origNumbers = List.of(75, 47, 61, 53, 29);
        List<Integer> numbers = new ArrayList<>(origNumbers);
        numbers.sort(pageOrderingRules);
        assertEquals(origNumbers, numbers);
    }

    @Test
    void sortNumbers_2()
    {
        List<Integer> origNumbers = List.of(75, 97, 47, 61, 53);
        List<Integer> numbers = new ArrayList<>(origNumbers);
        numbers.sort(pageOrderingRules);
        assertNotEquals(origNumbers, numbers);
    }

}