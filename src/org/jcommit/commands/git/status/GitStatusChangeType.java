package org.jcommit.commands.git.status;

public enum GitStatusChangeType {
    UNKNOWN(""),
    NEW("new file"),
    MODIFIED("modified"),
    RENAMED("renamed");

    private final String prefix;

    GitStatusChangeType(String prefix) {
        this.prefix = prefix;
    }

    static GitStatusChangeType getByPrefix(String prefix) {
        for (GitStatusChangeType changeType : values())
            if (changeType.prefix.equals(prefix))
                return changeType;

        return UNKNOWN;
    }
}
