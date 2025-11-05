package org.jcommit.core;

import org.jcommit.Log;
import org.jcommit.commands.CommandResult;
import org.jcommit.commands.git.add.GitAddCommand;
import org.jcommit.commands.git.checkout.GitCheckoutCommand;
import org.jcommit.commands.git.commit.GitCommitCommand;
import org.jcommit.commands.git.fetch.GitFetchCommand;
import org.jcommit.commands.git.help.GitHelpCommand;
import org.jcommit.commands.git.pull.GitPullCommand;
import org.jcommit.commands.git.push.GitPushUCommand;
import org.jcommit.commands.git.restore.GitRestoreCommand;
import org.jcommit.gui.theme.DarkTheme;
import org.jcommit.gui.theme.LightTheme;
import org.jcommit.gui.theme.Theme;
import org.jcommit.gui.util.GuiUtil;
import org.jcommit.gui.MainView;
import org.jcommit.util.FileLoader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class Context {

    private final MainView mainView;
    private final Theme theme;
    private final List<Project> openedProjects;
    private final Settings settings;
    private Project currentProject;

    public Context() {
        this.openedProjects = new ArrayList<>();
        this.currentProject = null;
        this.settings = new Settings();
        this.settings.load();
        applySettings();

        // Determine theme at startup, so changes only take effect after restart
        this.theme = this.settings.getUseDarkTheme() ? new DarkTheme() : new LightTheme();
        Theme.applyTheme(this.theme);
        this.mainView = new MainView(this);
        this.mainView.initGui();

        // Try to detect working git installation by running git help
        try {
            final int exitCode = new GitHelpCommand(new File(".")).execute().getExitCode();
            if (exitCode != 0)
                throw new RuntimeException("Git could not be run");
        } catch (Exception e) {
            GuiUtil.popupError("Failed to find working git installation");
        }

        loadOpenedProjects();
    }

    private final String OPENED_PROJECT_FILE = "projects.txt";

    private void loadOpenedProjects() {
        final File file = new File(OPENED_PROJECT_FILE);
        if (!file.exists() || !file.isFile()) {
            Log.info("Opened projects could not be loaded");
            return;
        }

        try {
            final List<String> projectPaths = FileLoader.loadFileLines(file);

            for (String projectPath : projectPaths) {
                final File projectFile = new File(projectPath);
                if (!Project.canBeProject(projectFile)) {
                    Log.error("Failed to load project " + projectPath);
                    continue;
                }

                openProject(new Project(projectFile), false);
            }
        } catch (IOException exception) {
            Log.error("Error while loading opened projects: " + exception.getMessage());
        }
    }

    private void saveOpenedProjects() {
        try {
            final File file = new File(OPENED_PROJECT_FILE);
            file.createNewFile();

            if (!file.isFile()) {
                Log.info("Opened projects could not be saved");
                return;
            }

            final List<String> projectPaths = new ArrayList<>();
            for (Project project : this.openedProjects)
                projectPaths.add(project.getFile().getAbsolutePath());

            FileLoader.writeFileLines(file, projectPaths);
        } catch (IOException exception) {
            Log.error("Error while saving opened projects: " + exception.getMessage());
        }
    }

    public void openProject(Project project) {
        openProject(project, true);
    }

    public void openProject(Project project, boolean saveList) {
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
        Log.info("Project " + project.getFile().getAbsolutePath() + " opened");

        if (saveList)
            saveOpenedProjects();
    }

    public void closeProject(Project project) {
        if (!openedProjects.contains(project))
            return;

        this.openedProjects.remove(project);
        this.mainView.notifyCloseProject(project);

        if (this.currentProject == project)
            makeProjectCurrent(null);

        saveOpenedProjects();
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

        Log.info("Fetching status...");
        this.currentProject.fetchStatus();
        fetchBranches();
        this.mainView.updateGui();
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

        Log.info("Pushing...");

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

        Log.info("Pulling...");

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

    public void checkoutBranch(String branchName) {
        if (currentProject == null)
            return;

        Log.info("Checkout branch " + branchName);

        final File executionPath = currentProject.getFile();
        final GitCheckoutCommand checkoutCommand = new GitCheckoutCommand(executionPath,
            branchName);

        try {
            final CommandResult result = checkoutCommand.execute();

            if (result.getExitCode() != 0)
                throw new RuntimeException("Could not check out branch " + branchName);
        } catch (Exception exception) {
            GuiUtil.popupError(exception.getMessage());
        }

        fetchStatus();
    }

    public void applySettings() {
        Log.setPrintDebug(this.settings.getPrintDebug());
        Log.setPrintToFile(this.settings.getPrintToFile());
        Log.setPrintTimeStamp(this.settings.getPrintTimeStamp());
        Log.setLogFilePath(this.settings.getLogFilePath());
        this.settings.save();
    }

    public Settings getSettings() {
        return this.settings;
    }

    public Theme getTheme() {
        return this.theme;
    }

    public Project getCurrentProject() {
        return this.currentProject;
    }
}
