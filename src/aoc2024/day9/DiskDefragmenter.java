package aoc2024.day9;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class DiskDefragmenter {
    private final String input;
    private final Set<DiskBlock> diskBlocks = new HashSet<>();

    public DiskDefragmenter(String input) {
        this.input = input;
        initializeDiskBlocks();
    }

    public Set<DiskBlock> getDiskBlocks() {
        return diskBlocks;
    }

    public Set<DiskBlock> getFreeBlocks() {
        return diskBlocks.stream().filter(DiskBlock::isFree).collect(Collectors.toSet());
    }

    public Set<DiskBlock> getIdBlocks()
    {
        return diskBlocks.stream().filter(d -> !d.isFree()).collect(Collectors.toSet());
    }

    public DiskBlock getLowestFreeBlock()
    {
        int lowestPosition = Integer.MAX_VALUE;
        Set<DiskBlock> freeBlocks = getFreeBlocks();
        DiskBlock lowestBlock = null;
        for (DiskBlock diskBlock : freeBlocks)
        {
            if (diskBlock.getLocation() < lowestPosition)
            {
                lowestBlock = diskBlock;
                lowestPosition = diskBlock.getLocation();
            }
        }
        return lowestBlock;
    }

    public DiskBlock getHighestIdBlock()
    {
        int highestPosition = -1;
        Set<DiskBlock> idBlocks = getIdBlocks();
        DiskBlock highestBlock = null;
        for (DiskBlock diskBlock : idBlocks)
        {
            if (diskBlock.getLocation() > highestPosition)
            {
                highestBlock = diskBlock;
                highestPosition = diskBlock.getLocation();
            }
        }
        return highestBlock;
    }

    public void defragment()
    {
        DiskBlock lowestFreeBlock = getLowestFreeBlock();
        DiskBlock highestIdBlock = getHighestIdBlock();

        while (lowestFreeBlock.getLocation() < highestIdBlock.getLocation())
        {
            if (lowestFreeBlock.getSize() == 0)
            {
                diskBlocks.remove(lowestFreeBlock);
            }
            else
            {
                if (highestIdBlock.getSize() == lowestFreeBlock.getSize())
                {
                    lowestFreeBlock.setId(highestIdBlock.getId());
                    highestIdBlock.setId(DiskBlock.FREE);
                }
                else if (highestIdBlock.getSize() > lowestFreeBlock.getSize())
                {
                    lowestFreeBlock.setId(highestIdBlock.getId());
                    int remainingIdBlocks = highestIdBlock.getSize() - lowestFreeBlock.getSize();
                    highestIdBlock.setSize(remainingIdBlocks);
                }
                else
                {
                    int remainingFreeBlocks = lowestFreeBlock.getSize() - highestIdBlock.getSize();
                    int idBlockSize = highestIdBlock.getSize();
                    int freeLocation = lowestFreeBlock.getLocation();
                    lowestFreeBlock.setId(highestIdBlock.getId());
                    lowestFreeBlock.setSize(highestIdBlock.getSize());
                    diskBlocks.add(DiskBlock.buildFreeBlock(remainingFreeBlocks, freeLocation + idBlockSize));
                    highestIdBlock.setId(DiskBlock.FREE);
                }
            }
            lowestFreeBlock = getLowestFreeBlock();
            highestIdBlock = getHighestIdBlock();
        }
    }

    public long getFileSystemCheckSum()
    {
        return diskBlocks.stream().mapToLong(DiskBlock::getCheckSum).sum();
    }

    private void initializeDiskBlocks()
    {
        int id = 0;
        int location = 0;
        for (int i = 0; i < input.length(); i++)
        {
            int size = Integer.parseInt(String.valueOf(input.charAt(i)));
            if (size > 0)
            {
                if (i % 2 == 0)
                {
                    diskBlocks.add(DiskBlock.buildIdBlock(id++, size, location));
                }
                else {
                    diskBlocks.add(DiskBlock.buildFreeBlock(size, location));
                }
                location += size;

            }
        }
    }

}
