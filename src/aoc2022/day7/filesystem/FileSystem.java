package aoc2022.day7.filesystem;

import java.util.*;

public class FileSystem {
    private final static int TotalDiskSpace = 70000000;
    private final Hashtable<String, Directory> directoryIndex = new Hashtable<>();
    private Directory currentDirectory;

    public FileSystem() {
        Directory root = new Directory("/", null);
        directoryIndex.put("/", root);
        currentDirectory = root;
    }

    public void changeDirectory(String directoryName) {
        if (directoryName.equals("..")) {
            currentDirectory = currentDirectory.getParentDirectory();
        }
        else {
            currentDirectory = currentDirectory.getChildDirectory(directoryName);
        }
    }

    public void addDirectory(String directoryName) {
        Directory newDirectory = new Directory(directoryName, currentDirectory);
        this.directoryIndex.put(newDirectory.getAbsolutePath(), newDirectory);
        currentDirectory.addDirectory(newDirectory);
    }

    public void addFile(String filename, int size) {
        currentDirectory.addFile(new File(filename, size));
    }

    public List<Directory> getAllDirectories() {
        return new ArrayList<>(directoryIndex.values());
    }

    public Directory getDirectoryByName(String name) {
        return directoryIndex.get(name);
    }

    public int getFreeSpace() {
        return TotalDiskSpace - this.getUsedSpace();
    }

    public int getUsedSpace() {
        return this.getDirectoryByName("/").getDirectorySize();
    }

    public void runCommand(String consoleOutput) {
        FileSystemCommand command = new FileSystemCommand(consoleOutput);
        FileSystemCommand.FileSystemAction action = command.getAction();
        List<String> args = command.getArgs();
        switch(action) {
            case ChangeDirectory: {
                this.changeDirectory(args.get(FileSystemCommand.CommandArgIndex.ChangeDirectoryName.getIndex()));
                break;
            }
            case CreateDirectory: {
                this.addDirectory(args.get(FileSystemCommand.CommandArgIndex.NewDirectoryName.getIndex()));
                break;
            }
            case CreateFile: {
                this.addFile(
                        args.get(FileSystemCommand.CommandArgIndex.Filename.getIndex()),
                        Integer.parseInt(args.get(FileSystemCommand.CommandArgIndex.FileSize.getIndex())));
                break;
            }
            case ListDirectory: {
                break;
            }
        }
    }

    public void printStructure(Directory d, String spaces) {
        System.out.println(spaces + "- " + d.getAbsolutePath() + " (dir, size=" + d.getDirectorySize() + ")");
        for (File file : d.getFiles()) {
            System.out.println(spaces + "  - " + file.getName() + " (file, size=" + file.getSize() + ")");
        }
        for (Directory d1: d.getDirectories()) {
            printStructure(d1, "  ");
        }
    }

}
