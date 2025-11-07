package org.jcommit.gui.theme.themes;

import org.jcommit.gui.theme.Theme;

import java.awt.*;

public final class DarkTheme implements Theme {

    @Override
    public Color getBackgroundDark() {
        return new Color(40, 44, 52);
    }

    @Override
    public Color getBackgroundLight() {
        return new Color(60, 63, 70);
    }

    @Override
    public Color getForegroundDark() {
        return new Color(120, 120, 120);
    }

    @Override
    public Color getForegroundLight() {
        return new Color(230, 230, 230);
    }

    @Override
    public Color getHighlightDark() {
        return new Color(80, 100, 160);
    }

    @Override
    public Color getHighlightLight() {
        return new Color(100, 150, 250);
    }

    @Override
    public Color getBorder() {
        return new Color(60, 60, 65);
    }
}
