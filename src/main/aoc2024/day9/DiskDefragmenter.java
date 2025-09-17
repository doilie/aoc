package aoc2024.day9;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class DiskDefragmenter {
    private final String input;
    private final Set<DiskBlock> diskBlocks = new HashSet<>();
    private int highestId = -1;

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

    public DiskBlock getNearestFreeBlock()
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

    public DiskBlock getNearestFreeBlockWithSize(int size)
    {
        int lowestPosition = Integer.MAX_VALUE;
        Set<DiskBlock> freeBlocks = getFreeBlocks();
        DiskBlock lowestBlock = null;
        for (DiskBlock diskBlock : freeBlocks)
        {
            if (diskBlock.getSize() >= size && diskBlock.getLocation() < lowestPosition)
            {
                lowestBlock = diskBlock;
                lowestPosition = diskBlock.getLocation();
            }
        }
        return lowestBlock;
    }

    public DiskBlock getFarthestIdBlock()
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

    public DiskBlock getDiskBlockById(int id)
    {
        return diskBlocks.stream().filter(d -> d.getId() == id).toList().get(0);
    }

    public void defragment()
    {
        DiskBlock nearestFreeBlock = getNearestFreeBlock();
        DiskBlock farthestFreeBlock = getFarthestIdBlock();

        while (nearestFreeBlock.getLocation() < farthestFreeBlock.getLocation())
        {
            if (farthestFreeBlock.getSize() == nearestFreeBlock.getSize())
            {
                nearestFreeBlock.setId(farthestFreeBlock.getId());
                farthestFreeBlock.setId(DiskBlock.FREE);
            }
            else if (farthestFreeBlock.getSize() > nearestFreeBlock.getSize())
            {
                nearestFreeBlock.setId(farthestFreeBlock.getId());
                int remainingIdBlocks = farthestFreeBlock.getSize() - nearestFreeBlock.getSize();
                farthestFreeBlock.setSize(remainingIdBlocks);
            }
            else
            {
                int remainingFreeBlocks = nearestFreeBlock.getSize() - farthestFreeBlock.getSize();
                int idBlockSize = farthestFreeBlock.getSize();
                int freeLocation = nearestFreeBlock.getLocation();
                nearestFreeBlock.setId(farthestFreeBlock.getId());
                nearestFreeBlock.setSize(farthestFreeBlock.getSize());
                diskBlocks.add(DiskBlock.buildFreeBlock(remainingFreeBlocks, freeLocation + idBlockSize));
                farthestFreeBlock.setId(DiskBlock.FREE);
            }
            nearestFreeBlock = getNearestFreeBlock();
            farthestFreeBlock = getFarthestIdBlock();
        }
    }

    public void defragment_v2()
    {
        for (int id = highestId; id >= 0; id--)
        {
            DiskBlock idBlock = getDiskBlockById(id);
            DiskBlock nearestFreeBlock = getNearestFreeBlockWithSize(idBlock.getSize());
            if (nearestFreeBlock == null || nearestFreeBlock.getLocation() > idBlock.getLocation())
            {
                continue;
            }
            if (idBlock.getSize() == nearestFreeBlock.getSize())
            {
                nearestFreeBlock.setId(idBlock.getId());
                idBlock.setId(DiskBlock.FREE);
            }
            else if (idBlock.getSize() < nearestFreeBlock.getSize())
            {
                int remainingFreeBlocks = nearestFreeBlock.getSize() - idBlock.getSize();
                int idBlockSize = idBlock.getSize();
                int freeLocation = nearestFreeBlock.getLocation();
                nearestFreeBlock.setId(idBlock.getId());
                nearestFreeBlock.setSize(idBlock.getSize());
                diskBlocks.add(DiskBlock.buildFreeBlock(remainingFreeBlocks, freeLocation + idBlockSize));
                idBlock.setId(DiskBlock.FREE);
            }
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
                    highestId++;
                }
                else {
                    diskBlocks.add(DiskBlock.buildFreeBlock(size, location));
                }
                location += size;

            }
        }
    }

}
