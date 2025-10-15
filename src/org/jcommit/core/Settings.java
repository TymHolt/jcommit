package org.jcommit.core;

import org.jcommit.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public final class Settings {

    private static final String SETTINGS_FILE_PATH  = "settings.properties";

    private final File file;
    private final Properties properties;

    public Settings() {
        this(new File(SETTINGS_FILE_PATH));
    }

    public Settings(File file) {
        if (file == null)
            throw new NullPointerException("File is null");

        this.file = file;
        this.properties = new Properties();
    }

    public void load() {
        Log.info("Loading settings " + this.file.getAbsolutePath());

        if (!file.exists()) {
            Log.info("Settings file does not exist");
            return;
        }

        try {
            this.properties.load(new FileInputStream(this.file));
        } catch (IOException exception) {
            Log.error("Settings file could not be loaded: " + exception.getMessage());
        }
    }

    public void save() {
        Log.info("Saving settings " + this.file.getAbsolutePath());

        try {
            this.properties.store(new FileWriter(this.file), null);
        } catch (IOException exception) {
            Log.error("Settings file could not be saved: " + exception.getMessage());
        }
    }

    private static final String PRINT_DEBUG_KEY = "print_debug";
    private static final boolean PRINT_DEBUG_DEFAULT = false;

    public void setPrintDebug(boolean flag) {
        this.properties.setProperty(PRINT_DEBUG_KEY, Boolean.toString(flag));
    }

    public boolean getPrintDebug() {
        final String stringValue = this.properties.getProperty(PRINT_DEBUG_KEY,
            Boolean.toString(PRINT_DEBUG_DEFAULT));
        boolean value = PRINT_DEBUG_DEFAULT;

        try {
            value = Boolean.parseBoolean(stringValue);
        } catch (Exception exception) {
            // Do nothing, just prevent exception if parsing fails
        }

        // Save this value again to make sure it exists and has right format
        this.properties.setProperty(PRINT_DEBUG_KEY, Boolean.toString(value));
        return value;
    }

    private static final String PRINT_TO_FILE_KEY = "print_to_file";
    private static final boolean PRINT_TO_FILE_DEFAULT = false;

    public void setPrintToFile(boolean flag) {
        this.properties.setProperty(PRINT_TO_FILE_KEY, Boolean.toString(flag));
    }

    public boolean getPrintToFile() {
        final String stringValue = this.properties.getProperty(PRINT_TO_FILE_KEY,
                Boolean.toString(PRINT_TO_FILE_DEFAULT));
        boolean value = PRINT_TO_FILE_DEFAULT;

        try {
            value = Boolean.parseBoolean(stringValue);
        } catch (Exception exception) {
            // Do nothing, just prevent exception if parsing fails
        }

        // Save this value again to make sure it exists and has right format
        this.properties.setProperty(PRINT_TO_FILE_KEY, Boolean.toString(value));
        return value;
    }

    private static final String PRINT_TIME_STAMP_KEY = "print_time_stamp";
    private static final boolean PRINT_TIME_STAMP_DEFAULT = false;

    public void setPrintTimeStamp(boolean flag) {
        this.properties.setProperty(PRINT_TIME_STAMP_KEY, Boolean.toString(flag));
    }

    public boolean getPrintTimeStamp() {
        final String stringValue = this.properties.getProperty(PRINT_TIME_STAMP_KEY,
                Boolean.toString(PRINT_TIME_STAMP_DEFAULT));
        boolean value = PRINT_TIME_STAMP_DEFAULT;

        try {
            value = Boolean.parseBoolean(stringValue);
        } catch (Exception exception) {
            // Do nothing, just prevent exception if parsing fails
        }

        // Save this value again to make sure it exists and has right format
        this.properties.setProperty(PRINT_TIME_STAMP_KEY, Boolean.toString(value));
        return value;
    }
}
