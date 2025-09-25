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
            addProject(project);
            Log.info("Project " + projectFile.getAbsolutePath() + " added");
        });
        add(addProjectButton, BorderLayout.PAGE_START);

        final JButton settingsButton = new JButton("Settings");
        settingsButton.addActionListener(actionEvent -> {
            GuiUtil.popupInfo("Settings not implemented yet");
        });
        add(settingsButton, BorderLayout.PAGE_END);
    }

    public void addProject(Project project) {
        this.projectListPanel.addProject(project);
    }

    public void removeProject(Project project) {
        this.projectListPanel.removeProject(project);
    }

    public void showProject(Project project) {
        this.projectListPanel.showProject(project);
    }

    public void hideProject(Project project) {
        this.projectListPanel.hideProject(project);
    }

    public MainView getMainView() {
        return mainView;
    }
}
