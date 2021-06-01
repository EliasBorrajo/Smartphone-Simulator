package ch.hevs.smartphone.applications.contacts.serialization;

import ch.hevs.smartphone.applications.contacts.Contact;
import ch.hevs.smartphone.errors.BusinessException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public interface StorableContact
{
    // To read JSON File
    ArrayList<Contact> read() throws BusinessException, IOException;

    // To write JSON File
    void write(File destination, ArrayList<Contact> contacts) throws BusinessException;
}

