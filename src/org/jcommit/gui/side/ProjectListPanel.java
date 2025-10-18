package org.jcommit.gui.side;

import org.jcommit.core.Context;
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
                    context.makeProjectCurrent(project);
            }
        }
    };

    private final Context context;
    private final HashMap<Project, ProjectEntryPanel> projectEntryPanels;

    public ProjectListPanel(Context context) {
        super();
        this.context = context;
        this.projectEntryPanels = new HashMap<>();
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBackground(context.getTheme().getBackgroundMain());
    }

    public void notifyOpenProject(Project project) {
        final ProjectEntryPanel projectEntryPanel = new ProjectEntryPanel(project, this.context);
        projectEntryPanel.addMouseListener(projectClickListener);
        this.projectEntryPanels.put(project, projectEntryPanel);
        add(projectEntryPanel);

        revalidate();
        repaint();
    }

    public void notifyCloseProject(Project project) {
        if (!this.projectEntryPanels.containsKey(project))
            return;

        final ProjectEntryPanel projectEntryPanel = this.projectEntryPanels.remove(project);
        remove(projectEntryPanel);

        revalidate();
        repaint();
    }

    public void notifyMakeProjectCurrent(Project project) {
        for (ProjectEntryPanel projectEntryPanel : this.projectEntryPanels.values())
            projectEntryPanel.setHighlighted(projectEntryPanel.getProject() == project);
    }

    private Project getProjectByComponent(JComponent component) {
        for (ProjectEntryPanel projectEntryPanel : this.projectEntryPanels.values()) {
            if (projectEntryPanel == component)
                return projectEntryPanel.getProject();
        }

        return null;
    }
}
