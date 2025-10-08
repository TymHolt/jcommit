package org.jcommit.gui.center;

import org.jcommit.core.Context;
import org.jcommit.core.Project;
import org.jcommit.gui.GuiUtil;
import org.jcommit.gui.popup.PushPopup;

import javax.swing.*;
import java.awt.*;
import java.util.List;

final class ControlPanel extends JPanel {

    ControlPanel(MainViewCenterPanel mainViewCenterPanel) {
        super();
        setLayout(new FlowLayout(FlowLayout.LEFT));

        final JButton statusButton = new JButton("Status");
        statusButton.addActionListener(actionEvent -> {
            mainViewCenterPanel.getMainView().getContext().fetchStatus();
        });
        add(statusButton);

        final JButton commitButton = new JButton("Commit");
        commitButton.addActionListener(actionEvent -> {
            final String message = GuiUtil.popupInput("Commit message");

            if (message.isBlank()) {
                GuiUtil.popupInfo("Commit message may not be blank");
                return;
            }

            mainViewCenterPanel.getMainView().getContext().commit(message);
        });
        add(commitButton);

        final JButton pushButton = new JButton("Push");
        pushButton.addActionListener(actionEvent -> {
            final Context context  = mainViewCenterPanel.getMainView().getContext();
            final Project currentProject = context.getCurrentProject();

            if (currentProject == null)
                return;

            final List<String> localBranches = currentProject.getBranchAllResult().getLocalBranches();
            final List<String> remotes = currentProject.getRemotes();

            final PushPopup pushPopup = new PushPopup(mainViewCenterPanel.getMainView(), "Push",
                localBranches, remotes);

            if (pushPopup.canceled())
                return;

            final String remote = pushPopup.getSelectedRemote();
            final String localBranch = pushPopup.getSelectedLocalBranch();
            context.push(remote, localBranch);
        });
        add(pushButton);
    }
}
