package org.jcommit.gui.center;

import javax.swing.*;
import java.awt.*;

final class ControlPanel extends JPanel {

    ControlPanel() {
        super();
        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(new JButton("Status"));
        add(new JButton("Test"));
    }
}
