package aoc2022.day17;

public enum WindDirection {
    Left,
    Right;

    public static WindDirection getFromCharacter(char direction) {
        switch(direction) {
            case '<': return Left;
            case '>': return Right;
        }
        return null;
    }
}
