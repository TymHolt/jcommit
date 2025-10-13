package org.jcommit.gui.center;

import org.jcommit.core.Context;
import org.jcommit.core.Project;
import org.jcommit.gui.util.GuiUtil;
import org.jcommit.gui.popup.PushPopup;

import javax.swing.*;
import java.awt.*;
import java.util.List;

final class ControlPanel extends JPanel {

    private final Context context;
    private final JButton fetchButton;
    private final JButton statusButton;
    private final JButton commitButton;
    private final JButton pushButton;
    private final JButton pullButton;

    ControlPanel(MainViewCenterPanel mainViewCenterPanel) {
        super();
        setLayout(new FlowLayout(FlowLayout.LEFT));
        this.context = mainViewCenterPanel.getMainView().getContext();

        fetchButton = new JButton("Fetch");
        fetchButton.addActionListener(actionEvent -> {
            this.context.fetch();
        });
        add(fetchButton);

        statusButton = new JButton("Status");
        statusButton.addActionListener(actionEvent -> {
            this.context.fetchStatus();
        });
        add(statusButton);

        commitButton = new JButton("Commit");
        commitButton.addActionListener(actionEvent -> {
            final String message = GuiUtil.popupInput("Commit message");

            if (message.isBlank()) {
                GuiUtil.popupInfo("Commit message may not be blank");
                return;
            }

            this.context.commit(message);
        });
        add(commitButton);

        pushButton = new JButton("Push");
        pushButton.addActionListener(actionEvent -> {
            final Project currentProject = this.context.getCurrentProject();

            if (currentProject == null)
                return;

            final List<String> localBranches = currentProject.getBranchAllResult().getLocalBranches();
            final List<String> remotes = currentProject.getRemotes();

            final PushPopup pushPopup = new PushPopup(mainViewCenterPanel.getMainView(), "Push",
                localBranches, remotes);

            if (pushPopup.wasCanceled())
                return;

            final String remote = pushPopup.getSelectedRemote();
            final String localBranch = pushPopup.getSelectedLocalBranch();
            context.push(remote, localBranch);
        });
        add(pushButton);

        pullButton = new JButton("Pull");
        pullButton.addActionListener(actionEvent -> {
            this.context.pull();
        });
        add(pullButton);
    }

    void updateGui() {
        final boolean showProject = this.context.getCurrentProject() != null;
        fetchButton.setEnabled(showProject);
        statusButton.setEnabled(showProject);
        commitButton.setEnabled(showProject);
        pushButton.setEnabled(showProject);
        pullButton.setEnabled(showProject);
    }
}
