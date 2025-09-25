package org.jcommit.gui.center;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

final class StagePanel extends JPanel {

    private JList<String> currentListComponent;

    StagePanel(String title, JButton allButton) {
        super();
        setLayout(new BorderLayout());
        this.currentListComponent = null;

        final JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.LINE_AXIS));
        titlePanel.add(new JLabel(title));
        titlePanel.add(Box.createHorizontalGlue());
        titlePanel.add(allButton);

        add(titlePanel, BorderLayout.PAGE_START);
        setElements(new ArrayList<>());
    }

    void setElements(List<String> elements) {
        if (this.currentListComponent != null)
            remove(this.currentListComponent);

        final DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String element : elements) {
            listModel.addElement(element);
        }

        final JList<String> listComponent = new JList<>(listModel);
        this.currentListComponent = listComponent;
        add(listComponent, BorderLayout.CENTER);
    }
}
