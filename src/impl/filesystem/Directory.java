package impl.filesystem;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Directory extends FileSystemContents {
    private final List<File> files = new ArrayList<>();
    private final Hashtable<String, Directory> directories = new Hashtable<>();
    private final Directory parentDirectory;
    private final String absolutePath;

    public Directory(String name, Directory parentDirectory) {
        super(name);
        this.parentDirectory = parentDirectory;
        this.absolutePath = buildAbsolutePath();
    }

    public Directory getParentDirectory() {
        return parentDirectory;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void addFile(File file) {
        this.files.add(file);
    }

    public void addDirectory(Directory childDirectory) {
        this.directories.put(childDirectory.getName(), childDirectory);
    }

    public int getDirectorySize() {
        int directoryFileSize = files.stream().mapToInt(File::getSize).sum();
        int childDirectorySize = directories.values().stream().mapToInt(Directory::getDirectorySize).sum();
        return directoryFileSize + childDirectorySize;
    }

    public Directory getChildDirectory(String directoryName) {
        return directories.get(directoryName);
    }

    public List<Directory> getDirectories() {
        return new ArrayList<>(directories.values());
    }

    public List<File> getFiles() {
        return this.files;
    }

    private String buildAbsolutePath() {
        StringBuilder absolutePath = new StringBuilder();
        Directory currentParent = parentDirectory;

        while(currentParent != null) {
            if (!currentParent.getName().equals("/")) {
                absolutePath.insert(0, "/");
            }
            absolutePath.insert(0, currentParent.getName());
            currentParent = currentParent.getParentDirectory();
        }

        absolutePath.append(this.getName());

        return absolutePath.toString();
    }
}
