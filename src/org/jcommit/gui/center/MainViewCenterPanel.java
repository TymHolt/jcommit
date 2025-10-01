package org.jcommit.gui.center;

import org.jcommit.commands.git.status.GitStatusResult;
import org.jcommit.core.Project;
import org.jcommit.gui.MainView;

import javax.swing.*;
import java.awt.*;

public final class MainViewCenterPanel extends JPanel {

    private final MainView mainView;
    private final StageControlPanel stageControlPanel;

    public MainViewCenterPanel(MainView mainView) {
        super();
        setLayout(new BorderLayout());
        this.mainView = mainView;

        final ControlPanel controlPanel = new ControlPanel(this);
        add(controlPanel, BorderLayout.PAGE_START);
        stageControlPanel = new StageControlPanel();
        add(stageControlPanel, BorderLayout.CENTER);
    }

    public void showProject(Project project) {

    }

    public void updateStatus(GitStatusResult gitStatusResult) {
        this.stageControlPanel.updateStatus(gitStatusResult);
    }

    public void init() {
        this.stageControlPanel.init();
    }

    public MainView getMainView() {
        return this.mainView;
    }
}
