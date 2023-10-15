package aoc2022.day7;

import aoc2022.day7.filesystem.Directory;
import aoc2022.day7.filesystem.FileSystem;
import lib.Challenge;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day7_NoSpace extends Challenge {
    public static void main(String[] args) {
        Day7_NoSpace day7 = new Day7_NoSpace();
        day7.doOneStarSolution();
        day7.doTwoStarSolution();
    }

    private final FileSystem fileSystem = new FileSystem();

    public Day7_NoSpace() {
        super("2022/day7-input.txt");
        parseFile();
    }

    @Override
    protected void parseFile() {
        String[] lines = this.getFileContents().split("\n");
        for (String line : lines) {
            if (!line.equals("$ cd /")) {
                fileSystem.runCommand(line);
            }
        }
//        fileSystem.printStructure(fileSystem.getDirectoryByName("/"), "");
//        System.out.println();
    }

    @Override
    public void doOneStarSolution() {
        Stream<Directory> directoryStream = fileSystem.getAllDirectories().stream().filter(dir -> dir.getDirectorySize() <= 100000);
        System.out.println("Sum of sizes of directories < 100K: " + directoryStream.mapToInt(Directory::getDirectorySize).sum());
    }

    @Override
    public void doTwoStarSolution() {
        final int updateSizeNeeded = 30000000;
        int freeSpace = fileSystem.getFreeSpace();
        int neededSpace = updateSizeNeeded - freeSpace;
        List<Integer> directorySizes = fileSystem.getAllDirectories().stream().map(Directory::getDirectorySize).filter(size -> size >= neededSpace).sorted().collect(Collectors.toList());
        System.out.println("Smallest directory to delete to free up space: " + directorySizes.get(0));
    }
}
