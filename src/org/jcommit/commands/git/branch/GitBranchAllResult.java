package org.jcommit.commands.git.branch;

import org.jcommit.commands.CommandResult;
import java.util.List;

public final class GitBranchAllResult {

    private final CommandResult commandResult;
    private final List<String> localBranches;
    private final List<String> remoteBranches;

    GitBranchAllResult(CommandResult commandResult, List<String> localBranches,
        List<String> remoteBranches) {
        this.commandResult = commandResult;
        this.localBranches = localBranches;
        this.remoteBranches = remoteBranches;
    }

    public CommandResult getCommandResult() {
        return this.commandResult;
    }

    public List<String> getLocalBranches() {
        return this.localBranches;
    }

    public List<String> getRemoteBranches() {
        return this.remoteBranches;
    }
}
