package org.jcommit.gui.center;

import org.jcommit.core.Context;

import javax.swing.*;

final class BranchSelectionComponent extends JComboBox<String> {

    private final Context context;
    private boolean eventUpdate;

    BranchSelectionComponent(Context context) {
        this(new String[] {"<No Branch>"}, context);
    }

    BranchSelectionComponent(String[] branches, Context context) {
        super(branches);
        this.context = context;
        this.eventUpdate = false;

        addActionListener(actionEvent -> {
            if (eventUpdate)
                return;

            final Object selectedObject = getSelectedItem();
            if (!(selectedObject instanceof String))
                return;

            final String branchName = (String) getSelectedItem();
            this.context.checkoutBranch(branchName);
        });
    }

    void showSelectedBranchName(String branchName) {
        this.eventUpdate = true;
        setSelectedItem(branchName);
        this.eventUpdate = false;
    }
}
