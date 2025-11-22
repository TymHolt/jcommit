package org.jcommit.gui.theme;

import org.jcommit.gui.theme.components.CustomComboBoxUI;
import org.jcommit.gui.theme.components.CustomPopupMenuSeparatorUI;
import org.jcommit.gui.theme.components.CustomScrollBarUI;
import org.jcommit.gui.theme.components.CustomSplitPaneUI;

import javax.swing.*;
import java.awt.*;

public interface Theme {

    Color getBackgroundDark();
    Color getBackgroundLight();
    Color getForegroundDark();
    Color getForegroundLight();
    Color getHighlightDark();
    Color getHighlightLight();
    Color getBorder();

    static void applyTheme(Theme theme) {
        UIManager.put("control", theme.getBackgroundDark());
        UIManager.put("Panel.background", theme.getBackgroundDark());
        UIManager.put("text", theme.getForegroundLight());
        UIManager.put("Label.foreground", theme.getForegroundLight());

        UIManager.put("Button.background", theme.getBackgroundLight());
        UIManager.put("Button.foreground", theme.getForegroundLight());
        UIManager.put("Button.focus", theme.getHighlightLight());

        UIManager.put("TextField.background", theme.getBackgroundDark());
        UIManager.put("TextField.foreground", theme.getForegroundLight());
        UIManager.put("TextField.caretForeground", theme.getForegroundLight());
        UIManager.put("TextField.selectionBackground", theme.getHighlightDark());

        UIManager.put("Table.background", theme.getBackgroundDark());
        UIManager.put("Table.foreground", theme.getForegroundLight());
        UIManager.put("Table.selectionBackground", theme.getHighlightDark());

        UIManager.put("ToolTip.background", theme.getBackgroundLight());
        UIManager.put("ToolTip.foreground", theme.getForegroundLight());

        UIManager.put("MenuBar.background", theme.getBackgroundDark());
        UIManager.put("MenuBar.foreground", theme.getForegroundLight());
        UIManager.put("MenuBar.border", BorderFactory.createMatteBorder(0, 0, 2, 0, theme.getBorder()));
        UIManager.put("Menu.background", theme.getBackgroundDark());
        UIManager.put("Menu.foreground", theme.getForegroundLight());
        UIManager.put("Menu.selectionBackground", theme.getHighlightDark());
        UIManager.put("Menu.selectionForeground", theme.getForegroundLight());
        UIManager.put("Menu.border", BorderFactory.createEmptyBorder());
        UIManager.put("MenuItem.background", theme.getBackgroundDark());
        UIManager.put("MenuItem.foreground", theme.getForegroundLight());
        UIManager.put("MenuItem.selectionBackground", theme.getHighlightDark());
        UIManager.put("MenuItem.selectionForeground", theme.getForegroundLight());
        UIManager.put("MenuItem.border", BorderFactory.createEmptyBorder());
        UIManager.put("PopupMenu.background", theme.getBackgroundDark());
        UIManager.put("PopupMenu.foreground", theme.getForegroundLight());
        UIManager.put("PopupMenu.border", BorderFactory.createLineBorder(theme.getBorder()));
        CustomPopupMenuSeparatorUI.setTheme(theme);
        UIManager.put("PopupMenuSeparatorUI", "org.jcommit.gui.theme.components.CustomPopupMenuSeparatorUI");

        UIManager.put("TabbedPane.background", theme.getBackgroundDark());
        UIManager.put("TabbedPane.selected", theme.getForegroundDark());

        UIManager.put("List.background", theme.getBackgroundDark());
        UIManager.put("List.foreground", theme.getForegroundLight());
        UIManager.put("List.selectionBackground", theme.getHighlightLight());
        UIManager.put("List.selectionForeground", theme.getForegroundLight());
        UIManager.put("List.focusCellHighlightBorder", BorderFactory.createEmptyBorder());
        UIManager.put("List.border", BorderFactory.createMatteBorder(2, 0, 0, 0, theme.getBorder()));

        UIManager.put("SplitPane.border", BorderFactory.createEmptyBorder());
        CustomSplitPaneUI.setTheme(theme);
        UIManager.put("SplitPaneUI", "org.jcommit.gui.theme.components.CustomSplitPaneUI");

        UIManager.put("ComboBox.background", theme.getBackgroundDark());
        UIManager.put("ComboBox.foreground", theme.getForegroundLight());
        UIManager.put("ComboBox.selectionBackground", theme.getHighlightDark());
        UIManager.put("ComboBox.selectionForeground", theme.getForegroundLight());
        UIManager.put("ComboBox.buttonBackground", theme.getBackgroundLight());
        UIManager.put("ComboBox.buttonShadow", theme.getBackgroundDark());
        UIManager.put("ComboBox.buttonHighlight", theme.getBackgroundLight());
        UIManager.put("ComboBox.border", BorderFactory.createLineBorder(theme.getBorder()));
        UIManager.put("ComboBox.disabledBackground", theme.getBackgroundLight());
        UIManager.put("ComboBox.disabledForeground", theme.getForegroundDark());
        CustomComboBoxUI.setTheme(theme);
        UIManager.put("ComboBoxUI", "org.jcommit.gui.theme.components.CustomComboBoxUI");

        UIManager.put("OptionPane.background", theme.getBackgroundDark());
        UIManager.put("OptionPane.foreground", theme.getForegroundLight());
        UIManager.put("OptionPane.messageForeground", theme.getForegroundLight());
        UIManager.put("OptionPane.border", BorderFactory.createLineBorder(theme.getBorder()));
        UIManager.put("OptionPane.messageAreaBorder", BorderFactory.createEmptyBorder(10, 10, 10, 10));

        UIManager.put("CheckBox.background", theme.getBackgroundLight());
        UIManager.put("CheckBox.foreground", theme.getForegroundLight());
        UIManager.put("CheckBox.focus", theme.getHighlightLight());
        UIManager.put("CheckBox.border", BorderFactory.createLineBorder(theme.getBorder()));
        UIManager.put("CheckBox.select", theme.getHighlightLight());
        UIManager.put("CheckBox.disabledText", theme.getForegroundDark());

        UIManager.put("ScrollBarUI", "org.jcommit.gui.theme.components.CustomScrollBarUI");
        CustomScrollBarUI.setTheme(theme);
        UIManager.put("ScrollBar.width", 12);
    }
}
