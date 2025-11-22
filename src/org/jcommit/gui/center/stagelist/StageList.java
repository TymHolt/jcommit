package org.jcommit.gui.center.stagelist;

import javax.swing.*;
import java.util.List;

public final class StageList extends JList<StageItem> {

    public StageList(List<StageItem> itemList) {
        final DefaultListModel<StageItem> model = new DefaultListModel<>();

        for (StageItem item : itemList)
            model.addElement(item);

        setModel(model);
        setCellRenderer(new StageListCellRenderer());
    }
}
