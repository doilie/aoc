package aoc2021.day2;

public class SubmarineCommand {
    public enum SubmarineDirection {
        Forward,
        Up,
        Down
    }

    private SubmarineDirection direction;
    private int distance;

    public SubmarineCommand(String command) {
        String[] commandParts = command.split(" ");
        if (commandParts.length == 2) {
            switch(commandParts[0]) {
                case "forward": direction = SubmarineDirection.Forward; break;
                case "up": direction = SubmarineDirection.Up; break;
                case "down": direction = SubmarineDirection.Down; break;
                default:
            }
            distance = Integer.parseInt(commandParts[1]);
        }
    }

    public void movePosition(SubmarinePosition position) {
        switch(direction) {
            case Forward: position.moveForward(distance); break;
            case Up: position.moveUp(distance); break;
            case Down: position.moveDown(distance); break;
        }
    }
}
