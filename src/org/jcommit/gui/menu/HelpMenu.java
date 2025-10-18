package org.jcommit.gui.menu;

import org.jcommit.core.Context;
import org.jcommit.gui.MainView;
import org.jcommit.gui.components.ThemedMenu;
import org.jcommit.gui.components.ThemedMenuItem;
import org.jcommit.gui.popup.SettingsPopup;
import org.jcommit.gui.theme.Theme;

import javax.swing.*;

public final class HelpMenu extends ThemedMenu {

    public HelpMenu(MainView mainView) {
        super("Help", mainView.getContext().getTheme());
        final Theme theme = mainView.getContext().getTheme();

        final JMenuItem settingsItem = new ThemedMenuItem("Settings", theme);
        settingsItem.addActionListener(actionEvent -> {
            final Context context = mainView.getContext();
            new SettingsPopup(mainView, context.getSettings());
            context.applySettings();
        });
        add(settingsItem);
    }
}
