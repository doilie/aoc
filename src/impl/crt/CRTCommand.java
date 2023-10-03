package impl.crt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class CRTCommand {
    private final String name;
    private final List<String> args = new ArrayList<>();

    public static CRTCommand createCommand(String line) {
        String commandName = getCommandName(line);
        if (!commandName.isEmpty()) {
            switch(commandName) {
                case "noop":
                    return new CRTCommand(line) {
                        @Override
                        public void run(CRTProcessor c) {

                        }
                    };
                case "addx":
                    return new CRTCommand(line) {
                        @Override
                        public void run(CRTProcessor c) {
                            if (c != null) {
                                int arg = Integer.parseInt(this.getArgs().get(0));
                                c.addToAccumulator(arg);
                            }
                        }
                    };
            }
        }
        return null;
    }

    private static String getCommandName(String line) {
        if (line != null) {
            String[] commandParts = line.split(" ");
            if (commandParts.length > 0) {
                return commandParts[0];
            }
        }
        return "";
    }

    private CRTCommand(String line) {
        this.name = getCommandName(line);
        if (!this.name.isEmpty()) {
            String[] commandParts = line.split(" ");
            if (commandParts.length > 1) {
                args.addAll(Arrays.asList(commandParts).subList(1, commandParts.length));
            }
        }
    }

    public String getName() {
        return name;
    }

    public List<String> getArgs() {
        return args;
    }

    public abstract void run(CRTProcessor c);
}
