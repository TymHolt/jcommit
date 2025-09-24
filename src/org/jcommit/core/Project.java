package org.jcommit.core;

import java.io.File;

public final class Project {

    public static boolean canBeProject(File file) {
        return file != null && file.exists() && file.isDirectory();
    }

    private final File file;

    public Project(File file) {
        if (!canBeProject(file))
            throw new IllegalArgumentException("File " + file.getAbsolutePath() +
                " can not be used as project");

        this.file = file;
    }

    public File getFile() {
        return this.file;
    }

    public boolean fileIsValid() {
        return canBeProject(this.file);
    }
}
