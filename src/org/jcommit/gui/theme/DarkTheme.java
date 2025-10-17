package org.jcommit.gui.theme;

import java.awt.*;

public final class DarkTheme implements Theme {

    private static final Color BACKGROUND_MAIN = new Color(34, 40, 49);
    private static final Color BACKGROUND_PANEL = new Color(57, 62 ,70);
    private static final Color TEXT_PRIMARY = new Color(238, 238, 238);
    private static final Color TEXT_SECONDARY = new Color(184, 184, 184);
    private static final Color ACCENT = new Color(0, 173, 181);
    private static final Color HOVER = new Color(8, 199, 206);
    private static final Color WARNING = new Color(255, 87, 34);
    private static final Color SUCCESS = new Color(76, 175, 80);
    private static final Color INACTIVE = new Color(102, 106, 112);
    private static final Color SEPARATION = new Color(44, 50, 58);


    @Override
    public Color getBackgroundMain() {
        return BACKGROUND_MAIN;
    }

    @Override
    public Color getBackgroundPanel() {
        return BACKGROUND_PANEL;
    }

    @Override
    public Color getTextPrimary() {
        return TEXT_PRIMARY;
    }

    @Override
    public Color getTextSecondary() {
        return TEXT_SECONDARY;
    }

    @Override
    public Color getAccent() {
        return ACCENT;
    }

    @Override
    public Color getHover() {
        return HOVER;
    }

    @Override
    public Color getWarning() {
        return WARNING;
    }

    @Override
    public Color getSuccess() {
        return SUCCESS;
    }

    @Override
    public Color getInactive() {
        return INACTIVE;
    }

    @Override
    public Color getSeparation() {
        return SEPARATION;
    }
}
