package org.jcommit.core;

import org.jcommit.commands.CommandResult;
import org.jcommit.commands.git.add.GitAddCommand;
import org.jcommit.commands.git.commit.GitCommitCommand;
import org.jcommit.commands.git.fetch.GitFetchCommand;
import org.jcommit.commands.git.pull.GitPullCommand;
import org.jcommit.commands.git.push.GitPushUCommand;
import org.jcommit.commands.git.restore.GitRestoreCommand;
import org.jcommit.commands.git.status.GitStatusResult;
import org.jcommit.gui.GuiUtil;
import org.jcommit.gui.MainView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class Context {

    private final MainView mainView;
    private final List<Project> openedProjects;
    private Project currentProject;

    public Context() {
        this.openedProjects = new ArrayList<>();
        this.mainView = new MainView(this);
        this.mainView.init();
        this.currentProject = null;
    }

    public void openProject(Project project) {
        for (Project openedProject : this.openedProjects) {
            if (openedProject.isSameProject(project)) {
                // Project already opened
                makeProjectCurrent(openedProject);
                break;
            }
        }

        this.openedProjects.add(project);
        this.mainView.notifyOpenProject(project);
        makeProjectCurrent(project);
    }

    public void closeProject(Project project) {
        if (!openedProjects.contains(project))
            return;

        this.openedProjects.remove(project);
        this.mainView.notifyCloseProject(project);
    }

    public void makeProjectCurrent(Project project) {
        // Project already current
        if (this.currentProject == project)
            return;

        this.currentProject = project;
        fetchStatus();
        this.mainView.notifyMakeProjectCurrent(project);
    }

    public void fetchStatus() {
        if (this.currentProject == null)
            return;

        this.currentProject.fetchStatus();
        fetchBranches();
        this.mainView.notifyFetchStatus(currentProject);
    }

    public void fetchBranches() {
        if (this.currentProject == null)
            return;

        this.currentProject.fetchBranches();
    }

    public void stage(List<String> gitFilePaths) {
        if (currentProject == null)
            return;

        final File projectFile = this.currentProject.getFile();
        final GitAddCommand gitAddCommand = new GitAddCommand(projectFile, gitFilePaths);

        try {
            final CommandResult result = gitAddCommand.execute();

            if (result.getExitCode() != 0)
                throw new RuntimeException("Git exited with error code");
        } catch (Exception exception) {
            GuiUtil.popupError(exception.getMessage());
        }

        fetchStatus();
    }

    public void unstage(List<String> gitFilePaths) {
        if (currentProject == null)
            return;

        final File projectFile = this.currentProject.getFile();
        final GitRestoreCommand gitRestoreCommand = new GitRestoreCommand(projectFile,
            gitFilePaths, true);

        try {
            final CommandResult result = gitRestoreCommand.execute();

            if (result.getExitCode() != 0)
                throw new RuntimeException("Git exited with error code");
        } catch (Exception exception) {
            GuiUtil.popupError(exception.getMessage());
        }

        fetchStatus();
    }

    public void commit(String message) {
        if (currentProject == null)
            return;

        final File projectFile = this.currentProject.getFile();
        final GitCommitCommand gitCommitCommand = new GitCommitCommand(projectFile, message);

        try {
            final CommandResult result = gitCommitCommand.execute();

            if (result.getExitCode() != 0)
                throw new RuntimeException("Git exited with error code");
        } catch (Exception exception) {
            GuiUtil.popupError(exception.getMessage());
        }

        fetchStatus();
    }

    public void push(String remote, String localBranch) {
        if (currentProject == null)
            return;

        final File projectFile = this.currentProject.getFile();
        final GitPushUCommand gitPushUCommand = new GitPushUCommand(projectFile, remote,
            localBranch);

        try {
            final CommandResult result = gitPushUCommand.execute();

            if (result.getExitCode() != 0)
                throw new RuntimeException("Git exited with error code");
        } catch (Exception exception) {
            GuiUtil.popupError(exception.getMessage());
        }

        fetchStatus();
    }

    public void pull() {
        if (currentProject == null)
            return;

        final File projectFile = this.currentProject.getFile();
        final GitPullCommand gitPullCommand = new GitPullCommand(projectFile);

        try {
            final CommandResult result = gitPullCommand.execute();

            if (result.getExitCode() != 0)
                throw new RuntimeException("Git exited with error code");
        } catch (Exception exception) {
            GuiUtil.popupError(exception.getMessage());
        }

        fetchStatus();
    }

    public void fetch() {
        if (currentProject == null)
            return;

        final File projectFile = this.currentProject.getFile();
        final GitFetchCommand gitFetchCommand = new GitFetchCommand(projectFile);

        try {
            final CommandResult result = gitFetchCommand.execute();

            if (result.getExitCode() != 0)
                throw new RuntimeException("Git exited with error code");
        } catch (Exception exception) {
            GuiUtil.popupError(exception.getMessage());
        }

        fetchStatus();
    }

    public MainView getMainView() {
        return this.mainView;
    }

    public List<Project> getOpenedProjects() {
        return new ArrayList<>(this.openedProjects);
    }

    public Project getCurrentProject() {
        return this.currentProject;
    }
}
