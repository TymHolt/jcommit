package org.jcommit.gui.components;

import org.jcommit.gui.theme.Theme;

import javax.swing.*;

public class ThemedMenuItem extends JMenuItem {

    public ThemedMenuItem(String text, Theme theme) {
        super(text);
        setBackground(theme.getBackgroundPanel());
        setForeground(theme.getTextPrimary());
    }
}
