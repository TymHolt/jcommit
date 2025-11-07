package org.jcommit.gui.theme.components;

import org.jcommit.gui.theme.Theme;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicPopupMenuSeparatorUI;
import java.awt.*;

public class CustomPopupMenuSeparatorUI extends BasicPopupMenuSeparatorUI {

    public static ComponentUI createUI(JComponent component) {
        return new CustomPopupMenuSeparatorUI();
    }

    private static Theme theme;

    public static void setTheme(Theme theme) {
        CustomPopupMenuSeparatorUI.theme = theme;
    }

    @Override
    public void paint(Graphics graphics, JComponent component) {
        graphics.setColor(theme.getBackgroundDark());
        graphics.fillRect(0, 0, component.getWidth(), component.getHeight());

        graphics.setColor(theme.getBorder());
        final int y = component.getHeight() / 2;
        graphics.fillRect(2, y, component.getWidth() - 4, 1);
    }

    @Override
    public Dimension getPreferredSize(JComponent component) {
        return new Dimension(0, 6);
    }
}