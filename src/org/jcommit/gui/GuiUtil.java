package org.jcommit.gui;

import javax.swing.*;

public final class GuiUtil {

    public static void popupInfo(String message) {
        JOptionPane.showMessageDialog(null, message, "Info", JOptionPane.INFORMATION_MESSAGE);
    }
}
