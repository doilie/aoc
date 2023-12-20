package aoc2023.day10;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Stream;

public class PipeLine {
    private final Hashtable<String, Pipe> pipes = new Hashtable<>();
    private Pipe startPipe;

    public PipeLine(String[] lines) {
        for (int y = 0; y < lines.length; y++) {
            String line = lines[y];
            for (int x = 0; x < line.length(); x++) {
                String currNode = Character.toString(line.charAt(x));
                Pipe pipe = new Pipe(currNode, x, y);
                pipes.putIfAbsent(pipe.getName(), pipe);
                if (currNode.equals("S")) {
                    startPipe = pipe;
                }
            }
        }
    }

    public List<Path> getPaths() {
        List<Path> paths = initializePaths();

        if (paths.size() != 2) {
            return new ArrayList<>();
        }

        Path path1 = paths.get(0);
        Path path2 = paths.get(1);
        do {
            moveToNextPipe(path1);
            moveToNextPipe(path2);
        } while(path1.getCurrentPipe() != path2.getCurrentPipe());

        return paths;
    }

    private void moveToNextPipe(Path path) {
        String nextLocation = path.getNextPipeLocation();
        Pipe nextPipe = pipes.get(nextLocation);
        path.addPipe(nextPipe);
    }

    private List<Path> initializePaths() {
        return Stream.of(Path.Direction.Left, Path.Direction.Right, Path.Direction.Up, Path.Direction.Down)
                .filter(direction -> getPathFromDirection(direction) != null)
                .map(this::getPathFromDirection)
                .toList();
    }

    private Path getPathFromDirection(Path.Direction direction) {
        String pipeLocation = Path.calculateNextPipeLocation(startPipe.x(), startPipe.y(), direction);
        if (pipes.containsKey(pipeLocation)) {
            return Path.startPath(startPipe, pipes.get(pipeLocation), direction);
        }
        return null;
    }
}
