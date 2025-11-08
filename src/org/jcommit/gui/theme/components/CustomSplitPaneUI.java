package org.jcommit.gui.theme.components;

import org.jcommit.gui.theme.Theme;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.awt.*;

public final class CustomSplitPaneUI extends BasicSplitPaneUI {

    public static ComponentUI createUI(JComponent component) {
        return new CustomSplitPaneUI();
    }

    private static Theme theme;

    public static void setTheme(Theme theme) {
        CustomSplitPaneUI.theme = theme;
    }

    @Override
    public BasicSplitPaneDivider createDefaultDivider() {
        return new BasicSplitPaneDivider(this) {

            @Override
            public void paint(Graphics graphics) {
                final int width = getWidth();
                final int height = getHeight();

                // Draw background
                graphics.setColor(theme.getBackgroundDark());
                graphics.fillRect(0, 0, width, height);

                // Draw foreground
                graphics.setColor(theme.getBorder());
                final int thickness = 2;
                if (splitPane.getOrientation() == JSplitPane.HORIZONTAL_SPLIT) {
                    final int mid = width / 2;
                    graphics.fillRect(mid - thickness, 0, thickness * 2, height);
                } else {
                    final int mid = height / 2;
                    graphics.fillRect(0, mid - thickness, width, thickness * 2);
                }
            }

            @Override
            public Dimension getPreferredSize() {
                if (splitPane.getOrientation() == JSplitPane.HORIZONTAL_SPLIT)
                    return new Dimension(10, 0);
                else
                    return new Dimension(0, 10);
            }
        };
    }
}
