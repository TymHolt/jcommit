package org.jcommit.commands;

public class CommandResult {

    private final String output;
    private final int exitCode;

    public CommandResult(String output, int exitCode) {
        this.output = output;
        this.exitCode = exitCode;
    }

    public String getOutput() {
        return this.output;
    }

    public int getExitCode() {
        return this.exitCode;
    }
}
