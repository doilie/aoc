package aoc2023.day10;

import java.util.*;
import java.util.stream.Stream;

public class PipeLine {
    private final Hashtable<String, Pipe> pipes = new Hashtable<>();
    private Pipe startPipe;
    private final List<Pipe> pipePath = new ArrayList<>();
    private final List<Pipe> areaInsidePipePath = new ArrayList<>();

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

    public List<Pipe> getPipePath() {
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

        // collect to 1 path
        pipePath.addAll(path1.getPipes());
        Collections.reverse(path2.getPipes());
        pipePath.addAll(path2.getPipes().subList(1, path2.getPipes().size() - 1));

        return pipePath;
    }

    public List<Pipe> getAreaInsidePipePath() {
        // hint from here: https://www.reddit.com/r/adventofcode/comments/18fgddy/2023_day_10_part_2_using_a_rendering_algorithm_to/
        int maxX = pipes.values().stream().mapToInt(Pipe::x).max().orElse(0);
        int maxY = pipes.values().stream().mapToInt(Pipe::y).max().orElse(0);

        String previousCurve = "";

        for (int y = 0; y < maxY; y++) {
            boolean isInside = false;
            for (int x = 0; x < maxX; x++) {
                Pipe currPipe = pipes.get(Pipe.buildRowColKey(x, y));
                if (pipePath.contains(currPipe)) {
                    switch (currPipe.value()) {
                        case "F", "L" -> previousCurve = currPipe.value();
                        case "|" -> isInside = !isInside;
                        case "7" -> {
                            if (previousCurve.equals("F")) {
                                previousCurve = "";
                            } else if (previousCurve.equals("L")) {
                                isInside = !isInside;
                            }
                        }
                        case "J" -> {
                            if (previousCurve.equals("L")) {
                                previousCurve = "";
                            } else if (previousCurve.equals("F")) {
                                isInside = !isInside;
                            }
                        }
                    }
                }

                if (!pipePath.contains(currPipe) && isInside) {
                    areaInsidePipePath.add(currPipe);
                }
            }
        }

        return areaInsidePipePath;
    }

    public void printPath() {
        int maxX = pipes.values().stream().mapToInt(Pipe::x).max().orElse(0);
        int maxY = pipes.values().stream().mapToInt(Pipe::y).max().orElse(0);

        for (int y = 0; y < maxY; y++) {
            for (int x = 0; x < maxX; x++) {
                Pipe pipe = pipes.get(Pipe.buildRowColKey(x, y));
                if (pipePath.contains(pipe)) {
                    System.out.print(pipe.value());
                }
                else if (areaInsidePipePath.contains(pipe)) {
                    System.out.print("I");
                }
                else {
                    System.out.print("O");
                }
            }
            System.out.println();
        }
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
