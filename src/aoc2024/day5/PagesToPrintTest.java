package aoc2024.day5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PagesToPrintTest
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
    void isInCorrectOrder_1()
    {
        PagesToPrint pagesToPrint = new PagesToPrint("75,47,61,53,29", pageOrderingRules);
        assertTrue(pagesToPrint.isInCorrectOrder());
    }

    @Test
    void isInCorrectOrder_2()
    {
        PagesToPrint pagesToPrint = new PagesToPrint("97,61,53,29,13", pageOrderingRules);
        assertTrue(pagesToPrint.isInCorrectOrder());
    }

    @Test
    void isInCorrectOrder_3()
    {
        PagesToPrint pagesToPrint = new PagesToPrint("75,29,13", pageOrderingRules);
        assertTrue(pagesToPrint.isInCorrectOrder());
    }

    @Test
    void isInCorrectOrder_4()
    {
        PagesToPrint pagesToPrint = new PagesToPrint("75,97,47,61,53", pageOrderingRules);
        assertFalse(pagesToPrint.isInCorrectOrder());
    }

    @Test
    void isInCorrectOrder_5()
    {
        PagesToPrint pagesToPrint = new PagesToPrint("61,13,29", pageOrderingRules);
        assertFalse(pagesToPrint.isInCorrectOrder());
    }

    @Test
    void isInCorrectOrder_6()
    {
        PagesToPrint pagesToPrint = new PagesToPrint("97,13,75,29,47", pageOrderingRules);
        assertFalse(pagesToPrint.isInCorrectOrder());
    }

    @Test
    void getMiddlePage_1()
    {
        PagesToPrint pagesToPrint = new PagesToPrint("97,61,53,29,13", pageOrderingRules);
        assertEquals(53, pagesToPrint.getMiddlePage());
    }

    @Test
    void getMiddlePage_2()
    {
        PagesToPrint pagesToPrint = new PagesToPrint("75,29,13", pageOrderingRules);
        assertEquals(29, pagesToPrint.getMiddlePage());
    }}