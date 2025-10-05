package org.jcommit.commands.git.restore;

import org.jcommit.commands.Command;
import org.jcommit.commands.CommandResult;

import java.io.File;
import java.io.IOException;
import java.util.List;

public final class GitRestoreCommand {

    private final File executionPath;
    private final List<String> gitFilePaths;
    private final boolean staged;

    public GitRestoreCommand(File executionPath, List<String> gitFilePaths, boolean staged) {
        this.executionPath = executionPath;
        this.gitFilePaths = gitFilePaths;
        this.staged = staged;
    }

    public CommandResult execute() throws IOException, InterruptedException {
        final String[] arguments = new String[gitFilePaths.size() + 2 + (this.staged ? 1 : 0)];
        int index = 0;
        arguments[index++] = "git";
        arguments[index++] = "restore";

        if (this.staged)
            arguments[index++] = "--staged";

        for (String gitFilePath : this.gitFilePaths)
            arguments[index++] = gitFilePath;

        final Command command = new Command(this.executionPath, arguments);
        return command.execute();
    }
}
