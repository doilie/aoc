package aoc2024.day9;

public class DiskBlock
{
    public static final int FREE = -1;

    private int id;
    private int size;
    private final int location;

    private DiskBlock(int id, int size, int location)
    {
        this.id = id;
        this.size = size;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size)
    {
        this.size = size;
    }

    public int getLocation() {
        return location;
    }

    public boolean isFree()
    {
        return FREE == id;
    }

    public long getCheckSum()
    {
        if (!isFree())
        {
            long sum = 0;
            for (long i = location; i < location + size; i++)
            {
                sum += i * id;
            }
            return sum;
        }
        return 0;
    }

    public static DiskBlock buildIdBlock(int id, int size, int location)
    {
        return new DiskBlock(id, size, location);
    }

    public static DiskBlock buildFreeBlock(int size, int location)
    {
        return new DiskBlock(FREE, size, location);
    }
}
