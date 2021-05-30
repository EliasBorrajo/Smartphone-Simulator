package ch.hevs.smartphone.applications.contacts.serialization;

import ch.hevs.smartphone.applications.contacts.Contact;
import ch.hevs.smartphone.applications.contacts.errors.BusinessException;

import java.io.File;
import java.util.ArrayList;

public interface StorableContact {
    ArrayList<Contact> read(File source) throws BusinessException;
    void write(File destination, ArrayList<Contact> contacts) throws BusinessException;
}
