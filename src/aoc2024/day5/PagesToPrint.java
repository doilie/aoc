package aoc2024.day5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class PagesToPrint
{
    private final List<Integer> pages = new ArrayList<>();
    private final Comparator<Integer> comparator;

    public PagesToPrint(String input, Comparator<Integer> comparator)
    {
        pages.addAll(Arrays.stream(input.split(",")).map(Integer::parseInt).toList());
        this.comparator = comparator;
    }

    public boolean isInCorrectOrder()
    {
        List<Integer> sortedPages = new ArrayList<>(pages);
        sortedPages.sort(comparator);
        return sortedPages.equals(pages);
    }

    public int getMiddlePage()
    {
        return pages.get(pages.size() / 2);
    }

    public void sort()
    {
        pages.sort(comparator);
    }
}
