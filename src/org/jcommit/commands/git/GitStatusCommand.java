package org.jcommit.commands.git;

import org.jcommit.commands.Command;
import org.jcommit.commands.CommandResult;
import org.jcommit.util.StringParser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GitStatusCommand {

    public enum GitStatusChangeType {
        NEW("new file:"),
        MODIFIED("modified:");

        private final String prefix;

        GitStatusChangeType(String prefix) {
            this.prefix = prefix;
        }

        private static GitStatusChangeType getByPrefix(String prefix) {
            for (GitStatusChangeType changeType : values())
                if (changeType.prefix.equals(prefix))
                    return changeType;

            return null;
        }
    }

    public static class GitStatusFileInfo {

        private final String gitFilePath;
        private final GitStatusChangeType changeType;

        private GitStatusFileInfo(String gitFilePath, GitStatusChangeType changeType) {
            this.gitFilePath = gitFilePath;
            this.changeType = changeType;
        }

        public String getGitFilePath() {
            return this.gitFilePath;
        }

        public GitStatusChangeType getChangeType() {
            return this.changeType;
        }
    }

    public static class GitStatusResult {

        private final CommandResult commandResult;
        private final List<GitStatusFileInfo> fileInfos;

        private GitStatusResult(CommandResult commandResult, List<GitStatusFileInfo> fileInfos) {
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

    private final File executionPath;

    public GitStatusCommand(File executionPath) {
        this.executionPath = executionPath;
    }

    public GitStatusResult execute() throws IOException, InterruptedException {
        final Command command = new Command(this.executionPath, new String[] {"git", "status"});
        final CommandResult result = command.execute();
        final String[] outputLines = result.getOutput().split("\n");
        final List<GitStatusFileInfo> fileInfos = new ArrayList<>();

        for (String line : outputLines) {
            final GitStatusFileInfo fileInfo = parseLine(line);

            if (fileInfo != null)
                fileInfos.add(fileInfo);
        }

        return new GitStatusResult(result, fileInfos);
    }

    private GitStatusFileInfo parseLine(String line) {
        final StringParser stringParser = new StringParser(line);
        stringParser.skipWhitespace();

        if (stringParser.isFinished())
            return null;

        final String prefix = stringParser.readUntilWhitespace();
        final GitStatusChangeType changeType = GitStatusChangeType.getByPrefix(prefix);

        if (changeType == null || stringParser.isFinished())
            return null;

        stringParser.skipWhitespace();

        if (stringParser.isFinished())
            return null;

        final String gitFilePath = stringParser.readUntilEnd();
        return new GitStatusFileInfo(gitFilePath, changeType);
    }
}
