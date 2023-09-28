package impl.filesystem;

public abstract class FileSystemContents {
    private final String name;

    protected FileSystemContents(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
