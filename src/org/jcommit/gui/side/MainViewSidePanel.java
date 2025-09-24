package org.jcommit.gui.side;

import org.jcommit.core.Project;
import org.jcommit.gui.GuiUtil;

import javax.swing.*;
import java.awt.*;

public final class MainViewSidePanel extends JPanel {

    private final ProjectListPanel projectListPanel;

    public MainViewSidePanel() {
        super();
        setLayout(new BorderLayout());

        this.projectListPanel = new ProjectListPanel();
        add(this.projectListPanel, BorderLayout.CENTER);

        final JButton addProjectButton = new JButton("Add project...");
        addProjectButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addProjectButton.addActionListener(actionEvent -> {
            GuiUtil.popupInfo("Add project not implemented yet");
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
}
