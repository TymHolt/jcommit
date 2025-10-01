package org.jcommit.commands.git.status;

public final class GitStatusFileInfo {

    private final String gitFilePath;
    private final GitStatusChangeType changeType;
    private final boolean staged;

    GitStatusFileInfo(String gitFilePath, GitStatusChangeType changeType, boolean staged) {
        this.gitFilePath = gitFilePath;
        this.changeType = changeType;
        this.staged = staged;
    }

    public String getGitFilePath() {
        return this.gitFilePath;
    }

    public GitStatusChangeType getChangeType() {
        return this.changeType;
    }

    public boolean isStaged() {
        return this.staged;
    }
}
