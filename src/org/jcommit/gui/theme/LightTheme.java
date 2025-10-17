package org.jcommit.gui.theme;

import java.awt.*;

public final class LightTheme implements Theme {

    private static final Color BACKGROUND_MAIN = new Color(245, 245, 245);     // sehr helles Grau
    private static final Color BACKGROUND_PANEL = new Color(235, 235, 235);   // leicht dunkler für Panels
    private static final Color TEXT_PRIMARY = new Color(33, 33, 33);          // fast schwarz
    private static final Color TEXT_SECONDARY = new Color(85, 85, 85);        // mittleres Grau
    private static final Color ACCENT = new Color(0, 173, 181);               // Türkis wie im Dark Theme
    private static final Color HOVER = new Color(8, 199, 206);                // helleres Türkis
    private static final Color WARNING = new Color(230, 74, 25);              // etwas dunkleres Orange
    private static final Color SUCCESS = new Color(56, 142, 60);              // satteres Grün
    private static final Color INACTIVE = new Color(180, 180, 180);           // hellgrau für Disabled-Zustände
    private static final Color SEPARATION = new Color(200, 200, 200);         // zarte Trennlinien

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
