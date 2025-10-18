package org.jcommit.gui.components;

import org.jcommit.gui.theme.Theme;

import javax.swing.*;

public class ThemedMenuBar extends JMenuBar {

    public ThemedMenuBar(Theme theme) {
        super();
        setBackground(theme.getBackgroundPanel());
        setForeground(theme.getTextPrimary());
    }
}
