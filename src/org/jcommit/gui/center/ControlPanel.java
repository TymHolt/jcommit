package org.jcommit.gui.center;

import javax.swing.*;
import java.awt.*;

final class ControlPanel extends JPanel {

    ControlPanel(MainViewCenterPanel mainViewCenterPanel) {
        super();
        setLayout(new FlowLayout(FlowLayout.LEFT));

        final JButton statusButton = new JButton("Status");
        statusButton.addActionListener(actionEvent -> {
            mainViewCenterPanel.getMainView().status();
        });
        add(statusButton);
    }
}
