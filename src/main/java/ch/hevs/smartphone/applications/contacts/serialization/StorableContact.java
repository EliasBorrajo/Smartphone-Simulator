package ch.hevs.smartphone.applications.contacts.serialization;

import ch.hevs.smartphone.applications.contacts.Contact;
import ch.hevs.smartphone.applications.contacts.errors.BusinessException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Interface to read and write JSON
 *
 * @author Bourquin Jonathan
 */
public interface StorableContact
{
    /**
     * Read JSON File
     *
     * @throws BusinessException
     * @throws IOException
     */
    ArrayList<Contact> read() throws BusinessException, IOException;

    /**
     * Write JSON File
     *
     * @param destination
     * @param contacts
     * @throws BusinessException
     */
    void write(File destination, ArrayList<Contact> contacts) throws BusinessException;
}

