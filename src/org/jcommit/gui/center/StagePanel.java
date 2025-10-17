package org.jcommit.gui.center;

import org.jcommit.gui.theme.Theme;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

final class StagePanel extends JPanel {

    private final Theme theme;
    private final JButton selectionButton;
    private final JButton allButton;
    private JList<String> currentListComponent;
    private List<String> elements;

    StagePanel(Theme theme, String title, JButton selectionButton, JButton allButton) {
        super();
        setLayout(new BorderLayout());
        this.theme = theme;
        this.currentListComponent = null;
        this.selectionButton = selectionButton;
        this.allButton = allButton;

        final JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.LINE_AXIS));
        final JLabel titleLabel = new JLabel(title);
        titleLabel.setForeground(theme.getTextPrimary());
        titlePanel.add(titleLabel);
        titlePanel.add(Box.createHorizontalGlue());
        titlePanel.add(selectionButton);
        titlePanel.add(allButton);
        titlePanel.setBackground(theme.getBackgroundPanel());

        add(titlePanel, BorderLayout.PAGE_START);
        setElements(new ArrayList<>());
    }

    void enableControls(boolean enabled) {
        this.selectionButton.setEnabled(enabled);
        this.allButton.setEnabled(enabled);
    }

    void setElements(List<String> elements) {
        if (this.currentListComponent != null)
            remove(this.currentListComponent);

        final DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String element : elements) {
            listModel.addElement(element);
        }

        final JList<String> listComponent = new JList<>(listModel);
        listComponent.setBackground(this.theme.getBackgroundMain());
        listComponent.setForeground(this.theme.getTextPrimary());
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
