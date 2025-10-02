package org.jcommit.core;

import org.jcommit.commands.git.status.GitStatusCommand;
import org.jcommit.commands.git.status.GitStatusResult;
import org.jcommit.gui.GuiUtil;

import java.io.File;

public final class Project {

    public static boolean canBeProject(File file) {
        return file != null && file.exists() && file.isDirectory();
    }

    private final File file;
    private GitStatusResult statusResult;

    public Project(File file) {
        if (!canBeProject(file))
            throw new IllegalArgumentException("File " + file.getAbsolutePath() +
                " can not be used as project");

        this.file = file;
        this.statusResult = null;
    }

    public boolean isSameProject(Project project) {
        return this.file.getAbsolutePath().equals(project.file.getAbsolutePath());
    }

    public GitStatusResult fetchStatus() {
        final GitStatusCommand statusCommand = new GitStatusCommand(this.file);

        try {
            final GitStatusResult statusResult = statusCommand.execute();
            this.statusResult = statusResult;
            return statusResult;
        } catch (Exception exception) {
            GuiUtil.popupError(exception.getMessage());
            this.statusResult = null;
            return null;
        }
    }

    public File getFile() {
        return this.file;
    }

    public GitStatusResult getStatusResult() {
        return this.statusResult;
    }
}
