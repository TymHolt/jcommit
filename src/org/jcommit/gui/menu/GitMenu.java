package org.jcommit.gui.menu;

import org.jcommit.Log;
import org.jcommit.core.Context;
import org.jcommit.core.Project;
import org.jcommit.gui.MainView;
import org.jcommit.gui.components.ThemedMenu;
import org.jcommit.gui.components.ThemedMenuItem;
import org.jcommit.gui.popup.CheckoutPopup;
import org.jcommit.gui.popup.PushPopup;
import org.jcommit.gui.theme.Theme;
import org.jcommit.gui.util.GuiUtil;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public final class GitMenu extends ThemedMenu {

    private final Context context;
    private final List<JMenuItem> items;

    public GitMenu(MainView mainView) {
        super("Git", mainView.getContext().getTheme());
        this.context = mainView.getContext();
        this.items = new ArrayList<>();
        final Theme theme = context.getTheme();

        // ------------------------------------------------------------

        final JMenuItem fetchItem = new ThemedMenuItem("Fetch", theme);
        fetchItem.addActionListener(actionEvent -> {
            this.context.fetch();
        });
        addItem(fetchItem);

        // ------------------------------------------------------------

        final JMenuItem statusItem = new ThemedMenuItem("Status", theme);
        statusItem.addActionListener(actionEvent -> {
            this.context.fetchStatus();
        });
        addItem(statusItem);

        // ------------------------------------------------------------

        addSeparator();

        // ------------------------------------------------------------

        final JMenuItem commitItem = new ThemedMenuItem("Commit...", theme);
        commitItem.addActionListener(actionEvent -> {
            final String message = GuiUtil.popupInput("Commit message");

            if (message == null)
                return;

            if (message.isBlank()) {
                GuiUtil.popupInfo("Commit message may not be blank");
                return;
            }

            this.context.commit(message);
        });
        addItem(commitItem);

        // ------------------------------------------------------------

        addSeparator();

        // ------------------------------------------------------------

        final JMenuItem pushItem = new ThemedMenuItem("Push...", theme);
        pushItem.addActionListener(actionEvent -> {
            final Project currentProject = this.context.getCurrentProject();
            if (currentProject == null)
                return;

            final List<String> localBranches = currentProject.getBranchAllResult().getLocalBranches();
            final List<String> remotes = currentProject.getRemotes();

            final PushPopup pushPopup = new PushPopup(mainView, "Push", localBranches, remotes);
            if (pushPopup.wasCanceled())
                return;

            final String remote = pushPopup.getSelectedRemote();
            final String localBranch = pushPopup.getSelectedLocalBranch();
            this.context.push(remote, localBranch);
        });
        addItem(pushItem);

        // ------------------------------------------------------------

        final JMenuItem pullItem = new ThemedMenuItem("Pull", theme);
        pullItem.addActionListener(actionEvent -> {
            this.context.pull();
        });
        addItem(pullItem);

        // ------------------------------------------------------------

        addSeparator();

        // ------------------------------------------------------------

        final JMenu checkoutMenu = new ThemedMenu("Checkout", theme);

        final JMenuItem localItem = new ThemedMenuItem("Local...", theme);
        localItem.addActionListener(actionEvent -> {
            final CheckoutPopup checkoutPopup = new CheckoutPopup(mainView,
                "Checkout local branch",
                this.context.getCurrentProject().getBranchAllResult().getLocalBranches());

            if (checkoutPopup.wasCanceled())
                return;

            this.context.checkoutBranch(checkoutPopup.getSelectedBranchName());
        });
        checkoutMenu.add(localItem);

        final JMenuItem remoteItem = new ThemedMenuItem("Remote...", theme);
        remoteItem.addActionListener(actionEvent -> {
            final CheckoutPopup checkoutPopup = new CheckoutPopup(mainView,
                "Checkout remote branch",
                this.context.getCurrentProject().getBranchAllResult().getRemoteBranches());

            if (checkoutPopup.wasCanceled())
                return;

            final String remoteBranch = checkoutPopup.getSelectedBranchName();
            final int substringIndex = remoteBranch.indexOf('/');
            // indexOf returns -1 if none was found, prevent exception
            final String branchName =
                substringIndex >= 0 ? remoteBranch.substring(substringIndex + 1) : remoteBranch;

            this.context.checkoutBranch(branchName);
        });
        checkoutMenu.add(remoteItem);

        addItem(checkoutMenu);
    }

    private void addItem(JMenuItem item) {
        add(item);
        this.items.add(item);
    }

    public void updateGui() {
        final boolean showProject = this.context.getCurrentProject() != null;
        for (JMenuItem item : this.items)
            item.setEnabled(showProject);
    }
}
