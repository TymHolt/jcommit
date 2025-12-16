package org.jcommit.commands.git.status;

import org.jcommit.commands.CommandResult;

import java.util.ArrayList;
import java.util.List;

final class GitStatusParser {

    private final CommandResult commandResult;
    private final String[] lines;
    private final List<GitStatusFileInfo> fileInfos;

    GitStatusParser(CommandResult commandResult) {
        this.commandResult = commandResult;

        final String output = commandResult.getOutput();
        if (output.isBlank())
            this.lines = new String[0];
        else
            this.lines = commandResult.getOutput().split("\n");

        this.fileInfos = new ArrayList<>();
    }

    GitStatusResult parse() {
        for (String line : lines)
            parseLine(line);

        return new GitStatusResult(this.commandResult, this.fileInfos);
    }

    private void parseLine(String line) {
        if (line.length() < 4)
            throw new RuntimeException("Line could not be parsed: " + line);

        final GitChangeType staged = GitChangeType.getByIdentifier(line.charAt(0));
        final GitChangeType unstaged = GitChangeType.getByIdentifier(line.charAt(1));
        final String fileName = parseFileName(line);
        this.fileInfos.add(new GitStatusFileInfo(fileName, unstaged, staged));
    }

    private static String parseFileName(String line) {
        final String fileName = line.substring(3);

        final String renameSplit = " -> ";
        if (fileName.contains(renameSplit))
            return fileName.split(renameSplit)[1];

        return fileName;
    }
}
