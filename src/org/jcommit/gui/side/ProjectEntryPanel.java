package org.jcommit.gui.side;

import org.jcommit.core.Context;
import org.jcommit.core.Project;
import org.jcommit.gui.theme.Theme;

import javax.swing.*;
import java.awt.*;

final class ProjectEntryPanel extends JPanel {

    private final Theme theme;
    private final JLabel nameLabel;
    private final Project project;

    ProjectEntryPanel(Project project, Context context) {
        super();
        this.theme = context.getTheme();
        this.project = project;
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
                context.closeProject(project);
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
            setBackground(this.theme.getHighlightLight());
            this.nameLabel.setForeground(this.theme.getForegroundLight());
        } else {
            setBackground(this.theme.getBackgroundDark());
            this.nameLabel.setForeground(this.theme.getForegroundLight());
        }
    }

    Project getProject() {
        return this.project;
    }
}

