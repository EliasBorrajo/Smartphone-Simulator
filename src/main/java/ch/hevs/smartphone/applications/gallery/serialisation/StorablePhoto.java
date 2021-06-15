package ch.hevs.smartphone.applications.gallery.serialisation;

import ch.hevs.smartphone.applications.contacts.errors.BusinessException;
import ch.hevs.smartphone.applications.gallery.Photo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Interface to read and write JSON
 *
 * @author Lonfat Milena
 */
public interface StorablePhoto {
    /**
     * Read JSON File
     *
     * @throws BusinessException
     * @throws IOException
     */
    ArrayList<Photo> read() throws BusinessException, IOException;

    /**
     * Write JSON File
     *
     * @param destination
     * @param photos
     * @throws BusinessException
     */
    void write(File destination, ArrayList<Photo> photos) throws BusinessException;
}
