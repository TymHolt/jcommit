package org.jcommit.gui.center;

import org.jcommit.gui.GuiUtil;

import javax.swing.*;
import java.awt.*;

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
    }
}
