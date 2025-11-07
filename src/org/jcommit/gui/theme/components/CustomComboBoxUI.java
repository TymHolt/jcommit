package org.jcommit.gui.theme.components;

import org.jcommit.gui.theme.Theme;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;

public final class CustomComboBoxUI extends BasicComboBoxUI {

    public static ComponentUI createUI(JComponent component) {
        return new CustomComboBoxUI();
    }

    private static Theme theme;

    public static void setTheme(Theme theme) {
        CustomComboBoxUI.theme = theme;
    }

    @Override
    protected JButton createArrowButton() {
        final JButton button = new JButton("▼");
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setForeground(theme.getForegroundLight());
        button.setBackground(theme.getBackgroundLight());
        button.setFocusPainted(false);
        return button;
    }

    @Override
    public void paintCurrentValueBackground(Graphics graphics, Rectangle bounds, boolean hasFocus) {
        graphics.setColor(theme.getBackgroundLight());
        graphics.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
    }
}
