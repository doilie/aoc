package aoc2024.day9;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DiskDefragmenterTest
{

    @Test
    void getDiskBlocks_1()
    {
        DiskDefragmenter diskDefragmenter = new DiskDefragmenter("12345");
        Set<DiskBlock> diskBlocks = diskDefragmenter.getDiskBlocks();
        assertEquals(5, diskBlocks.size());
        Set<DiskBlock> freeBlocks = diskDefragmenter.getFreeBlocks();
        assertEquals(2, freeBlocks.size());
        assertEquals(1, diskDefragmenter.getLowestFreeBlock().getLocation());
        Set<DiskBlock> idBlocks = diskDefragmenter.getIdBlocks();
        assertEquals(3, idBlocks.size());
        assertEquals(10, diskDefragmenter.getHighestIdBlock().getLocation());
        assertEquals(2, diskDefragmenter.getHighestIdBlock().getId());
    }

    @Test
    void getDiskBlocks_2()
    {
        DiskDefragmenter diskDefragmenter = new DiskDefragmenter("2333133121414131402");
        Set<DiskBlock> diskBlocks = diskDefragmenter.getDiskBlocks();
        assertEquals(18, diskBlocks.size());
        Set<DiskBlock> freeBlocks = diskDefragmenter.getFreeBlocks();
        assertEquals(8, freeBlocks.size());
        assertEquals(2, diskDefragmenter.getLowestFreeBlock().getLocation());
        Set<DiskBlock> idBlocks = diskDefragmenter.getIdBlocks();
        assertEquals(10, idBlocks.size());
        assertEquals(40, diskDefragmenter.getHighestIdBlock().getLocation());
        assertEquals(9, diskDefragmenter.getHighestIdBlock().getId());
    }

    @Test
    void defragment_1()
    {
        DiskDefragmenter diskDefragmenter = new DiskDefragmenter("12345");
        diskDefragmenter.defragment();
        assertEquals(9, diskDefragmenter.getLowestFreeBlock().getLocation());
        assertTrue(diskDefragmenter.getLowestFreeBlock().getLocation() > diskDefragmenter.getHighestIdBlock().getLocation());
    }


    @Test
    void getFileSystemCheckSum()
    {
        DiskDefragmenter diskDefragmenter = new DiskDefragmenter("2333133121414131402");
        diskDefragmenter.defragment();
        assertEquals(1928, diskDefragmenter.getFileSystemCheckSum());
    }

}