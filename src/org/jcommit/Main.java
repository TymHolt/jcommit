package org.jcommit;

import org.jcommit.gui.MainView;

public final class Main {

    public static final String SOFTWARE_NAME = "JCommit";
    public static final String VERSION_NAME = "In-Dev";
    public static final String VERSION_NUMBER = "0.0.1";

    public static String getVersionName() {
        return VERSION_NAME + " v"  + VERSION_NUMBER;
    }

    public static void main(String[] args) {
        Log.info(SOFTWARE_NAME + " " + getVersionName() + " started...");
        new MainView().init();
    }
}
