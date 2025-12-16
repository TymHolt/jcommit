package org.jcommit.gui.center.stagelist;

import org.jcommit.commands.git.status.GitChangeType;

public final class StageItem {

    public final String gitFilePath;
    public final GitChangeType changeType;

    public StageItem(String gitFilePath, GitChangeType changeType) {
        this.gitFilePath = gitFilePath;
        this.changeType = changeType;
    }
}
