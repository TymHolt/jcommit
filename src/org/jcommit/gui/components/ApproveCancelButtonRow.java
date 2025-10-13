package org.jcommit.gui.components;

import javax.swing.*;
import java.awt.event.ActionListener;

public final class ApproveCancelButtonRow extends JPanel {

    private final JButton cancelButton;
    private final JButton approveButton;

    public ApproveCancelButtonRow() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        cancelButton = new JButton("Cancel");
        add(cancelButton);

        add(Box.createHorizontalGlue());

        approveButton = new JButton("Approve");
        add(approveButton);
    }

    public void setCancelOption(String buttonTitle, ActionListener listener) {
        this.cancelButton.setText(buttonTitle);
        this.cancelButton.addActionListener(listener);
    }

    public void setApproveOption(String buttonTitle, ActionListener listener) {
        this.approveButton.setText(buttonTitle);
        this.approveButton.addActionListener(listener);
    }
}
