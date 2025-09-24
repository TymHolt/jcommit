package org.jcommit.gui.side;

import org.jcommit.core.Project;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public final class ProjectListPanel extends JPanel {

    private static class ProjectEntryRow extends JPanel {

        final Project project;

        ProjectEntryRow(Project project) {
            super();
            this.project = project;
        }
    }

    private final HashMap<Project, ProjectEntryRow> projectEntryRows;

    public ProjectListPanel() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.projectEntryRows = new HashMap<>();

        setBackground(Color.GREEN); // Debug
    }

    public void addProject(Project project) {
        final ProjectEntryRow projectEntryRow = new ProjectEntryRow(project);
        this.projectEntryRows.put(project, projectEntryRow);
        add(projectEntryRow);
    }

    public void removeProject(Project project) {
        if (!this.projectEntryRows.containsKey(project))
            return;

        final ProjectEntryRow projectEntryRow = this.projectEntryRows.remove(project);
        remove(projectEntryRow);
    }
}
