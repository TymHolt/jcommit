package org.jcommit.gui;

import org.jcommit.Main;
import org.jcommit.core.Project;
import org.jcommit.gui.center.MainViewCenterPanel;
import org.jcommit.gui.side.MainViewSidePanel;

import javax.swing.*;
import java.awt.*;

public final class MainView extends JFrame {

    private final MainViewSidePanel sidePanel;
    private final MainViewCenterPanel centerPanel;

    public MainView() {
        super(Main.SOFTWARE_NAME + " " + Main.getVersionName());

        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width / 2, screenSize.height / 2);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.sidePanel = new MainViewSidePanel(this);
        this.centerPanel = new MainViewCenterPanel();
        final JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, this.sidePanel,
            this.centerPanel);
        splitPane.setDividerLocation(getWidth() / 5);
        add(splitPane);
        setVisible(true);
    }

    public void showProject(Project project) {
        this.sidePanel.showProject(project);
        this.centerPanel.showProject(project);
    }

    public void hideProject(Project project) {
        this.sidePanel.hideProject(project);
    }

    public void init() {
        this.centerPanel.init();
    }
}
