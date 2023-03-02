package com.casablanca.wallpaper.model;

import java.util.Objects;

public enum Command {
    READ_FILE("F"),
    READ_LINE("L"),
    TOTAL("T"),
    CUBIC("C"),
    DUPLICATION("D"),
    QUIT("Q");

    private final String command;

    Command(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public static Command getCommand(String command) {
        for (var c : Command.values()) {
            if (Objects.equals(c.command, command)) {
                return c;
            }
        }
        throw new IllegalArgumentException("Unknown command: " + command);
    }
}
