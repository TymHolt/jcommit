package org.jcommit.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public final class FileLoader {

    public static List<String> loadFileLines(File file) throws IOException {
        final List<String> lines = new ArrayList<>();
        final FileInputStream inputStream = new FileInputStream(file);
        StringBuilder lineBuilder = new StringBuilder();

        int readByteValue;
        while((readByteValue = inputStream.read()) > -1) {
            final char readChar = (char) readByteValue;

            if (readChar == '\r')
                continue;

            if (readChar == '\n') {
                lines.add(lineBuilder.toString());
                lineBuilder = new StringBuilder();
                continue;
            }

            lineBuilder.append(readChar);
        }

        if (!lineBuilder.isEmpty())
            lines.add(lineBuilder.toString());

        inputStream.close();
        return lines;
    }

    public static void writeFileLines(File file, List<String> lines) throws IOException {
        final FileWriter fileWriter = new FileWriter(file, false);

        for (String line : lines) {
            fileWriter.write(line);
            fileWriter.write('\n');
        }

        fileWriter.close();
    }
}
