package org.jcommit.gui.util;

import javax.swing.*;

public enum FileSelectionOption {

    APPROVE(JFileChooser.APPROVE_OPTION),
    CANCEL(JFileChooser.CANCEL_OPTION),
    ERROR(JFileChooser.ERROR_OPTION);

    private final int swingId;

    FileSelectionOption(int swingId) {
        this.swingId = swingId;
    }

    static FileSelectionOption getFromSwingId(int swingId) {
        for (FileSelectionOption option : values()) {
            if (option.swingId == swingId)
                return option;
        }
        return null;
    }
}
