package org.klozevitz.services.enums.callbackServiceCommands;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum EmployeeCallBackServiceCommands {
    REGISTER_EMPLOYEE("/employee");

    private final String command;

    EmployeeCallBackServiceCommands(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return command;
    }

    public boolean equals(String command) {
        return this.toString().equals(command);
    }

    public static boolean hasCommand(String command) {
        return Arrays.stream(EmployeeCallBackServiceCommands.values())
                .anyMatch(c -> c.equals(command));
    }
}