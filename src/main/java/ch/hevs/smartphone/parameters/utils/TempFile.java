package ch.hevs.smartphone.parameters.utils;

import java.io.File;
import java.io.IOException;

public class TempFile {

    public static File getTempFile(boolean deleteOnExit) {
        File tempFile = null;
        try {
            tempFile = File.createTempFile("data", ".json");
        } catch (IOException e) {
            System.err.println("Failed to create temporary file");
            System.exit(1);
        }

        if (deleteOnExit) {
            tempFile.deleteOnExit();
        }

        return tempFile;
    }
}
