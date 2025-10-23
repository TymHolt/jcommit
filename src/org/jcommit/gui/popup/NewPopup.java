package org.jcommit.gui.popup;

import org.jcommit.gui.components.ApproveCancelButtonRow;
import org.jcommit.gui.util.FileSelectionOption;
import org.jcommit.gui.util.FileSelectionResult;
import org.jcommit.gui.util.GuiUtil;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public final class NewPopup extends JDialog {

    private final JTextField projectDirectoryPathField;
    private boolean cancel;

    public NewPopup(JFrame parent, String title) {
        super(parent, title, true);
        setLayout(new BorderLayout());
        setLocationRelativeTo(parent);
        this.cancel = true;

        final JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));

        final JPanel directorySelectionContainer = new JPanel();
        directorySelectionContainer.setLayout(new BorderLayout());
        directorySelectionContainer.setAlignmentX(Component.LEFT_ALIGNMENT);

        this.projectDirectoryPathField = new JTextField();
        directorySelectionContainer.add(this.projectDirectoryPathField, BorderLayout.CENTER);

        final JButton parentDirectorySelectionButton = new JButton("...");
        parentDirectorySelectionButton.addActionListener(actionEvent -> {
            final FileSelectionResult result = GuiUtil.popupSelectDirectory("Select");
            if (result.getOption() == FileSelectionOption.APPROVE)
                this.projectDirectoryPathField.setText(result.getFile().getAbsolutePath());
        });
        directorySelectionContainer.add(parentDirectorySelectionButton, BorderLayout.LINE_END);

        container.add(directorySelectionContainer);

        //  ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

        container.add(Box.createVerticalStrut(30));
        add(container, BorderLayout.CENTER);

        //  vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv

        final ApproveCancelButtonRow buttonRow = new ApproveCancelButtonRow();
        buttonRow.setCancelOption("Cancel", actionEvent -> {
            this.cancel = true;
            this.dispose();
        });
        buttonRow.setApproveOption("Init", actionEvent -> {
            this.cancel = false;
            this.dispose();
        });
        add(buttonRow, BorderLayout.PAGE_END);

        //  ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

        pack();
        setVisible(true);
    }

    public File getProjectDirectory() {
        final File file = new File(this.projectDirectoryPathField.getText());

        if (file.isDirectory())
            return file;

        return null;
    }

    public boolean wasCanceled() {
        return this.cancel;
    }
}
