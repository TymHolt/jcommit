package org.jcommit.gui.theme.themes;

import org.jcommit.gui.theme.Theme;

import java.awt.*;

public final class LightTheme implements Theme {

    @Override
    public Color getBackgroundDark() {
        return new Color(240, 240, 245);
    }

    @Override
    public Color getBackgroundLight() {
        return new Color(250, 250, 252);
    }

    @Override
    public Color getForegroundDark() {
        return new Color(130, 130, 130);
    }

    @Override
    public Color getForegroundLight() {
        return new Color(30, 30, 30);
    }

    @Override
    public Color getHighlightDark() {
        return new Color(100, 140, 220);
    }

    @Override
    public Color getHighlightLight() {
        return new Color(140, 170, 255);
    }

    @Override
    public Color getBorder() {
        return new Color(210, 210, 215);
    }
}
