package org.jcommit;

import java.util.Calendar;

public final class Log {

    private static boolean printDebug = false;
    private static boolean printToFile = false;
    private static boolean printTimeStamp = false;

    public static void setPrintDebug(boolean flag) {
        printDebug = flag;
    }

    public static void setPrintToFile(boolean flag) {
        printToFile = flag;
    }

    public static void setPrintTimeStamp(boolean flag) {
        printTimeStamp = flag;
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

        // TODO Print to file
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
