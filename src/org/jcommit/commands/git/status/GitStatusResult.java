package org.jcommit.commands.git.status;

import org.jcommit.commands.CommandResult;

import java.util.List;

public final class GitStatusResult {

    private final CommandResult commandResult;
    private final List<GitStatusFileInfo> fileInfos;
    private final List<String> untrackedFiles;

    GitStatusResult(CommandResult commandResult, List<GitStatusFileInfo> fileInfos, List<String> untrackedFiles) {
        this.commandResult = commandResult;
        this.fileInfos = fileInfos;
        this.untrackedFiles = untrackedFiles;
    }

    public CommandResult getCommandResult() {
        return this.commandResult;
    }

    public List<GitStatusFileInfo> getFileInfos() {
        return this.fileInfos;
    }

    public List<String> getUntrackedFiles() {
        return this.untrackedFiles;
    }
}
