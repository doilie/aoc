package challenges;

import impl.rucksack.Rucksack;

import java.util.ArrayList;
import java.util.List;

public class Day3_Rucksack extends Challenge {
    public static void main(String[] args)  {
        Day3_Rucksack day3 = new Day3_Rucksack();
        day3.doOneStarSolution();
        day3.doTwoStarSolution();
    }

    private final List<Rucksack> rucksacks = new ArrayList<>();

    public Day3_Rucksack() {
        super("day3-input.txt");
        parseFile();
    }

    @Override
    protected void parseFile() {
        String[] lines = this.getFileContents().split("\n");
        for (String line : lines) {
            if (line != null) {
                rucksacks.add(new Rucksack(line));
            }
        }
    }

    @Override
    public void doOneStarSolution() {
        int itemPrioritySum = 0;
        for (Rucksack rucksack : rucksacks) {
            itemPrioritySum += Rucksack.getItemPriority(rucksack.getMisplacedItem());
        }
        System.out.println("Misplaced Items Priority Sum: " + itemPrioritySum);
    }

    @Override
    public void doTwoStarSolution() {
        int itemPrioritySum = 0;
        for (int i = 0; i < rucksacks.size(); i += 3) {
            Rucksack rucksack = rucksacks.get(i);
            List<Rucksack> otherRucksacks = new ArrayList<>();
            otherRucksacks.add(rucksacks.get(i + 1));
            otherRucksacks.add(rucksacks.get(i + 2));
            itemPrioritySum += Rucksack.getItemPriority(rucksack.getBadgeItem(otherRucksacks));
        }
        System.out.println("Badge Priority Sum: " + itemPrioritySum);
    }
}
