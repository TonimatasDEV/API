package dev.tonimatas.maven.api;

import java.io.File;

public class Version {
    private String version;

    public Version() {
        File filesFolder = new File("1201");

        if (!filesFolder.exists()) {
            this.version = "error";
            return;
        }

        File[] files = filesFolder.listFiles();

        if (files == null) {
            this.version = "error";
            return;
        }

        for (File file : files) {
            this.version = file.getName().split("-")[3];
            break;
        }
    }

    public String getVersion() {
        return version;
    }
}
