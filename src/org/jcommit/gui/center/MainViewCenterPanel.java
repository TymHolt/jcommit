package org.jcommit.gui.center;

import org.jcommit.core.Project;

import javax.swing.*;
import java.awt.*;

public final class MainViewCenterPanel extends JPanel {

    private final StageControlPanel stageControlPanel;

    public MainViewCenterPanel() {
        super();
        setLayout(new BorderLayout());

        final ControlPanel controlPanel = new ControlPanel();
        add(controlPanel, BorderLayout.PAGE_START);
        stageControlPanel = new StageControlPanel();
        add(stageControlPanel, BorderLayout.CENTER);
    }

    public void showProject(Project project) {

    }

    public void init() {
        this.stageControlPanel.init();
    }
}
