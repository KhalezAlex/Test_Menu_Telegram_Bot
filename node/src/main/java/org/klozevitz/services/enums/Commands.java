package org.klozevitz.services.enums;

import lombok.Getter;

@Getter
public enum Commands {
    HELP("/help"),
    CANCEL("/cancel"),
    START("/start");

    private final String command;

    Commands(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return command;
    }

    public boolean equals(String command) {
        return this.toString().equals(command);
    }
}
