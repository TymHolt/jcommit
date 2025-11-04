package org.jcommit.commands.git.branch;

import org.jcommit.commands.CommandResult;
import org.jcommit.util.StringParser;

import java.util.ArrayList;
import java.util.List;

final class GitBranchAllParser {

    private final CommandResult commandResult;
    private final String[] lines;
    private final List<String> localBranches;
    private final List<String> remoteBranches;
    private String currentBranch;

    GitBranchAllParser(CommandResult commandResult) {
        this.commandResult = commandResult;
        this.lines = commandResult.getOutput().split("\n");
        this.localBranches = new ArrayList<>();
        this.remoteBranches = new ArrayList<>();
        this.currentBranch = "";
    }

    GitBranchAllResult parse() {
        for (String line : this.lines)
            parseLine(line);

        return new GitBranchAllResult(this.commandResult, this.localBranches, this.remoteBranches,
            this.currentBranch);
    }

    private void parseLine(String line) {
        final StringParser stringParser = new StringParser(line);
        stringParser.skipWhitespace();

        final boolean isCurrentBranch = stringParser.getCurrentChar() == '*';
        stringParser.skipAllChar('*');

        stringParser.skipWhitespace();

        final String remotesPrefix = "remotes/";
        final String branchName = stringParser.readUntilEnd();

        if (branchName.startsWith(remotesPrefix)) {
            final String branchNameNoPrefix = branchName.substring(remotesPrefix.length());

            // Exclude info, where remote head is pointing for now
            if (!branchNameNoPrefix.contains("HEAD ->"))
                remoteBranches.add(branchNameNoPrefix);
        } else {
            localBranches.add(branchName);

            if (isCurrentBranch)
                this.currentBranch = branchName;
        }
    }
}
