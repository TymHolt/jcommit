package org.jcommit.commands.git.init;

import org.jcommit.commands.Command;
import org.jcommit.commands.CommandResult;

import java.io.File;
import java.io.IOException;

public final class GitInitCommand {

    private final File executionPath;

    public GitInitCommand(File executionPath) {
        this.executionPath = executionPath;
    }

    public CommandResult execute() throws IOException, InterruptedException {
        final Command command = new Command(this.executionPath, new String[] {
            "git", "init"});
        return command.execute();
    }
}
