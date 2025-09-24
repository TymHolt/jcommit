package org.jcommit.gui;

import org.jcommit.Main;

import javax.swing.*;
import java.awt.*;

public final class MainView extends JFrame {

    public MainView() {
        super(Main.SOFTWARE_NAME + " " + Main.getVersionName());

        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width / 2, screenSize.height / 2);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final MainViewSidePanel sidePanel = new MainViewSidePanel();
        final MainViewCenterPanel centerPanel = new MainViewCenterPanel();
        final JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sidePanel,
            centerPanel);
        splitPane.setDividerLocation(getWidth() / 5);
        add(splitPane);
        setVisible(true);
    }
}
