package org.jcommit.commands.git.status;

import org.jcommit.commands.CommandResult;
import org.jcommit.util.StringParser;

import java.util.ArrayList;
import java.util.List;

final class GitStatusParser {

    private final CommandResult commandResult;
    private final String[] lines;
    private final List<GitStatusFileInfo> fileInfos;
    private final List<String> untrackedFiles;

    GitStatusParser(CommandResult commandResult) {
        this.commandResult = commandResult;
        this.lines = commandResult.getOutput().split("\n");
        this.fileInfos = new ArrayList<>();
        this.untrackedFiles = new ArrayList<>();
    }

    GitStatusResult parse() {
        parseChanges("Changes to be committed:", true);
        parseChanges("Changes not staged for commit:", false);
        parseUntrackedFiles();
        return new GitStatusResult(this.commandResult, this.fileInfos, this.untrackedFiles);
    }

    private void parseChanges(String blockStart, boolean stagedFlag) {
        boolean startParsing = false;
        for (final String line : this.lines) {
            if (!startParsing) {
                if (line.startsWith(blockStart))
                    startParsing = true;

                continue;
            }

            if (StringParser.startsWithIgnoreWhitespace(line, "("))
                continue;

            if (line.isBlank())
                return;

            final StringParser stringParser = new StringParser(line);
            stringParser.skipWhitespace();

            final String changeTypePrefix = stringParser.readUntilChar(':', true);
            final GitStatusChangeType changeType = GitStatusChangeType.getByPrefix(changeTypePrefix);
            stringParser.skipWhitespace();

            final String gitFilePath = stringParser.readUntilEnd();
            this.fileInfos.add(new GitStatusFileInfo(gitFilePath, changeType, stagedFlag));
        }
    }

    private void parseUntrackedFiles() {
        boolean startParsing = false;
        for (final String line : this.lines) {
            if (!startParsing) {
                if (line.startsWith("Untracked files:"))
                    startParsing = true;

                continue;
            }

            if (StringParser.startsWithIgnoreWhitespace(line, "("))
                continue;

            if (line.isBlank())
                return;

            final StringParser stringParser = new StringParser(line);
            stringParser.skipWhitespace();
            final String gitFilePath = stringParser.readUntilEnd();
            this.untrackedFiles.add(gitFilePath);
        }
    }
}
