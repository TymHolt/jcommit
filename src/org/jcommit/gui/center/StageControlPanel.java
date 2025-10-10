package org.jcommit.gui.center;

import org.jcommit.commands.git.status.GitStatusFileInfo;
import org.jcommit.commands.git.status.GitStatusResult;
import org.jcommit.core.Context;
import org.jcommit.core.Project;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

final class StageControlPanel extends JPanel {

    private final Context context;
    private final StagePanel unstagedPanel;
    private final StagePanel stagedPanel;
    private final JSplitPane splitPane;

    StageControlPanel(MainViewCenterPanel mainViewCenterPanel) {
        super();
        setLayout(new BorderLayout());
        this.context = mainViewCenterPanel.getMainView().getContext();

        final JButton stageButton = new JButton("Stage");
        final JButton stageAllButton = new JButton("Stage all");
        final JButton unstageButton = new JButton("Unstage");
        final JButton unstageAllButton = new JButton("Unstage all");

        this.unstagedPanel = new StagePanel("Unstaged", stageButton, stageAllButton);
        this.stagedPanel = new StagePanel("Staged", unstageButton, unstageAllButton);

        stageButton.addActionListener(actionEvent -> {
            final List<String> paths = this.unstagedPanel.getSelectedPaths();
            this.context.stage(paths);
        });

        stageAllButton.addActionListener(actionEvent -> {
            final List<String> paths = this.unstagedPanel.getAllPaths();
            this.context.stage(paths);
        });

        unstageButton.addActionListener(actionEvent -> {
            final List<String> paths = this.stagedPanel.getSelectedPaths();
            this.context.unstage(paths);
        });

        unstageAllButton.addActionListener(actionEvent -> {
            final List<String> paths = this.stagedPanel.getAllPaths();
            this.context.unstage(paths);
        });

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, this.unstagedPanel,
            this.stagedPanel);
        add(splitPane, BorderLayout.CENTER);
    }

    void updateGui() {
        final List<String> unstagedElements = new ArrayList<>();
        final List<String> stagedElements = new ArrayList<>();
        final Project project = this.context.getCurrentProject();
        final boolean showProject = project != null;

        this.stagedPanel.enableControls(showProject);
        this.unstagedPanel.enableControls(showProject);

        if (showProject) {
            final GitStatusResult gitStatusResult = project.getStatusResult();

            for (GitStatusFileInfo fileInfo : gitStatusResult.getFileInfos()) {
                if (fileInfo.isStaged())
                    stagedElements.add(fileInfo.getGitFilePath());
                else
                    unstagedElements.add(fileInfo.getGitFilePath());
            }

            for (String gitFilePath : gitStatusResult.getUntrackedFiles())
                unstagedElements.add(gitFilePath);
        }

        this.unstagedPanel.setElements(unstagedElements);
        this.stagedPanel.setElements(stagedElements);

        revalidate();
        repaint();
    }

    void initGui() {
        this.splitPane.setDividerLocation(getWidth() / 2);
    }
}
