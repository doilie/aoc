package aoc2021.day2;

import java.util.ArrayList;
import java.util.List;

public class SubmarineCommandList {
    private final List<SubmarineCommand> commands = new ArrayList<>();

    public void addCommand(String command) {
        commands.add(new SubmarineCommand(command));
    }

    public void performCommands(SubmarinePosition position) {
        for (SubmarineCommand command : commands) {
            command.movePosition(position);
        }
    }
}
