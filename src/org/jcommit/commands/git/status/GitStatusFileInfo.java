package org.jcommit.commands.git.status;

import java.util.Objects;

public final class GitStatusFileInfo {

    public final String gitFilePath;
    public final GitChangeType unstagedChange;
    public final GitChangeType stagedChange;

    GitStatusFileInfo(String gitFilePath, GitChangeType unstagedChange,
        GitChangeType stagedChange) {
        Objects.requireNonNull(unstagedChange, "Git file path is null");
        Objects.requireNonNull(unstagedChange, "Unstaged change is null");
        Objects.requireNonNull(stagedChange, "Staged change is null");

        this.gitFilePath = gitFilePath;
        this.unstagedChange = unstagedChange;
        this.stagedChange = stagedChange;
    }
}
