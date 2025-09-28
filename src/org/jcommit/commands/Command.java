package org.jcommit.commands;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class Command {

    private final File executionPath;
    private final List<String> command;

    public Command(File executionPath, List<String> command) {
        this.executionPath = executionPath;
        this.command = command;
    }

    public Command(File executionPath, String[] command) {
        this(executionPath, Arrays.asList(command));
    }

    public CommandResult execute() throws IOException, InterruptedException {
        final ProcessBuilder processBuilder = new ProcessBuilder(this.command);
        processBuilder.directory(executionPath);
        final Process process = processBuilder.start();
        final StringBuilder outputBuilder = new StringBuilder();
        final BufferedReader outputReader = new BufferedReader(new InputStreamReader(
            process.getInputStream()));

        final int EOF = -1;
        int readCharValue;
        while ((readCharValue = outputReader.read()) != EOF) {
            // Discard carriage return to make parsing easier
            if (readCharValue != '\r')
                outputBuilder.append((char) readCharValue);
        }

        final int exitCode = process.waitFor();
        return new CommandResult(outputBuilder.toString(), exitCode);
    }
}
