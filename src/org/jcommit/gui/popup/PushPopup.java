package org.jcommit.gui.popup;

import org.jcommit.util.ListUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public final class PushPopup extends JDialog {

    private final JComboBox<String> localBranchesComboBox;
    private final JComboBox<String> remotesComboBox;
    private boolean cancel = true;

    public PushPopup(JFrame parent, String title, List<String> localBranches,
        List<String> remotes) {
        super(parent, title, true);
        setLayout(new BorderLayout());
        setLocationRelativeTo(parent);

        final int preferredWidth = Math.max((parent.getHeight() / 3), 200);
        final JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));

        container.add(new JLabel("Local branch"));
        this.localBranchesComboBox = new JComboBox<>(ListUtil.listToArray(localBranches));
        applySizing(this.localBranchesComboBox, preferredWidth);
        this.localBranchesComboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        container.add(this.localBranchesComboBox);

        container.add(Box.createVerticalStrut(15));

        container.add(new JLabel("Remote"));
        this.remotesComboBox = new JComboBox<>(ListUtil.listToArray(remotes));
        applySizing(this.remotesComboBox, preferredWidth);
        this.remotesComboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        container.add(this.remotesComboBox);

        container.add(Box.createVerticalStrut(30));
        add(container, BorderLayout.CENTER);

        final JPanel buttonRow = new JPanel();
        buttonRow.setLayout(new BoxLayout(buttonRow, BoxLayout.LINE_AXIS));

        final JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(actionEvent -> {
            this.cancel = true;
            this.dispose();
        });
        buttonRow.add(cancelButton);

        buttonRow.add(Box.createHorizontalGlue());

        final JButton pushButton = new JButton("Push");
        pushButton.addActionListener(actionEvent -> {
            this.cancel = false;
            this.dispose();
        });
        buttonRow.add(pushButton);

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

    public String getSelectedLocalBranch() {
        return (String) this.localBranchesComboBox.getSelectedItem();
    }

    public String getSelectedRemote() {
        return (String) this.remotesComboBox.getSelectedItem();
    }

    public boolean canceled() {
        return this.cancel;
    }
}
