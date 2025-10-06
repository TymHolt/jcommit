package org.jcommit.commands.git.commit;

import org.jcommit.commands.Command;
import org.jcommit.commands.CommandResult;

import java.io.File;
import java.io.IOException;

public final class GitCommitCommand {

    private final File executionPath;
    private final String message;

    public GitCommitCommand(File executionPath, String message) {
        this.executionPath = executionPath;
        this.message = message;
    }

    public CommandResult execute() throws IOException, InterruptedException {
        final String[] arguments = {"git", "commit", "-m", '"' + message + '"'};
        final Command command = new Command(this.executionPath, arguments);
        return command.execute();
    }
}
