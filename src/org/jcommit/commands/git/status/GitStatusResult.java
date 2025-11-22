package org.jcommit.commands.git.status;

import org.jcommit.commands.CommandResult;

import java.util.List;

public final class GitStatusResult {

    private final CommandResult commandResult;
    private final List<GitStatusFileInfo> fileInfos;

    GitStatusResult(CommandResult commandResult, List<GitStatusFileInfo> fileInfos) {
        this.commandResult = commandResult;
        this.fileInfos = fileInfos;
    }

    public CommandResult getCommandResult() {
        return this.commandResult;
    }

    public List<GitStatusFileInfo> getFileInfos() {
        return this.fileInfos;
    }
}
