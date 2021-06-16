package ch.hevs.smartphone.parameters.utils;

import java.io.File;
import java.io.IOException;

/**
 * This class create a temporary JSON file,
 * that can be erase at the end of the test
 *
 * @author Beuchat Jean-Luc
 */

public class TempFile {
    /**
     * @param deleteOnExit
     * @return a File object
     */
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

