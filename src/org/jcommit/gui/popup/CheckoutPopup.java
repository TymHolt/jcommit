package org.jcommit.gui.popup;

import org.jcommit.gui.components.ApproveCancelButtonRow;
import org.jcommit.util.ListUtil;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public final class CheckoutPopup extends JDialog {

    private final JComboBox<String> branchesComboBox;
    private boolean cancel;

    public CheckoutPopup(JFrame parent, String title, List<String> branches) {
        super(parent, title, true);
        setLayout(new BorderLayout());
        setLocationRelativeTo(parent);
        this.cancel = true;

        final int preferredWidth = Math.max((parent.getHeight() / 3), 200);
        final JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));

        container.add(new JLabel("Branch"));
        this.branchesComboBox = new JComboBox<>(ListUtil.listToArray(branches));
        applySizing(this.branchesComboBox, preferredWidth);
        this.branchesComboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        container.add(this.branchesComboBox);

        container.add(Box.createVerticalStrut(30));
        add(container, BorderLayout.CENTER);

        final ApproveCancelButtonRow buttonRow = new ApproveCancelButtonRow();
        buttonRow.setCancelOption("Cancel", actionEvent -> {
            this.cancel = true;
            this.dispose();
        });
        buttonRow.setApproveOption("Checkout", actionEvent -> {
            this.cancel = false;
            this.dispose();
        });
        add(buttonRow, BorderLayout.PAGE_END);

        pack();
        setVisible(true);
    }

    private static void applySizing(JComponent component, int preferredWidth) {
        final Dimension preferredSize = component.getPreferredSize();
        final Dimension size = new Dimension(preferredWidth, preferredSize.height);
        component.setPreferredSize(size);
        component.setMaximumSize(size);
    }

    public String getSelectedBranchName() {
        return (String) this.branchesComboBox.getSelectedItem();
    }

    public boolean wasCanceled() {
        return this.cancel;
    }
}
