package org.jcommit.gui.side;

import org.jcommit.Log;
import org.jcommit.core.Project;
import org.jcommit.gui.GuiUtil;
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

        this.projectListPanel = new ProjectListPanel(this);
        add(this.projectListPanel, BorderLayout.CENTER);

        final JButton addProjectButton = new JButton("Add project...");
        addProjectButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addProjectButton.addActionListener(actionEvent -> {
            final JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            final int option = fileChooser.showDialog(this, "Add project");
            if (option != JFileChooser.APPROVE_OPTION)
                return;

            final File projectFile = fileChooser.getSelectedFile();
            if (!Project.canBeProject(projectFile)) {
                GuiUtil.popupInfo("File can not be opened as project");
                return;
            }

            final Project project = new Project(projectFile);
            this.mainView.getContext().openProject(project);
            Log.info("Project " + projectFile.getAbsolutePath() + " added");
        });
        add(addProjectButton, BorderLayout.PAGE_START);

        final JButton settingsButton = new JButton("Settings");
        settingsButton.addActionListener(actionEvent -> {
            GuiUtil.popupInfo("Settings not implemented yet");
        });
        add(settingsButton, BorderLayout.PAGE_END);
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
