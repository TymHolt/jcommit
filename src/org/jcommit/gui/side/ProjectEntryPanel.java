package org.jcommit.gui.side;

import org.jcommit.core.Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

final class ProjectEntryPanel extends JPanel {

    private final Project project;
    private final ProjectListPanel listPanel;

    ProjectEntryPanel(Project project, ProjectListPanel listPanel) {
        super();
        this.project = project;
        this.listPanel = listPanel;
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setHighlighted(false);

        add(new JLabel(project.getFile().getName()));
        add(Box.createHorizontalGlue());

        final JButton projectMenuButton = new JButton("...");
        projectMenuButton.addActionListener(actionEvent -> {
            final Object sourceObject = actionEvent.getSource();
            if (!(sourceObject instanceof JComponent))
                return;

            final JComponent sourceComponent = (JComponent) sourceObject;
            final JPopupMenu popupMenu = new JPopupMenu();
            final JMenuItem menuItem = new JMenuItem("Close");
            menuItem.addActionListener(menuActionEvent -> {
                final MainViewSidePanel sidePanel = this.listPanel.getSidePanel();
                sidePanel.getMainView().getContext().closeProject(project);
            });
            popupMenu.add(menuItem);
            popupMenu.show(sourceComponent, 0, 0);
        });
        add(projectMenuButton);

        setMaximumSize(new Dimension(getMaximumSize().width, getPreferredSize().height));
    }

    void setHighlighted(boolean highlighted) {
        setBackground(highlighted ? Color.GRAY : Color.LIGHT_GRAY);
    }

    Project getProject() {
        return this.project;
    }
}

