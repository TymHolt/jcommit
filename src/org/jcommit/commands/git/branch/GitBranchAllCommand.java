package org.jcommit.commands.git.branch;

import org.jcommit.commands.Command;
import org.jcommit.commands.CommandResult;

import java.io.File;
import java.io.IOException;

public final class GitBranchAllCommand {

    private final File executionPath;

    public GitBranchAllCommand(File executionPath) {
        this.executionPath = executionPath;
    }

    public GitBranchAllResult execute() throws IOException, InterruptedException {
        final Command command = new Command(this.executionPath, new String[] {
            "git", "branch", "--all"});
        final CommandResult commandResult = command.execute();
        return new GitBranchAllParser(commandResult).parse();
    }
}
