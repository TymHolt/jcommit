package org.jcommit.gui.util;

import org.jcommit.Log;

import javax.swing.*;
import java.io.File;

public final class GuiUtil {

    public static void popupInfo(JFrame parent, String message) {
        Log.info("Popup: " + message);
        JOptionPane.showMessageDialog(parent, message, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void popupError(JFrame parent, String message) {
        Log.error("Popup: " + message);
        JOptionPane.showMessageDialog(parent, message, "Info", JOptionPane.ERROR_MESSAGE);
    }

    public static String popupInput(JFrame parent, String message) {
        return JOptionPane.showInputDialog(parent, message);
    }

    public static FileSelectionResult popupSelectDirectory(String actionText) {
        final JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        final int option = fileChooser.showDialog(null, actionText);
        final File selectedFile = fileChooser.getSelectedFile();

        return new FileSelectionResult(FileSelectionOption.getFromSwingId(option), selectedFile);
    }
}
