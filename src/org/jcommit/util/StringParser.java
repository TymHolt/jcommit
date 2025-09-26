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

    private boolean isWhitespace(char c) {
        return c == ' ' || c == '\t' || c == '\n' || c == '\r';
    }

    public void skipWhitespace() {
        while (!isFinished()) {
            if (!isWhitespace(this.currentChar))
                return;

            next();
        }
    }

    public String readUntilWhitespace() {
        final StringBuilder stringBuilder = new StringBuilder();

        while (!isFinished()) {
            if (isWhitespace(this.currentChar))
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
}
