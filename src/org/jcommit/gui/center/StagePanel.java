package org.jcommit.gui.center;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

final class StagePanel extends JPanel {

    private JList<String> currentListComponent;
    private List<String> elements;

    StagePanel(String title, JButton selectionButton, JButton allButton) {
        super();
        setLayout(new BorderLayout());
        this.currentListComponent = null;

        final JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.LINE_AXIS));
        titlePanel.add(new JLabel(title));
        titlePanel.add(Box.createHorizontalGlue());
        titlePanel.add(selectionButton);
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

        this.elements = elements;

        revalidate();
        repaint();
    }

    List<String> getSelectedPaths() {
        if (this.currentListComponent == null)
            return new ArrayList<>();

        return this.currentListComponent.getSelectedValuesList();
    }

    List<String> getAllPaths() {
        if (this.elements == null)
            return new ArrayList<>();

        return this.elements;
    }
}
