package org.jcommit.commands.git.checkout;

import org.jcommit.commands.Command;
import org.jcommit.commands.CommandResult;

import java.io.File;
import java.io.IOException;

public final class GitCheckoutCommand {

    private final File executionPath;
    private final String branchName;

    public GitCheckoutCommand(File executionPath, String branchName) {
        this.executionPath = executionPath;
        this.branchName = branchName;
    }

    public CommandResult execute() throws IOException, InterruptedException {
        final Command command = new Command(this.executionPath, new String[] {
            "git", "checkout", branchName});
        return command.execute();
    }
}
