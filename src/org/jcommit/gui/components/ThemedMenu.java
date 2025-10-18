package org.jcommit.gui.components;

import org.jcommit.gui.theme.Theme;

import javax.swing.*;

public class ThemedMenu extends JMenu {

    public ThemedMenu(String text, Theme theme) {
        super(text);
        setBackground(theme.getBackgroundPanel());
        setForeground(theme.getTextPrimary());
    }
}
