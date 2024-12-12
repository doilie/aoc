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

    @Override
    protected void parseFile()
    {

    }

    @Override
    public void doOneStarSolution()
    {
        DiskDefragmenter defragmenter = new DiskDefragmenter(getFileContents().trim());
        defragmenter.defragment();
        System.out.println("Filesystem checksum : " + defragmenter.getFileSystemCheckSum());
    }

    @Override
    public void doTwoStarSolution()
    {
        DiskDefragmenter defragmenter = new DiskDefragmenter(getFileContents().trim());
        defragmenter.defragment_v2();
        System.out.println("Filesystem checksum according to decreasing file id number : " + defragmenter.getFileSystemCheckSum());
    }
}
