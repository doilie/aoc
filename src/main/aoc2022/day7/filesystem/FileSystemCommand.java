package aoc2022.day7.filesystem;

import java.util.ArrayList;
import java.util.List;

public class FileSystemCommand {
    public enum FileSystemAction {
        ChangeDirectory,
        CreateDirectory,
        CreateFile,
        ListDirectory
    }

    public enum CommandArgIndex {
        ChangeDirectoryName(2),
        NewDirectoryName(1),
        FileSize(0),
        Filename(1);

        private final int index;

        CommandArgIndex(int index) {
            this.index = index;
        }

        public int getIndex() {
            return this.index;
        }
    }

    private final FileSystemAction fileSystemAction;
    private final List<String> args = new ArrayList<>();
    public FileSystemCommand(String consoleOutput) {
        args.addAll(List.of(consoleOutput.split(" ")));
        this.fileSystemAction = parseLine(consoleOutput);
    }

    private FileSystemAction parseLine(String consoleOutput) {
        if (consoleOutput.startsWith("$")) {
            String[] command = consoleOutput.split(" ");
            switch(command[1]) {
                case "cd":
                    return FileSystemAction.ChangeDirectory;
                case "ls":
                    return FileSystemAction.ListDirectory;
            }
        }
        else if (consoleOutput.startsWith("dir")) {
            return FileSystemAction.CreateDirectory;
        }
        return FileSystemAction.CreateFile;
    }

    public FileSystemAction getAction() {
        return this.fileSystemAction;
    }

    public List<String> getArgs() {
        return args;
    }
}
