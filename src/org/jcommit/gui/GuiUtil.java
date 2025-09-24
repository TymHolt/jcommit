package org.jcommit.gui;

import org.jcommit.Log;

import javax.swing.*;

public final class GuiUtil {

    public static void popupInfo(String message) {
        Log.info("Popup: " + message);
        JOptionPane.showMessageDialog(null, message, "Info", JOptionPane.INFORMATION_MESSAGE);
    }
}
