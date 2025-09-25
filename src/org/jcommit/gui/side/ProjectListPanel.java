package org.jcommit.gui.side;

import org.jcommit.core.Project;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

public final class ProjectListPanel extends JPanel {

    private final MouseListener projectClickListener = new MouseAdapter() {

        @Override
        public void mouseClicked(MouseEvent event) {
            super.mouseClicked(event);

            final Object sourceObject = event.getSource();
            if (!(sourceObject instanceof JComponent))
                return;

            if (event.getButton() == MouseEvent.BUTTON1) {
                final Project project = getProjectByComponent((JComponent) sourceObject);
                if (project != null)
                    sidePanel.getMainView().showProject(project);
            }
        }
    };

    private final MainViewSidePanel sidePanel;
    private final HashMap<Project, ProjectEntryPanel> projectEntryPanels;

    public ProjectListPanel(MainViewSidePanel sidePanel) {
        super();
        this.sidePanel = sidePanel;
        this.projectEntryPanels = new HashMap<>();
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    }

    public void addProject(Project project) {
        for (Project openedProject : this.projectEntryPanels.keySet()) {
            // Check if the project path is already opened
            if (project.getFile().getAbsolutePath().equals(openedProject.getFile().getAbsolutePath()))
                return;
        }

        final ProjectEntryPanel projectEntryPanel = new ProjectEntryPanel(project, this);
        projectEntryPanel.addMouseListener(projectClickListener);
        this.projectEntryPanels.put(project, projectEntryPanel);
        add(projectEntryPanel);

        revalidate();
        repaint();
    }

    public void removeProject(Project project) {
        if (!this.projectEntryPanels.containsKey(project))
            return;

        final ProjectEntryPanel projectEntryPanel = this.projectEntryPanels.remove(project);
        remove(projectEntryPanel);

        revalidate();
        repaint();
    }

    public void showProject(Project project) {
        for (ProjectEntryPanel projectEntryPanel : this.projectEntryPanels.values())
            projectEntryPanel.setHighlighted(projectEntryPanel.getProject() == project);
    }

    public void hideProject(Project project) {
        if (this.projectEntryPanels.containsKey(project))
            this.projectEntryPanels.get(project).setHighlighted(false);
    }

    private Project getProjectByComponent(JComponent component) {
        for (ProjectEntryPanel projectEntryPanel : this.projectEntryPanels.values()) {
            if (projectEntryPanel == component)
                return projectEntryPanel.getProject();
        }

        return null;
    }

    public MainViewSidePanel getSidePanel() {
        return this.sidePanel;
    }
}
