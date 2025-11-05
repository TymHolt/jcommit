package org.jcommit.gui.theme;

import javax.swing.*;
import java.awt.*;

public interface Theme {

    Color getControl();
    Color getPanelBackground();
    Color getText();
    Color getLabelForeground();

    Color getButtonBackground();
    Color getButtonForeground();
    Color getButtonFocus();

    Color getTextFieldBackground();
    Color getTextFieldForeground();
    Color getTextFieldCaretForeground();
    Color getTextFieldSelectionBackground();

    Color getTableBackground();
    Color getTableForeground();
    Color getTableSelectionBackground();

    Color getToolTipBackground();
    Color getToolTipForeground();

    Color getMenuForeground();
    Color getMenuBackground();
    Color getMenuItemSelectionBackground();

    Color getTabbedPaneBackground();
    Color getTabbedPaneSelected();

    static void applyTheme(Theme theme) {
        UIManager.put("control", theme.getControl());
        UIManager.put("Panel.background", theme.getPanelBackground());
        UIManager.put("text", theme.getText());
        UIManager.put("Label.foreground", theme.getLabelForeground());
        UIManager.put("Button.background", theme.getButtonBackground());
        UIManager.put("Button.foreground", theme.getButtonForeground());
        UIManager.put("Button.focus", theme.getButtonFocus());
        UIManager.put("TextField.background", theme.getTextFieldBackground());
        UIManager.put("TextField.foreground", theme.getTextFieldForeground());
        UIManager.put("TextField.caretForeground", theme.getTextFieldCaretForeground());
        UIManager.put("TextField.selectionBackground", theme.getTextFieldSelectionBackground());
        UIManager.put("Table.background", theme.getTableBackground());
        UIManager.put("Table.foreground", theme.getTableForeground());
        UIManager.put("Table.selectionBackground", theme.getTableSelectionBackground());
        UIManager.put("ToolTip.background", theme.getToolTipBackground());
        UIManager.put("ToolTip.foreground", theme.getToolTipForeground());
        UIManager.put("Menu.background", theme.getMenuBackground());
        UIManager.put("Menu.foreground", theme.getMenuForeground());
        UIManager.put("MenuItem.selectionBackground", theme.getMenuItemSelectionBackground());
        UIManager.put("TabbedPane.background", theme.getTabbedPaneBackground());
        UIManager.put("TabbedPane.selected", theme.getTabbedPaneSelected());
    }
}
