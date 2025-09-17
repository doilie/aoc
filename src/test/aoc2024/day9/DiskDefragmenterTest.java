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
        assertEquals(1, diskDefragmenter.getNearestFreeBlock().getLocation());
        Set<DiskBlock> idBlocks = diskDefragmenter.getIdBlocks();
        assertEquals(3, idBlocks.size());
        assertEquals(10, diskDefragmenter.getFarthestIdBlock().getLocation());
        assertEquals(2, diskDefragmenter.getFarthestIdBlock().getId());
    }

    @Test
    void getDiskBlocks_2()
    {
        DiskDefragmenter diskDefragmenter = new DiskDefragmenter("2333133121414131402");
        Set<DiskBlock> diskBlocks = diskDefragmenter.getDiskBlocks();
        assertEquals(18, diskBlocks.size());
        Set<DiskBlock> freeBlocks = diskDefragmenter.getFreeBlocks();
        assertEquals(8, freeBlocks.size());
        assertEquals(2, diskDefragmenter.getNearestFreeBlock().getLocation());
        Set<DiskBlock> idBlocks = diskDefragmenter.getIdBlocks();
        assertEquals(10, idBlocks.size());
        assertEquals(40, diskDefragmenter.getFarthestIdBlock().getLocation());
        assertEquals(9, diskDefragmenter.getFarthestIdBlock().getId());
    }

    @Test
    void defragment_1()
    {
        DiskDefragmenter diskDefragmenter = new DiskDefragmenter("12345");
        diskDefragmenter.defragment();
        assertEquals(9, diskDefragmenter.getNearestFreeBlock().getLocation());
        assertTrue(diskDefragmenter.getNearestFreeBlock().getLocation() > diskDefragmenter.getFarthestIdBlock().getLocation());
    }


    @Test
    void getFileSystemCheckSum()
    {
        DiskDefragmenter diskDefragmenter = new DiskDefragmenter("2333133121414131402");
        diskDefragmenter.defragment();
        assertEquals(1928, diskDefragmenter.getFileSystemCheckSum());
    }


    @Test
    void getFileSystemCheckSum_defragment_v2()
    {
        DiskDefragmenter diskDefragmenter = new DiskDefragmenter("2333133121414131402");
        diskDefragmenter.defragment_v2();
        assertEquals(2858, diskDefragmenter.getFileSystemCheckSum());
    }

}