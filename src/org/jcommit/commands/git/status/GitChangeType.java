package org.jcommit.commands.git.status;

public enum GitChangeType {

    NONE(' '),
    MODIFIED('M'),
    ADDED('A'),
    DELETED('D'),
    RENAMED('R'),
    COPIED('C'),
    UNTRACKED('?'),
    IGNORED('!'),
    CONFLICTED('U');

    private final char identifier;

    GitChangeType(char identifier) {
        this.identifier = identifier;
    }

    static GitChangeType getByIdentifier(char identifier) {
        for (GitChangeType changeType : values())
            if (changeType.identifier == identifier)
                return changeType;

        throw new IllegalArgumentException("Unknown identifier '" + identifier + "'");
    }
}
