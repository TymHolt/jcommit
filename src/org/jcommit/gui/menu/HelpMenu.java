package org.jcommit.gui.menu;

import org.jcommit.core.Context;
import org.jcommit.gui.MainView;
import org.jcommit.gui.popup.SettingsPopup;

import javax.swing.*;

public final class HelpMenu extends JMenu {

    public HelpMenu(MainView mainView) {
        super("Help");

        final JMenuItem settingsItem = new JMenuItem("Settings");
        settingsItem.addActionListener(actionEvent -> {
            final Context context = mainView.getContext();
            new SettingsPopup(mainView, context.getSettings());
            context.applySettings();
        });
        add(settingsItem);
    }
}
