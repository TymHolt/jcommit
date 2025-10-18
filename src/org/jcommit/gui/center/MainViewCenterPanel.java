package org.jcommit.gui.center;

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

        this.stageControlPanel = new StageControlPanel(this);
        add(stageControlPanel, BorderLayout.CENTER);
    }

    public void updateGui() {
        this.stageControlPanel.updateGui();
    }

    public void initGui() {
        this.stageControlPanel.initGui();
        updateGui();
    }

    public MainView getMainView() {
        return this.mainView;
    }
}
