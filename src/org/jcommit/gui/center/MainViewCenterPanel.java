package org.jcommit.gui.center;

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
        stageControlPanel = new StageControlPanel(this);
        add(stageControlPanel, BorderLayout.CENTER);
    }

    public void notifyFetchStatus(Project project) {
        this.stageControlPanel.notifyFetchStatus(project);
    }

    public void init() {
        this.stageControlPanel.init();
    }

    public MainView getMainView() {
        return this.mainView;
    }
}
