package org.jcommit.commands.git.help;

import org.jcommit.commands.Command;
import org.jcommit.commands.CommandResult;

import java.io.File;
import java.io.IOException;

public final class GitHelpCommand {

    private final File executionPath;

    public GitHelpCommand(File executionPath) {
        this.executionPath = executionPath;
    }

    public CommandResult execute() throws IOException, InterruptedException {
        final Command command = new Command(this.executionPath, new String[] {"git", "help"});
        return command.execute();
    }
}
