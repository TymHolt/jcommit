package org.jcommit.gui;

import org.jcommit.Main;
import org.jcommit.core.Context;
import org.jcommit.core.Project;
import org.jcommit.gui.center.MainViewCenterPanel;
import org.jcommit.gui.menu.GitMenu;
import org.jcommit.gui.menu.HelpMenu;
import org.jcommit.gui.menu.ProjectMenu;
import org.jcommit.gui.side.ProjectListPanel;

import javax.swing.*;
import java.awt.*;

public final class MainView extends JFrame {

    private final Context context;
    private final ProjectListPanel projectListPanel;
    private final MainViewCenterPanel centerPanel;
    private final GitMenu gitMenu;

    public MainView(Context context) {
        super(Main.SOFTWARE_NAME + " " + Main.getVersionName());
        this.context = context;
        setLayout(new BorderLayout());

        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width / 2, screenSize.height / 2);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ------------------------------------------------------------

        final JMenuBar menuBar = new JMenuBar();

        menuBar.add(new ProjectMenu(this));
        this.gitMenu = new GitMenu(this);
        menuBar.add(gitMenu);
        menuBar.add(new HelpMenu(this));

        add(menuBar, BorderLayout.PAGE_START);

        // ------------------------------------------------------------

        this.projectListPanel = new ProjectListPanel(this.context);
        this.centerPanel = new MainViewCenterPanel(this);
        final JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
            this.projectListPanel, this.centerPanel);
        splitPane.setDividerLocation(getWidth() / 5);
        add(splitPane, BorderLayout.CENTER);

        // ------------------------------------------------------------

        updateGui();
        setVisible(true);
    }

    public void notifyOpenProject(Project project) {
        this.projectListPanel.notifyOpenProject(project);
        updateGui();
    }

    public void notifyCloseProject(Project project) {
        this.projectListPanel.notifyCloseProject(project);
        updateGui();
    }

    public void notifyMakeProjectCurrent(Project project) {
        this.projectListPanel.notifyMakeProjectCurrent(project);
        updateGui();
    }

    public Context getContext() {
        return this.context;
    }

    public void updateGui() {
        this.centerPanel.updateGui();
        this.gitMenu.updateGui();
    }

    public void initGui() {
        this.centerPanel.initGui();
    }
}
