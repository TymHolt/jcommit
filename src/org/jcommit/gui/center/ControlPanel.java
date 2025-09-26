package org.jcommit.gui.center;

import org.jcommit.Log;
import org.jcommit.commands.git.GitStatusCommand;
import org.jcommit.core.Project;
import org.jcommit.gui.GuiUtil;

import javax.swing.*;
import java.awt.*;

final class ControlPanel extends JPanel {

    ControlPanel(MainViewCenterPanel mainViewCenterPanel) {
        super();
        setLayout(new FlowLayout(FlowLayout.LEFT));

        final JButton statusButton = new JButton("Status");
        statusButton.addActionListener(actionEvent -> {
            final Project project = mainViewCenterPanel.getMainView().getCurrentProject();

            if (project == null)
                return;

            final GitStatusCommand statusCommand = new GitStatusCommand(project.getFile());

            try {
                final GitStatusCommand.GitStatusResult statusResult = statusCommand.execute();

                for (GitStatusCommand.GitStatusFileInfo fileInfo : statusResult.getFileInfos())
                    Log.info(fileInfo.getChangeType().name() + " " + fileInfo.getGitFilePath());
            } catch (Exception exception) {
                GuiUtil.popupError(exception.getMessage());
            }
        });
        add(statusButton);
    }
}
