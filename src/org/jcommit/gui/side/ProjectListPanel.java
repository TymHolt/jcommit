package org.jcommit.gui.side;

import org.jcommit.core.Project;
import org.jcommit.gui.GuiUtil;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public final class ProjectListPanel extends JPanel {

    private static class ProjectEntryRow extends JPanel {

        final Project project;

        ProjectEntryRow(Project project) {
            super();
            this.project = project;
            setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

            add(new JLabel(project.getFile().getName()));
            add(Box.createHorizontalGlue());

            final JButton projectMenuButton = new JButton("...");
            projectMenuButton.addActionListener(actionEvent -> {
                GuiUtil.popupInfo("Project menu not implemented yet");
            });
            add(projectMenuButton);

            setMaximumSize(new Dimension(getMaximumSize().width, getPreferredSize().height));
        }
    }

    private final HashMap<Project, ProjectEntryRow> projectEntryRows;

    public ProjectListPanel() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.projectEntryRows = new HashMap<>();
    }

    public void addProject(Project project) {
        final ProjectEntryRow projectEntryRow = new ProjectEntryRow(project);
        this.projectEntryRows.put(project, projectEntryRow);
        add(projectEntryRow);

        revalidate();
        repaint();
    }

    public void removeProject(Project project) {
        if (!this.projectEntryRows.containsKey(project))
            return;

        final ProjectEntryRow projectEntryRow = this.projectEntryRows.remove(project);
        remove(projectEntryRow);

        revalidate();
        repaint();
    }
}
