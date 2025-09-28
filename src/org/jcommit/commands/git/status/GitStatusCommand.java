package org.jcommit.commands.git.status;

import org.jcommit.commands.Command;
import org.jcommit.commands.CommandResult;

import java.io.File;
import java.io.IOException;

public final class GitStatusCommand {

    private final File executionPath;

    public GitStatusCommand(File executionPath) {
        this.executionPath = executionPath;
    }

    public GitStatusResult execute() throws IOException, InterruptedException {
        final Command command = new Command(this.executionPath, new String[] {"git", "status"});
        final CommandResult commandResult = command.execute();
        return new GitStatusParser(commandResult).parse();
    }
}
