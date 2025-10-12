package org.jcommit.gui.util;

import org.jcommit.Log;

import javax.swing.*;
import java.io.File;

public final class GuiUtil {

    public static void popupInfo(String message) {
        Log.info("Popup: " + message);
        JOptionPane.showMessageDialog(null, message, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void popupError(String message) {
        Log.error("Popup: " + message);
        JOptionPane.showMessageDialog(null, message, "Info", JOptionPane.ERROR_MESSAGE);
    }

    public static String popupInput(String message) {
        return JOptionPane.showInputDialog(message);
    }

    public static FileSelectionResult popupSelectDirectory(String actionText) {
        final JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        final int option = fileChooser.showDialog(null, actionText);
        final File selectedFile = fileChooser.getSelectedFile();

        return new FileSelectionResult(FileSelectionOption.getFromSwingId(option), selectedFile);
    }
}
