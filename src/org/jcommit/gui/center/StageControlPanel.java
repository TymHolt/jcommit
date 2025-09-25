package org.jcommit.gui.center;

import javax.swing.*;
import java.awt.*;

final class StageControlPanel extends JPanel {

    private final StagePanel unstagedPanel;
    private final StagePanel stagedPanel;
    private final JSplitPane splitPane;

    StageControlPanel() {
        super();
        setLayout(new BorderLayout());

        this.unstagedPanel = new StagePanel("Unstaged", new JButton("Stage all"));
        this.stagedPanel = new StagePanel("Staged", new JButton("Unstage all"));

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, this.unstagedPanel,
            this.stagedPanel);
        add(splitPane, BorderLayout.CENTER);
    }

    void init() {
        this.splitPane.setDividerLocation(getWidth() / 2);
    }
}
