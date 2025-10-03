package org.jcommit.commands.git.add;

import org.jcommit.commands.Command;
import org.jcommit.commands.CommandResult;

import java.io.File;
import java.io.IOException;
import java.util.List;

public final class GitAddCommand {

    private final File executionPath;
    private final List<String> gitFilePaths;

    public GitAddCommand(File executionPath, List<String> gitFilePaths) {
        this.executionPath = executionPath;
        this.gitFilePaths = gitFilePaths;
    }

    public CommandResult execute() throws IOException, InterruptedException {
        final String[] arguments = new String[gitFilePaths.size() + 2];
        int index = 0;
        arguments[index++] = "git";
        arguments[index++] = "add";

        for (String gitFilePath : this.gitFilePaths)
            arguments[index++] = gitFilePath;

        final Command command = new Command(this.executionPath, arguments);
        return command.execute();
    }
}
