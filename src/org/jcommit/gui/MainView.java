package org.jcommit.gui;

import org.jcommit.Main;
import org.jcommit.commands.git.status.GitStatusResult;
import org.jcommit.core.Context;
import org.jcommit.core.Project;
import org.jcommit.gui.center.MainViewCenterPanel;
import org.jcommit.gui.side.MainViewSidePanel;

import javax.swing.*;
import java.awt.*;

public final class MainView extends JFrame {

    private final Context context;
    private final MainViewSidePanel sidePanel;
    private final MainViewCenterPanel centerPanel;
    private GitStatusResult lastStatusResult;

    public MainView(Context context) {
        super(Main.SOFTWARE_NAME + " " + Main.getVersionName());
        this.context = context;

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

    public void notifyOpenProject(Project project) {
        this.sidePanel.notifyOpenProject(project);
        updateGui();
    }

    public void notifyCloseProject(Project project) {
        this.sidePanel.notifyCloseProject(project);
        updateGui();
    }

    public void notifyMakeProjectCurrent(Project project) {
        this.sidePanel.notifyMakeProjectCurrent(project);
        updateGui();
    }

    public Context getContext() {
        return this.context;
    }

    public void updateGui() {
        this.centerPanel.updateGui();
    }

    public void initGui() {
        this.centerPanel.initGui();
    }
}
