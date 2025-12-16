package org.jcommit.gui.center;

import org.jcommit.commands.git.branch.GitBranchAllResult;
import org.jcommit.commands.git.status.GitChangeType;
import org.jcommit.commands.git.status.GitStatusFileInfo;
import org.jcommit.commands.git.status.GitStatusResult;
import org.jcommit.core.Context;
import org.jcommit.core.Project;
import org.jcommit.gui.center.stagelist.StageItem;
import org.jcommit.util.ListUtil;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

final class StageControlPanel extends JPanel {

    private final Context context;
    private final JPanel actionPanel;
    private BranchSelectionComponent branchSelection;
    private final StagePanel unstagedPanel;
    private final StagePanel stagedPanel;
    private final JSplitPane splitPane;

    StageControlPanel(MainViewCenterPanel mainViewCenterPanel) {
        super();
        setLayout(new BorderLayout());
        this.context = mainViewCenterPanel.getMainView().getContext();

        // ------------------------------------------------------------

        this.actionPanel = new JPanel();
        this.actionPanel.setLayout(new BorderLayout());

        this.branchSelection = new BranchSelectionComponent(context);
        this.actionPanel.add(this.branchSelection, BorderLayout.LINE_END);

        add(this.actionPanel, BorderLayout.PAGE_START);

        // ------------------------------------------------------------

        final JButton stageButton = new JButton("Stage");
        final JButton stageAllButton = new JButton("Stage all");
        final JButton unstageButton = new JButton("Unstage");
        final JButton unstageAllButton = new JButton("Unstage all");

        this.unstagedPanel = new StagePanel(this.context.getTheme(), "Unstaged", stageButton,
            stageAllButton);
        this.stagedPanel = new StagePanel(this.context.getTheme(), "Staged", unstageButton,
            unstageAllButton);

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
        final List<StageItem> unstagedItems = new ArrayList<>();
        final List<StageItem> stagedItems = new ArrayList<>();
        final Project project = this.context.getCurrentProject();
        final boolean showProject = project != null;

        this.stagedPanel.enableControls(showProject);
        this.unstagedPanel.enableControls(showProject);

        if (showProject) {
            final GitStatusResult gitStatusResult = project.getStatusResult();
            for (GitStatusFileInfo fileInfo : gitStatusResult.getFileInfos()) {
                if (fileInfo.unstagedChange != GitChangeType.NONE &&
                    fileInfo.unstagedChange != GitChangeType.IGNORED) {
                    unstagedItems.add(new StageItem(fileInfo.gitFilePath,
                        fileInfo.unstagedChange));
                }

                if (fileInfo.stagedChange != GitChangeType.NONE &&
                    fileInfo.stagedChange != GitChangeType.IGNORED &&
                    fileInfo.stagedChange != GitChangeType.UNTRACKED) {
                    stagedItems.add(new StageItem(fileInfo.gitFilePath,
                        fileInfo.stagedChange));
                }
            }

            showSelectedBranch();
        }

        this.unstagedPanel.setElements(unstagedItems);
        this.stagedPanel.setElements(stagedItems);

        revalidate();
        repaint();
    }

    private void showSelectedBranch() {
        final GitBranchAllResult branchResult =
            this.context.getCurrentProject().getBranchAllResult();
        final List<String> branchesList = branchResult.getLocalBranches();
        final String[] branches = ListUtil.listToArray(branchesList);

        this.actionPanel.remove(this.branchSelection);
        this.branchSelection = new BranchSelectionComponent(branches, context);
        this.branchSelection.showSelectedBranchName(branchResult.getCurrentBranch());
        this.actionPanel.add(this.branchSelection, BorderLayout.LINE_END);
    }

    void initGui() {
        this.splitPane.setDividerLocation(getWidth() / 2);
    }
}
