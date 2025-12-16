package org.jcommit;

import org.jcommit.core.Context;
import org.jcommit.gui.util.GuiUtil;

public final class Main {

    public static final String SOFTWARE_NAME = "JCommit";
    public static final String VERSION = "0.1.0";

    public static void main(String[] args) {
        Log.info(SOFTWARE_NAME + " " + VERSION + " starting");

        try {
            new Context();
        } catch(Exception exception) {
            GuiUtil.popupError(null, exception.getMessage());
        }
    }
}
