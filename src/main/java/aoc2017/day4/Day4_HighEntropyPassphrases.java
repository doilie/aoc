package aoc2017.day4;

import lib.Challenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day4_HighEntropyPassphrases extends Challenge {
    public static void main(String[] args)
    {
        Day4_HighEntropyPassphrases day2 = new Day4_HighEntropyPassphrases();
        day2.doOneStarSolution();
        day2.doTwoStarSolution();
    }

    public Day4_HighEntropyPassphrases()
    {
        super("2017/day4-input.txt");
        this.parseFile();
    }

    private final List<Passphrase> passphrases = new ArrayList<>();

    @Override
    protected void parseFile()
    {
        String[] lines = getFileContents().split("\\n");
        passphrases.addAll(Arrays.stream(lines).map(Passphrase::new).toList());
    }

    @Override
    public void doOneStarSolution()
    {
        System.out.println("Number of valid passphrases: " + passphrases.stream().filter(Passphrase::isValid).count());
    }

    @Override
    public void doTwoStarSolution()
    {
        System.out.println("Number of valid passphrases (no anagrams): " + passphrases.stream().filter(Passphrase::isValid_NoAnagram).count());
    }
}
