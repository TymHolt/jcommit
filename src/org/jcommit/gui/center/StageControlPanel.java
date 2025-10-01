package org.jcommit.gui.center;

import org.jcommit.Log;
import org.jcommit.commands.git.status.GitStatusFileInfo;
import org.jcommit.commands.git.status.GitStatusResult;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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

    public void updateStatus(GitStatusResult gitStatusResult) {
        final List<String> unstagedElements = new ArrayList<>();
        final List<String> stagedElements = new ArrayList<>();

        for (GitStatusFileInfo fileInfo : gitStatusResult.getFileInfos()) {
            if (fileInfo.isStaged())
                stagedElements.add(fileInfo.getGitFilePath());
            else
                unstagedElements.add(fileInfo.getGitFilePath());
        }

        for (String gitFilePath : gitStatusResult.getUntrackedFiles())
            unstagedElements.add(gitFilePath);

        this.unstagedPanel.setElements(unstagedElements);
        this.stagedPanel.setElements(stagedElements);

        revalidate();
        repaint();
    }

    void init() {
        this.splitPane.setDividerLocation(getWidth() / 2);
    }
}
