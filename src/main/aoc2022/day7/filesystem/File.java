package aoc2022.day7.filesystem;

public class File extends FileSystemContents {
    private final int size;

    public File(String name, int size) {
        super(name);
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
