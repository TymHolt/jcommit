package org.jcommit.gui.side;

import org.jcommit.core.Project;
import org.jcommit.gui.theme.Theme;

import javax.swing.*;
import java.awt.*;

final class ProjectEntryPanel extends JPanel {

    private final Theme theme;
    private final JLabel nameLabel;
    private final Project project;
    private final ProjectListPanel listPanel;

    ProjectEntryPanel(Project project, ProjectListPanel listPanel) {
        super();
        this.theme = listPanel.getSidePanel().getMainView().getContext().getTheme();
        this.project = project;
        this.listPanel = listPanel;
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        this.nameLabel = new JLabel(project.getFile().getName());
        add(this.nameLabel);
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
        setHighlighted(false);
    }

    void setHighlighted(boolean highlighted) {
        if (highlighted) {
            setBackground(this.theme.getAccent());
            this.nameLabel.setForeground(this.theme.getTextPrimary());
        } else {
            setBackground(this.theme.getBackgroundPanel());
            this.nameLabel.setForeground(this.theme.getTextSecondary());
        }
    }

    Project getProject() {
        return this.project;
    }
}

