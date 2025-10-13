package org.jcommit.gui.side;

import org.jcommit.Log;
import org.jcommit.commands.CommandResult;
import org.jcommit.commands.git.clone.GitCloneCommand;
import org.jcommit.core.Project;
import org.jcommit.gui.popup.ClonePopup;
import org.jcommit.gui.util.FileSelectionOption;
import org.jcommit.gui.util.FileSelectionResult;
import org.jcommit.gui.util.GuiUtil;
import org.jcommit.gui.MainView;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public final class MainViewSidePanel extends JPanel {

    private final ProjectListPanel projectListPanel;
    private final MainView mainView;

    public MainViewSidePanel(MainView mainView) {
        super();
        this.mainView = mainView;
        setLayout(new BorderLayout());

        //  vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv

        final JPanel buttonContainer = new JPanel();
        buttonContainer.setLayout(new BorderLayout());

        final JButton addProjectButton = new JButton("Open project");
        addProjectButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addProjectButton.addActionListener(actionEvent -> {
            final FileSelectionResult fileSelectionResult = GuiUtil.popupSelectDirectory(
                "Open project");

            if (fileSelectionResult.getOption() != FileSelectionOption.APPROVE)
                return;

            final File projectFile = fileSelectionResult.getFile();
            if (!Project.canBeProject(projectFile)) {
                GuiUtil.popupInfo("File can not be opened as project");
                return;
            }

            final Project project = new Project(projectFile);
            this.mainView.getContext().openProject(project);
        });
        buttonContainer.add(addProjectButton, BorderLayout.PAGE_START);

        final JButton cloneProjectButton = new JButton("Clone project");
        cloneProjectButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        cloneProjectButton.addActionListener(actionEvent -> {
            final ClonePopup clonePopup = new ClonePopup(mainView, "Clone project");
            if (clonePopup.wasCanceled())
                return;

            final File parentDirectory = clonePopup.getParentDirectory();
            if (parentDirectory == null || !parentDirectory.isDirectory() ||
                !parentDirectory.exists()) {
                GuiUtil.popupError("No directory selected");
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
                GuiUtil.popupError(exception.getMessage());
            }

            final String projectName = getProjectNameFromUrl(url);
            final String projectPath = parentDirectory.getAbsolutePath() + "/" + projectName;
            final File projectFile = new File(projectPath);

            if (!Project.canBeProject(projectFile)) {
                GuiUtil.popupInfo("File could not be opened as project");
                return;
            }

            final Project project = new Project(projectFile);
            this.mainView.getContext().openProject(project);
        });
        buttonContainer.add(cloneProjectButton, BorderLayout.PAGE_END);

        add(buttonContainer, BorderLayout.PAGE_START);

        //  ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
        //  vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv

        this.projectListPanel = new ProjectListPanel(this);
        add(this.projectListPanel, BorderLayout.CENTER);

        //  ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
        //  vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv

        final JButton settingsButton = new JButton("Settings");
        settingsButton.addActionListener(actionEvent -> {
            GuiUtil.popupInfo("Settings not implemented yet");
        });
        add(settingsButton, BorderLayout.PAGE_END);

        //  ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
    }

    private static String getProjectNameFromUrl(String url) {
        final String[] elements = url.split("/");
        String projectName = elements[elements.length - 1];

        if (projectName.endsWith(".git"))
            projectName = projectName.substring(0, projectName.length() - ".git".length());

        return projectName;
    }

    public void notifyOpenProject(Project project) {
        this.projectListPanel.notifyOpenProject(project);
    }

    public void notifyCloseProject(Project project) {
        this.projectListPanel.notifyCloseProject(project);
    }

    public void notifyMakeProjectCurrent(Project project) {
        this.projectListPanel.notifyMakeProjectCurrent(project);
    }

    public MainView getMainView() {
        return mainView;
    }
}
