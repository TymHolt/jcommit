package org.jcommit.commands.git.add;

import org.jcommit.commands.Command;
import org.jcommit.commands.CommandResult;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class GitAddCommand {

    private final File executionPath;
    private final List<String> gitFilePaths;

    public GitAddCommand(File executionPath, List<String> gitFilePaths) {
        this.executionPath = executionPath;
        this.gitFilePaths = gitFilePaths;
    }

    public CommandResult execute() throws IOException, InterruptedException {
        final List<String> arguments = new ArrayList<>();
        arguments.add("git");
        arguments.add("add");

        for (String gitFilePath : this.gitFilePaths)
            arguments.add(gitFilePath);

        final Command command = new Command(this.executionPath, arguments);
        return command.execute();
    }
}
