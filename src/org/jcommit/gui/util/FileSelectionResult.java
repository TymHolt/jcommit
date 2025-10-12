package org.jcommit.gui.util;

import java.io.File;

public final class FileSelectionResult {

    private final FileSelectionOption option;
    private final File file;

    FileSelectionResult(FileSelectionOption option, File file) {
        this.option = option;
        this.file = file;
    }

    public FileSelectionOption getOption() {
        return this.option;
    }

    public File getFile() {
        return file;
    }
}
