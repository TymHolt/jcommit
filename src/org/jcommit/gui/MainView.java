package org.jcommit.gui;

import org.jcommit.Log;
import org.jcommit.Main;
import org.jcommit.commands.git.status.GitStatusCommand;
import org.jcommit.commands.git.status.GitStatusFileInfo;
import org.jcommit.commands.git.status.GitStatusResult;
import org.jcommit.core.Project;
import org.jcommit.gui.center.MainViewCenterPanel;
import org.jcommit.gui.side.MainViewSidePanel;

import javax.swing.*;
import java.awt.*;

public final class MainView extends JFrame {

    private final MainViewSidePanel sidePanel;
    private final MainViewCenterPanel centerPanel;
    private Project currentProject = null;
    private GitStatusResult lastStatusResult;

    public MainView() {
        super(Main.SOFTWARE_NAME + " " + Main.getVersionName());

        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width / 2, screenSize.height / 2);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.sidePanel = new MainViewSidePanel(this);
        this.centerPanel = new MainViewCenterPanel(this);
        final JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, this.sidePanel,
            this.centerPanel);
        splitPane.setDividerLocation(getWidth() / 5);
        add(splitPane);
        setVisible(true);
    }

    public void showProject(Project project) {
        this.currentProject = project;
        this.sidePanel.showProject(project);
        this.centerPanel.showProject(project);
    }

    public void hideProject(Project project) {
        if (this.currentProject == project)
            this.currentProject = null;

        this.sidePanel.hideProject(project);
    }

    public void status() {
        if (this.currentProject == null)
            return;

        final GitStatusCommand statusCommand = new GitStatusCommand(this.currentProject.getFile());

        try {
            final GitStatusResult statusResult = statusCommand.execute();
            this.lastStatusResult = statusResult;
            this.centerPanel.updateStatus(statusResult);
        } catch (Exception exception) {
            GuiUtil.popupError(exception.getMessage());
        }
    }

    public void init() {
        this.centerPanel.init();
    }

    public Project getCurrentProject() {
        return this.currentProject;
    }

    public GitStatusResult getLastStatusResult() {
        return this.lastStatusResult;
    }
}
