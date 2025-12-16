package org.jcommit.gui.center.stagelist;

import javax.swing.*;
import java.awt.*;

public class StageListCellRenderer implements ListCellRenderer<StageItem> {

    @Override
    public Component getListCellRendererComponent(JList<? extends StageItem> list,
        StageItem item, int index, boolean isSelected, boolean hasFocus) {

        final JLabel label = new JLabel();
        label.setOpaque(true);

        label.setText(getPrefix(item) + "  " + item.gitFilePath);

        if (isSelected) {
            label.setBackground(UIManager.getColor("List.selectionBackground"));
            label.setForeground(UIManager.getColor("List.selectionForeground"));
        } else {
            label.setBackground(getBackgroundColor(item));
            label.setForeground(UIManager.getColor("List.foreground"));
        }

        return label;
    }

    private static String getPrefix(StageItem item) {
        return switch (item.changeType) {
            case ADDED -> "[+]";
            case MODIFIED -> "[~]";
            case DELETED -> "[-]";
            case COPIED -> "[C]";
            case UNTRACKED -> "[*]";
            case RENAMED -> "[R]";
            case CONFLICTED -> "[!]";
            default -> "[?]";
        };
    }

    private static Color getBackgroundColor(StageItem item) {
        return switch (item.changeType) {
            case ADDED, COPIED -> new Color(40, 120, 70);
            case MODIFIED, RENAMED -> new Color(160, 130, 60);
            case DELETED -> new Color(135, 45, 45);
            case UNTRACKED -> new Color(55, 95, 160);
            case CONFLICTED -> new Color(120, 70, 160);
            default -> new Color(50, 53, 58);
        };
    }
}
