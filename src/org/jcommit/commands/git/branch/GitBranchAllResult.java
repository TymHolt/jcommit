package org.jcommit.commands.git.branch;

import org.jcommit.commands.CommandResult;
import java.util.List;

public final class GitBranchAllResult {

    private final CommandResult commandResult;
    private final List<String> localBranches;
    private final List<String> remoteBranches;
    private final String currentBranch;

    GitBranchAllResult(CommandResult commandResult, List<String> localBranches,
        List<String> remoteBranches, String currentBranch) {
        this.commandResult = commandResult;
        this.localBranches = localBranches;
        this.remoteBranches = remoteBranches;
        this.currentBranch = currentBranch;
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

    public String getCurrentBranch() {
        return this.currentBranch;
    }
}
