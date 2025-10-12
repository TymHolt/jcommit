package org.jcommit.commands.git.clone;

import org.jcommit.commands.Command;
import org.jcommit.commands.CommandResult;

import java.io.File;
import java.io.IOException;

public final class GitCloneCommand {

    private final File executionPath;
    private final String url;

    public GitCloneCommand(File executionPath, String url) {
        this.executionPath = executionPath;
        this.url = url;
    }

    public CommandResult execute() throws IOException, InterruptedException {
        final Command command = new Command(this.executionPath, new String[] {
            "git", "clone", url});
        return command.execute();
    }
}
