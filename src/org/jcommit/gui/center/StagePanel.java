package org.jcommit.gui.center;

import org.jcommit.gui.center.stagelist.StageItem;
import org.jcommit.gui.center.stagelist.StageList;
import org.jcommit.gui.theme.Theme;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

final class StagePanel extends JPanel {

    private final Theme theme;
    private final JButton selectionButton;
    private final JButton allButton;
    private JScrollPane currentScrollPane;
    private StageList currentListComponent;
    private List<StageItem> items;

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
        titlePanel.add(titleLabel);
        titlePanel.add(Box.createHorizontalGlue());
        titlePanel.add(selectionButton);
        titlePanel.add(allButton);

        add(titlePanel, BorderLayout.PAGE_START);
        setElements(new ArrayList<>());
    }

    void enableControls(boolean enabled) {
        this.selectionButton.setEnabled(enabled);
        this.allButton.setEnabled(enabled);
    }

    void setElements(List<StageItem> items) {
        if (this.currentScrollPane != null)
            remove(this.currentScrollPane);

        final StageList listComponent = new StageList(items);
        this.currentListComponent = listComponent;
        this.currentScrollPane = new JScrollPane(listComponent);

        final JPanel corner = new JPanel();
        corner.setBackground(this.theme.getBackgroundDark());
        this.currentScrollPane.setCorner(JScrollPane.LOWER_RIGHT_CORNER, corner);
        this.currentScrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER, corner);
        this.currentScrollPane.setCorner(JScrollPane.UPPER_RIGHT_CORNER, corner);
        this.currentScrollPane.setCorner(JScrollPane.LOWER_LEFT_CORNER, corner);
        this.currentScrollPane.setCorner(JScrollPane.LOWER_RIGHT_CORNER, corner);
        this.currentScrollPane.setBorder(BorderFactory.createEmptyBorder());

        add(this.currentScrollPane, BorderLayout.CENTER);

        this.items = items;

        revalidate();
        repaint();
    }

    List<String> getSelectedPaths() {
        final List<String> pathList = new ArrayList<>();

        if (this.currentListComponent != null) {
            for (StageItem item : this.currentListComponent.getSelectedValuesList())
                pathList.add(item.gitFilePath);
        }

        return pathList;
    }

    List<String> getAllPaths() {
        final List<String> pathList = new ArrayList<>();

        if (this.items != null) {
            for (StageItem item : this.items)
                pathList.add(item.gitFilePath);
        }

        return pathList;
    }
}
