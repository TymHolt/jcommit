package org.jcommit.gui.popup;

import org.jcommit.core.Settings;
import org.jcommit.gui.components.ApproveCancelButtonRow;

import javax.swing.*;
import java.awt.*;

public final class SettingsPopup extends JDialog {

    private final JCheckBox printDebugToggle;
    private final JCheckBox printToFileToggle;
    private final JCheckBox printTimeStampToggle;

    public SettingsPopup(JFrame parent, Settings settings) {
        super(parent, "Settings", true);

        //  vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv

        final JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));

        this.printDebugToggle = new JCheckBox("Print debug logs");
        this.printDebugToggle.setSelected(settings.getPrintDebug());
        container.add(printDebugToggle);

        this.printToFileToggle = new JCheckBox("Print logs to file");
        this.printToFileToggle.setSelected(settings.getPrintToFile());
        container.add(printToFileToggle);

        this.printTimeStampToggle = new JCheckBox("Print logs with time stamp");
        this.printTimeStampToggle.setSelected(settings.getPrintTimeStamp());
        container.add(printTimeStampToggle);

        //  ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

        container.add(Box.createVerticalStrut(30));
        add(container, BorderLayout.CENTER);

        //  vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv

        final ApproveCancelButtonRow buttonRow = new ApproveCancelButtonRow();
        buttonRow.setCancelOption("Cancel", actionEvent -> {
            this.dispose();
        });
        buttonRow.setApproveOption("Apply", actionEvent -> {
            settings.setPrintDebug(this.printDebugToggle.isSelected());
            settings.setPrintToFile(this.printToFileToggle.isSelected());
            settings.setPrintTimeStamp(this.printTimeStampToggle.isSelected());
            this.dispose();
        });
        add(buttonRow, BorderLayout.PAGE_END);

        //  ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

        pack();
        setVisible(true);
    }
}
