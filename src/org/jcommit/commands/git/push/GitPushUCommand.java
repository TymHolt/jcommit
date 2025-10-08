package org.jcommit.commands.git.push;

import org.jcommit.commands.Command;
import org.jcommit.commands.CommandResult;

import java.io.File;
import java.io.IOException;

public final class GitPushUCommand {

    private final File executionPath;
    private final String remote;
    private final String localBranch;

    public GitPushUCommand(File executionPath, String remote, String localBranch) {
        this.executionPath = executionPath;
        this.remote = remote;
        this.localBranch = localBranch;
    }

    public CommandResult execute() throws IOException, InterruptedException {
        final Command command = new Command(this.executionPath, new String[] {
                "git", "push", "-u", this.remote, this.localBranch});
        return command.execute();
    }
}
