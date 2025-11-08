package org.jcommit.gui.menu;

import org.jcommit.Log;
import org.jcommit.commands.CommandResult;
import org.jcommit.commands.git.clone.GitCloneCommand;
import org.jcommit.commands.git.init.GitInitCommand;
import org.jcommit.core.Context;
import org.jcommit.core.Project;
import org.jcommit.gui.MainView;
import org.jcommit.gui.popup.ClonePopup;
import org.jcommit.gui.popup.NewPopup;
import org.jcommit.gui.util.FileSelectionOption;
import org.jcommit.gui.util.FileSelectionResult;
import org.jcommit.gui.util.GuiUtil;

import javax.swing.*;
import java.io.File;

public final class ProjectMenu extends JMenu {

    public ProjectMenu(MainView mainView) {
        super("Project");
        final Context context = mainView.getContext();

        // ------------------------------------------------------------

        final JMenuItem newItem = new JMenuItem("New...");
        newItem.addActionListener(actionEvent -> {
            final NewPopup newPopup = new NewPopup(mainView, "New project");
            if (newPopup.wasCanceled())
                return;

            final File projectFile = newPopup.getProjectDirectory();
            if (projectFile == null || !projectFile.isDirectory() ||
                !projectFile.exists()) {
                GuiUtil.popupError(mainView, "No directory selected");
                return;
            }

            Log.info("Init...");

            final GitInitCommand initCommand = new GitInitCommand(projectFile);

            try {
                final CommandResult result = initCommand.execute();

                if (result.getExitCode() != 0)
                    throw new RuntimeException("Git exited with error code");
            } catch (Exception exception) {
                GuiUtil.popupError(mainView, exception.getMessage());
            }

            if (!Project.canBeProject(projectFile)) {
                GuiUtil.popupInfo(mainView, "File could not be opened as project");
                return;
            }

            final Project project = new Project(projectFile);
            context.openProject(project);
        });
        add(newItem);

        // ------------------------------------------------------------

        final JMenuItem openItem = new JMenuItem("Open...");
        openItem.addActionListener(actionEvent -> {
            final FileSelectionResult fileSelectionResult = GuiUtil.popupSelectDirectory(
                "Open project");

            if (fileSelectionResult.getOption() != FileSelectionOption.APPROVE)
                return;

            final File projectFile = fileSelectionResult.getFile();
            if (!Project.canBeProject(projectFile)) {
                GuiUtil.popupInfo(mainView, "File can not be opened as project");
                return;
            }

            final Project project = new Project(projectFile);
            context.openProject(project);
        });
        add(openItem);

        // ------------------------------------------------------------

        final JMenuItem cloneItem = new JMenuItem("Clone...");
        cloneItem.addActionListener(actionEvent -> {
            final ClonePopup clonePopup = new ClonePopup(mainView, "Clone project");
            if (clonePopup.wasCanceled())
                return;

            final File parentDirectory = clonePopup.getParentDirectory();
            if (parentDirectory == null || !parentDirectory.isDirectory() ||
                !parentDirectory.exists()) {
                GuiUtil.popupError(mainView, "No directory selected");
                return;
            }

            Log.info("Cloning...");

            final String url = clonePopup.getUrl();
            final GitCloneCommand cloneCommand = new GitCloneCommand(parentDirectory, url);

            try {
                final CommandResult result = cloneCommand.execute();

                if (result.getExitCode() != 0)
                    throw new RuntimeException("Git exited with error code");
            } catch (Exception exception) {
                GuiUtil.popupError(mainView, exception.getMessage());
            }

            final String projectName = getProjectNameFromUrl(url);
            final String projectPath = parentDirectory.getAbsolutePath() + "/" + projectName;
            final File projectFile = new File(projectPath);

            if (!Project.canBeProject(projectFile)) {
                GuiUtil.popupInfo(mainView, "File could not be opened as project");
                return;
            }

            final Project project = new Project(projectFile);
            context.openProject(project);
        });
        add(cloneItem);
    }

    private static String getProjectNameFromUrl(String url) {
        final String[] elements = url.split("/");
        String projectName = elements[elements.length - 1];

        if (projectName.endsWith(".git"))
            projectName = projectName.substring(0, projectName.length() - ".git".length());

        return projectName;
    }
}
