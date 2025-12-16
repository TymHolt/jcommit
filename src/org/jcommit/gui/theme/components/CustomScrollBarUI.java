package org.jcommit.gui.theme.components;

import org.jcommit.gui.theme.Theme;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class CustomScrollBarUI extends BasicScrollBarUI {

    public static ComponentUI createUI(JComponent component) {
        return new CustomScrollBarUI();
    }

    private static Theme theme;

    public static void setTheme(Theme theme) {
        CustomScrollBarUI.theme = theme;
    }

    @Override
    protected void configureScrollBarColors() {
        this.thumbColor = theme.getBackgroundLight();
        this.trackColor = theme.getForegroundDark();
    }

    @Override
    protected void paintThumb(Graphics graphics, JComponent component, Rectangle bounds) {
        Graphics2D graphics2d = (Graphics2D) graphics.create();
        graphics2d.setColor(thumbColor);
        graphics2d.fillRoundRect(bounds.x, bounds.y, bounds.width, bounds.height, 10, 10);
        graphics2d.dispose();
    }

    @Override
    protected void paintTrack(Graphics graphics, JComponent component, Rectangle bounds) {
        Graphics2D graphics2d = (Graphics2D) graphics.create();
        graphics2d.setColor(trackColor);
        graphics2d.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
        graphics2d.dispose();
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        return createNoButton();
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return createNoButton();
    }

    private JButton createNoButton() {
        final JButton button = new JButton();
        button.setPreferredSize(new Dimension(0, 0));
        button.setMinimumSize(new Dimension(0, 0));
        button.setMaximumSize(new Dimension(0, 0));
        return button;
    }
}
