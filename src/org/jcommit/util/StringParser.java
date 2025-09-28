package org.jcommit.util;

public final class StringParser {

    private final String string;
    private int index;
    private char currentChar;

    public StringParser(String string) {
        this.string = string;
        this.index = -1;
        next();
    }

    public boolean isFinished() {
        return this.index >= this.string.length();
    }

    private void next() {
        this.index++;

        if (isFinished())
            return;

        this.currentChar = string.charAt(this.index);
    }

    public void skipWhitespace() {
        while (!isFinished()) {
            if (!Character.isWhitespace(this.currentChar))
                return;

            next();
        }
    }

    public String readUntilChar(char c, boolean skipChar) {
        final StringBuilder stringBuilder = new StringBuilder();

        while (!isFinished()) {
            if (this.currentChar == c) {
                if (skipChar)
                    next();

                break;
            }

            stringBuilder.append(this.currentChar);
            next();
        }

        return stringBuilder.toString();
    }

    public String readUntilWhitespace() {
        final StringBuilder stringBuilder = new StringBuilder();

        while (!isFinished()) {
            if (Character.isWhitespace(this.currentChar))
                break;

            stringBuilder.append(this.currentChar);
            next();
        }

        return stringBuilder.toString();
    }

    public String readUntilEnd() {
        final StringBuilder stringBuilder = new StringBuilder();

        while (!isFinished()) {
            stringBuilder.append(this.currentChar);
            next();
        }

        return stringBuilder.toString();
    }

    public static boolean startsWithIgnoreWhitespace(String string, String start) {
        final StringParser stringParser = new StringParser(string);
        stringParser.skipWhitespace();
        return stringParser.readUntilEnd().startsWith(start);
    }
}
