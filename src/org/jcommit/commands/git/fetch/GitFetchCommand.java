package org.jcommit.commands.git.fetch;

import org.jcommit.commands.Command;
import org.jcommit.commands.CommandResult;

import java.io.File;
import java.io.IOException;

public final class GitFetchCommand {

    private final File executionPath;

    public GitFetchCommand(File executionPath) {
        this.executionPath = executionPath;
    }

    public CommandResult execute() throws IOException, InterruptedException {
        final Command command = new Command(this.executionPath, new String[] {"git", "fetch"});
        return command.execute();
    }
}
