package org.jcommit;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

public final class Log {

    private static boolean printDebug = false;
    private static boolean printToFile = false;
    private static boolean printTimeStamp = false;
    private static String logFilePath = "log.txt";

    public static void setPrintDebug(boolean flag) {
        printDebug = flag;
    }

    public static void setPrintToFile(boolean flag) {
        printToFile = flag;
    }

    public static void setPrintTimeStamp(boolean flag) {
        printTimeStamp = flag;
    }

    public static void setLogFilePath(String path) {
        logFilePath = path;
    }

    public static void info(String message) {
        for (String line : message.split("\n"))
            output("[INFO] " + line);
    }

    public static void error(String message) {
        for (String line : message.split("\n"))
            output("[ERROR] " + line);
    }

    public static void debug(String message) {
        if (!printDebug)
            return;

        for (String line : message.split("\n"))
            output("[DEBUG] " + line);
    }

    private static void output(String line) {
        if (printTimeStamp)
            line = "[" + getTimeStamp() + "] " + line;

        System.out.println(line);

        if (!printToFile)
            return;

        final File logFile = new File(logFilePath);
        if (!logFile.exists()) {
            try {
                logFile.createNewFile();

                if (!logFile.isFile())
                    throw new IOException("Not a file");
            } catch (IOException exception) {
                // Print directly to System.err, log would be recursive call
                System.err.println("LOG FILE COULD NOT BE CREATED");
                exception.printStackTrace(System.err);
                return;
            }
        }

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(logFile, true);
            fileWriter.write(line);
            fileWriter.write('\n');
            fileWriter.close();
        } catch (IOException exception) {
            // Print directly to System.err, log would be recursive call
            System.err.println("LOG FILE COULD NOT BE WRITTEN");
            exception.printStackTrace(System.err);
        }
    }

    private static String getTimeStamp() {
        final Calendar calendar = Calendar.getInstance();
        final String hour = makeDoubleDigit(calendar.get(Calendar.HOUR_OF_DAY));
        final String minute = makeDoubleDigit(calendar.get(Calendar.MINUTE));
        final String second = makeDoubleDigit(calendar.get(Calendar.SECOND));

        return hour + ":" + minute + ":" + second;
    }

    private static String makeDoubleDigit(int value) {
        String stringValue = Integer.toString(value);

        if (stringValue.length() < 2)
            stringValue = "0" + stringValue;

        return stringValue;
    }
}
