package org.jcommit.commands.git.pull;

import org.jcommit.commands.Command;
import org.jcommit.commands.CommandResult;

import java.io.File;
import java.io.IOException;

public final class GitPullCommand {

    private final File executionPath;

    public GitPullCommand(File executionPath) {
        this.executionPath = executionPath;
    }

    public CommandResult execute() throws IOException, InterruptedException {
        final Command command = new Command(this.executionPath, new String[] {"git", "pull"});
        return command.execute();
    }
}
