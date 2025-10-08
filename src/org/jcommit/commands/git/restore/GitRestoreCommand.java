package org.jcommit.commands.git.restore;

import org.jcommit.commands.Command;
import org.jcommit.commands.CommandResult;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
        final List<String> arguments = new ArrayList<>();
        arguments.add("git");
        arguments.add("restore");

        if (this.staged)
            arguments.add("--staged");

        for (String gitFilePath : this.gitFilePaths)
            arguments.add(gitFilePath);

        final Command command = new Command(this.executionPath, arguments);
        return command.execute();
    }
}
