package aoc2024.day9;

import lib.Challenge;

public class Day9_DiskFragmenter extends Challenge
{
    public static void main(String[] args)
    {
        Day9_DiskFragmenter day8 = new Day9_DiskFragmenter();
        day8.doOneStarSolution();
        day8.doTwoStarSolution();
    }

    public Day9_DiskFragmenter() {
        super("2024/day9-input.txt");
        this.parseFile();
    }

    private final DiskDefragmenter defragmenter = new DiskDefragmenter(getFileContents().trim());

    @Override
    protected void parseFile()
    {

    }

    @Override
    public void doOneStarSolution()
    {
        defragmenter.defragment();
        System.out.println("Filesystem checksum : " + defragmenter.getFileSystemCheckSum());
    }

    @Override
    public void doTwoStarSolution()
    {
//        System.out.println("Number of repeating antinodes : " + defragmenter.getAntinodePositionsUntilEnd().size());
    }
}
